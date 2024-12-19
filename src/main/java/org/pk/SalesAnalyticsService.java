package org.pk;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesAnalyticsService {
    private List<Transaction> transactionLIst;

    public SalesAnalyticsService(List<Transaction> transactionLIst) {
        this.transactionLIst = transactionLIst;
    }

    public void groupTransactionsByCategory() {
        Map<String, List<Transaction>> groupedByTransaction = transactionLIst.stream()
                .collect(Collectors.groupingBy(t -> t.getProduct().getCategory()));

        groupedByTransaction.forEach((category, transactions) -> {
            System.out.println(category + " ");
            transactions.forEach(System.out::println);
        });
    }

    public void filterTransactionsByCategory(String category) {
        transactionLIst.stream()
                .filter(t -> t.getProduct().getCategory().equals(category))
                .forEach(System.out::println);
    }

    public void calculateTotalRevenueByProduct() {
        Map<String, Double> revenueByProduct = transactionLIst.stream()
                .collect(Collectors.groupingBy(t -> t.getProduct().getName(),
                        Collectors.summingDouble(Transaction::getTotal)));

        revenueByProduct.forEach((productName, totalRevenue) -> {
            System.out.println(productName + " $" + totalRevenue);
        });
    }

    public void calculateTotalSalesRevenue() {
        double totalRevenue = transactionLIst.stream()
                .mapToDouble(Transaction::getTotal)
                .sum();
        System.out.println("Total sales revenue: $" + totalRevenue);
    }

    public void checkIfAnyProductInCategory(String category) {
        boolean anyProductInCategory = transactionLIst.stream()
                .anyMatch(t -> t.getProduct().getCategory().equals(category));
        System.out.println("Is there any product in category " + category + " : " + anyProductInCategory);

    }

    public void calculateTotalQuantitySoldByProduct() {
        Map<String, Integer> quantitySoldByProduct = transactionLIst.stream()
                .collect(Collectors.groupingBy(t -> t.getProduct().getName(),
                        Collectors.summingInt(Transaction::getQuantity)));

        quantitySoldByProduct.forEach((productName, quantitySold) -> {
            System.out.println(productName + " : " + quantitySold + " units sold");
        });
    }

    public void listAllProductsNames() {
        String productNames = transactionLIst.stream()
                .map(t -> t.getProduct().getName())
                .distinct()
                .collect(Collectors.joining(" , "));
        System.out.println("Products sold : " + productNames);
    }

    public void findTransactionByProductName(String productName) {
        Optional<Transaction> transaction = transactionLIst.stream()
                .filter(t -> t.getProduct().getName().equals(productName))
                .findFirst();

        transaction.ifPresentOrElse(
                t -> System.out.println("Found transaction " + t),
                () -> System.out.println("No transaction found for " + productName)
        );
    }

    public void countProductCategories() {
        Map<String, Long> categoryCount = transactionLIst.stream()
                .flatMap(t -> Stream.of(t.getProduct().getCategory()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        categoryCount.forEach((category, count) -> {
            System.out.println(category + ": " + count + " occurrences");
        });
    }
}
