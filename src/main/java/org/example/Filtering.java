package org.example;
import java.util.ArrayList;
import java.util.List;

public class Filtering {

    static class Transaction {
        double amount;
        //stores the amount either positive or negative
        public Transaction(double amount) {
            this.amount = amount;
        }

        //constructor starts it with given amount
        public double getAmount() {
            return amount;
        }
        //getter for amount
        @Override
        public String toString() {
            return "Amount: " + amount;
        }
    }
    //how object is printed

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();
        //creates list to put objects
        // sample data
        list.add(new Transaction(100));   // deposit
        list.add(new Transaction(-50));   // payment
        list.add(new Transaction(200));   // deposit

        // Show only deposits
        list.stream()
                //list into stream
                .filter(t -> t.getAmount() > 0)
                //filters for positive deposits
                .forEach(System.out::println);
        // prints transactions
    }
}