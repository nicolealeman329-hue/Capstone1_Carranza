package org.example;



    public class Transaction {

        String date;
        String time;
        String description;
        String vendor;
        double amount;
        //variables

        //constructor
        public Transaction(String date, String time, String description, String vendor, double amount) {
            this.date = date;
            this.time = time;
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }
        //gives values to objects

        public double getAmount() {
            return amount;
        }

        public String getVendor() {
            return vendor;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }
        //getters

        // csv placement
        public String toCSV() {
            return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        }

        // Display format
        public String toString() {
            return date + " " + time + " | " + description + " | " + vendor + " | $" + amount;
        }
    }

// This represents a single transaction. Each objects holds the date,time and description,vendor
//there's also override included so that it'll save directly to the proper format for the csv file.