package org.example;
import java.io.*;
import java.util.*;

public class FileManager {

    // Load transactions from file
    public static List<Transaction> getTransactions() {

        List<Transaction> list = new ArrayList<>();
        //list to store data
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
        //opens it for reading
            String line;
            //stores variables

            while ((line = reader.readLine()) != null) {
            //reads until null

                String[] p = line.split("\\|");
            //seperates it

                if (p.length < 5) continue;
                //if not meeting data skip

                list.add(new Transaction(
                        p[0],
                        //date
                        p[1],
                        //time
                        p[2],
                        //description
                        p[3],
                        //vendor
                        Double.parseDouble(p[4])
                        //converting from string to double
                ));
            }
            //made a object
        } catch (Exception e) {
            System.out.println("Error with file!");
        }
        //helps catch errors with file and lets user know
        return list;
    }

    // Save one transaction
    public static void writeTransaction(Transaction t) {

        try (FileWriter writer = new FileWriter("transactions.csv", true)) {

            writer.write(t.toCSV());
            //put data onto csv file

            writer.write(System.lineSeparator());
            //new line after writing
        } catch (Exception e) {
            //writing errors
            e.printStackTrace();
            //will help with debugging
        }
    }
}
