package org.example;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
//allows list and arrays
import java.util.Scanner;

public class Main {

    static List<Transaction> ledger = FileManager.getTransactions();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

//this tracker does deposits,payments,trasaction history can also sort by date or vendor.

            System.out.println("\n=== HOME MENU ===");
            System.out.println("\nD) Deposit");
            System.out.println("P) Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().toUpperCase();
//prevents hiccups that could be caused by user input like case errors

            switch (choice) {

                //adds income, calls this method.
                case "D":
                    addDeposit(scanner);
                    break;
                //adds expense,calls this method.
                case "P":
                    addPayment(scanner);
                    break;
                //opens next menu
                case "L":
                    ledgerMenu(scanner);
                    break;
                //exits
                case "X":
                    System.out.println("\n=====================Enjoy the rest of your day====================");
                    System.out.println("It was a pleasure serving you today!");
                    System.out.println("Rest assured that all your transactions have been recorded!");
                    System.out.println("Look forward to assisting you next time! ");
                    return;
                    //stops it
            }
        }
    }

    // Home actions

    public static void addDeposit(Scanner scanner) {
        //user puts in description
        System.out.print("Enter the description please: ");
        String desc = scanner.nextLine();

        System.out.print("Enter the vendor please: ");
        String vendor = scanner.nextLine();
        //makes it into a number
        System.out.print("Enter the amount please: ");
        double amount = Double.parseDouble(scanner.nextLine());
//YAYY this is where a transaction object is made
        Transaction t = new Transaction(
                LocalDate.now().toString(),
                LocalTime.now().toString(),
                desc,
                vendor,
                amount
        );
//commits to memory
        ledger.add(t);
        FileManager.writeTransaction(t);
    //writes to file
        System.out.println("Thank you for the information you provided.Deposit successully added to ledger!");
    }
//negative because were losing money
    public static void addPayment(Scanner scanner) {

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction t = new Transaction(
                LocalDate.now().toString(),
                LocalTime.now().toString(),
                desc,
                vendor,
                -Math.abs(amount)
                //makes it negative
        );

        ledger.add(t);
        FileManager.writeTransaction(t);

        System.out.println("Thank you for the information you provided. Payment successully added to ledger!");
    }

    // Ledger menu

    public static void ledgerMenu(Scanner scanner) {

        while (true) {
//shows menu for viewing and sorting
            System.out.println("\nA) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("0) Back");
        //options
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "A":
                    showAll();
                    break;

                case "D":
                    showDeposits();
                    break;

                case "P":
                    showPayments();
                    break;

                case "R":
                    reports(scanner);
                    break;

                case "0":
                    return;
            }
        }
    }

    // ledger different views

    public static void showAll() {
        ledger.stream()
                //sorts transactions newest to oldest
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                //makes it new to old
                .forEach(System.out::println);
                //each transaction
    }

    public static void showDeposits() {
        ledger.stream()
                .filter(t -> t.getAmount() > 0)
                //ONLY positive values
                .forEach(System.out::println);
    }

    public static void showPayments() {
        ledger.stream()
                .filter(t -> t.getAmount() < 0)
                //ONLY negative values
                .forEach(System.out::println);
    }

    // Reports

    public static void reports(Scanner scanner) {
    //filtering modes
        while (true) {

            System.out.println("\n1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Vendor Search");
            System.out.println("0) Back");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    String month = LocalDate.now().toString().substring(0, 7);
                    ledger.stream()
                            .filter(t -> t.getDate().startsWith(month))
                            //current month
                            .forEach(System.out::println);
                    break;

                case "2":
                    String prevMonth = LocalDate.now().minusMonths(1).toString().substring(0, 7);
                    //gets last month
                    ledger.stream()
                            .filter(t -> t.getDate().startsWith(prevMonth))
                            .forEach(System.out::println);
                    break;

                case "3":
                    String year = LocalDate.now().toString().substring(0, 4);
                    //sorts by year
                    ledger.stream()
                            .filter(t -> t.getDate().startsWith(year))
                            .forEach(System.out::println);
                    break;

                case "4":
                    String prevYear = String.valueOf(LocalDate.now().getYear() - 1);
                    ledger.stream()
                            .filter(t -> t.getDate().startsWith(prevYear))
                            .forEach(System.out::println);
                    break;

                case "5":
                    System.out.print("Vendor: ");
                    //matches vendor
                    String vendor = scanner.nextLine();

                    ledger.stream()
                            .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                            .forEach(System.out::println);
                    break;

                case "0":
                    return;
            }
        }
    }
}



