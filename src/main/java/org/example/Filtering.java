package org.example;
import java.util.ArrayList;
import java.util.List;

public class Filtering {

    static class Transaction {
        double amount;

        public Transaction(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Amount: " + amount;
        }
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        // sample data
        list.add(new Transaction(100));   // deposit
        list.add(new Transaction(-50));   // payment
        list.add(new Transaction(200));   // deposit

        // Show only deposits
        list.stream()
                .filter(t -> t.getAmount() > 0)
                .forEach(System.out::println);
    }
}