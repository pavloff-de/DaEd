package de.pavloff.daed.util;

import com.intellij.openapi.ui.Messages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DataFileHelper {

    private static String[] DELIMITERS = {",", ";", ":", "|", "\t", " "};
    private static char[] QUOTATIONS = new char[]{'"', '"'};

    private String filePath;
    private String[] lines;
    private String delimiter;
    private int header;

    public DataFileHelper(String filePath) {
        this.filePath = filePath;
        initialize(3);
    }

    public String[] getNames() {
        if (header != -1) {
            return lines[header].split(delimiter);
        }

        if (delimiter.length() != 0) {
            int numOfValues = lines[0].split(delimiter).length;

            String[] names = new String[numOfValues];
            for (int i = 0; i < numOfValues; i++) {
                names[i] = "column" + i;
            }

            return names;
        }

        return new String[0];
    }

    public String[][] getData(int rows) {
        if (rows > lines.length) {
            lines = readFile(rows);
        }

        if (delimiter.length() == 0 || lines.length == 0) {
            return new String[0][0];
        }

        int firstDataRow = getHeader() + 1;
        int nonEmptyLines = rows - firstDataRow;
        int numValues = 0;

        for (int i = nonEmptyLines - 1; i > 0; i--) {
            if (lines[i] == null) {
                nonEmptyLines -= 1;
            } else {
                numValues = lines[i].split(delimiter).length;
                break;
            }
        }

        if (nonEmptyLines == 0) {
            return new String[0][0];
        }

        String[][] data = new String[nonEmptyLines][numValues];

        for (int i = 0; i < nonEmptyLines; i++) {
            data[i] = lines[i + firstDataRow].split(delimiter);
        }

        return data;
    }

    private void initialize(int rows) {
        lines = readFile(rows);
        delimiter = guessDelimiter();
        header = getHeader();
    }

    private int getHeader() {
        // -1 if not found
        // 0 if names in the first line

        if (delimiter.length() == 0 || lines.length == 0) {
            return -1;
        }

        String[] vals1 = lines[0].split(delimiter);
        String[] vals2 = lines[1].split(delimiter);

        if (vals1.length != vals2.length) {
            return -1;
        }

        int numOfMatches = 0;
        for (int i = 0; i < vals1.length; i++) {
            if (guessValueType(vals1[i]) == guessValueType(vals2[i])) {
                numOfMatches += 1;
            }
        }

        if (numOfMatches != vals1.length) {
            // different values in first two lines
            // points at a header in first line
            return 0;
        }

        return -1;
    }

    private Class guessValueType(String value) {
        try {
            Integer.valueOf(value);
            return Integer.class;
        } catch (NumberFormatException e) {
        }

        try {
            Float.valueOf(value);
            return Float.class;
        } catch (NumberFormatException e) {
        }

        try {
            Boolean.valueOf(value);
            return Boolean.class;
        } catch (NumberFormatException e) {
        }

        return null;
    }

    private String guessDelimiter() {
        if (lines.length == 0) {
            return "";
        }

        String dataLine = lines[0];
        if (lines.length > 1) {
            // let first line be column names
            // search for the first non empty line
            dataLine = "";
            int z = 1;

            while (dataLine.length() == 0 && z < lines.length) {
                dataLine = lines[z];
                z += 1;
            }
        }

        if (dataLine.length() == 0) {
            return "";
        }

        String guessedDelimiter = "";

        char first = dataLine.charAt(0);
        if (Arrays.asList(QUOTATIONS).contains(first)) {
            // quoted
            // find delimiter after second quotation
            int qPos = dataLine.indexOf(first, 1);
            int dPos = qPos + 1;

            if (dPos != 0 && dPos < dataLine.length()) {
                String d = dataLine.substring(dPos, dPos);

                if (Arrays.asList(DELIMITERS).contains(d)) {
                    return d;
                }
            }
        }

        // try to parse numbers in the line
        int maxNumOfNumbers = 0;
        int idxNumOfNumbers = 0;

        // find also the max split values
        int maxNumOfValues = 0;
        int idxNumOfValues = 0;

        for (int i = 0; i < DELIMITERS.length; i++) {
            String d = DELIMITERS[i];

            if (!dataLine.contains(d)) {
                continue;
            }

            String[] values = dataLine.split(d);
            int n = 0;

            for (String value : values) {
                Class valueType = guessValueType(value);

                if (valueType != null) {
                    n += 1;
                }
            }

            if (n > maxNumOfNumbers) {
                maxNumOfNumbers = n;
                idxNumOfNumbers = i;
            }

            if (values.length > maxNumOfValues) {
                maxNumOfValues = values.length;
                idxNumOfValues = i;
            }
        }

        if (maxNumOfNumbers > 1) {
            return DELIMITERS[idxNumOfNumbers];
        }

        // possibly all values are strings without quotations
        if (maxNumOfValues > 1) {
            return DELIMITERS[idxNumOfValues];
        }

        // delimiter of the file is not in DELIMITERS
        return guessedDelimiter;
    }

    private String[] readFile(int rows) {
        if (rows < 3) {
            rows = 3;
        }

        String[] firstRows = new String[0];

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            if (line != null) {
                firstRows = new String[rows];
            }

            int currentRow = 0;
            while (line != null && currentRow < firstRows.length) {
                firstRows[currentRow] = line;
                line = br.readLine();
                currentRow += 1;
            }

            br.close();
        } catch (IOException e) {
            Messages.showErrorDialog("An error occurred while reading file.\n"
                    + e.getMessage(), "Error");
        }

        return firstRows;
    }
}
