package org.pk;

import java.util.Arrays;
import java.util.List;

public class SalesAnalyticsApp {
    public static void main(String[] args) {

        Product laptop = new Product("P001", "Laptop", "Electronics", 1200);
        Product smartphone = new Product("P002", "Smartphone", "Electronics", 800);
        Product headphones = new Product("P003", "Headphones", "Electronics", 150);
        Product shirt = new Product("P004", "Shirt", "Clothing", 30);
        Product jeans = new Product("P005", "Jeans", "Clothing", 40);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(laptop, 2),
                new Transaction(smartphone, 3),
                new Transaction(headphones, 5),
                new Transaction(shirt, 10),
                new Transaction(jeans, 7),
                new Transaction(laptop, 1),
                new Transaction(smartphone, 2)
        );

        SalesAnalyticsService analyticsService = new SalesAnalyticsService(transactions);
        System.out.println("Transactions grouped by category:");
        analyticsService.groupTransactionsByCategory();

        System.out.println("\nTransactions in 'Electronics' category:");
        analyticsService.filterTransactionsByCategory("Electronics");

        System.out.println("\nTotal revenue by product:");
        analyticsService.calculateTotalRevenueByProduct();

        System.out.println("\nTotal sales revenue:");
        analyticsService.calculateTotalSalesRevenue();

        System.out.println("\nChecking if there is any 'Clothing' product:");
        analyticsService.checkIfAnyProductInCategory("Clothing");

        System.out.println("\nTotal quantity sold by product:");
        analyticsService.calculateTotalQuantitySoldByProduct();

        System.out.println("\nListing all products sold:");
        analyticsService.listAllProductsNames();

        System.out.println("\nFinding transaction for 'Laptop':");
        analyticsService.findTransactionByProductName("Laptop");

        System.out.println("\nCount of product categories sold:");
        analyticsService.countProductCategories();

    }
}