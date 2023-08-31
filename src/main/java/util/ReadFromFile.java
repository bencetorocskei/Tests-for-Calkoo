package util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadFromFile {
///home/bence/codecool/Calkoo/src/test/resources
   // valueInputFor2DecimalDigitsPrecisionValidation.csv
    public static void readFromCsv () throws CsvException, IOException {
        CSVReader readCsv = new CSVReader(new FileReader("home\\bence\\codecool\\Calkoo\\src\\test\\resources\\valueInputFor2DecimalDigitsPrecisionValidation.csv"));
        String [] nextLine;
        while ((nextLine = readCsv.readNext())!= null) {
            System.out.println(nextLine[0]);
        }
    }

}
