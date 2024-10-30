package DataGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AsciiToCsv {

    public static void main(String[] args) {
        String asciiFilePath = "/src/main/java/DataGenerator/Ecommerce-Customers"; // Path to your ASCII file
        String csvFilePath = "/DataGenerator/Ecommerce-Customers1"; // Path to save the CSV file

        convertAsciiToCsv(asciiFilePath, csvFilePath);
    }

    public static void convertAsciiToCsv(String asciiFilePath, String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(asciiFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Replace spaces with commas to create CSV format
                String csvLine = line.replace(" ", ",");
                bw.write(csvLine);
                bw.newLine();
            }

            System.out.println("Conversion complete. CSV file created at: " + csvFilePath);

        } catch (IOException e) {
            System.err.println("Error occurred during conversion: " + e.getMessage());
        }
    }
}

