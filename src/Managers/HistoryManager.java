/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import entity.Product;
import entity.History;
import entity.Customer;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputFromKeyboard;
import java.util.*;

public class HistoryManager {

    private final Scanner scanner;
    private final CustomerManager customerManager;
    private final ProductManager productManager;

    public HistoryManager(Scanner scanner) {
        this.scanner = scanner;
        this.customerManager = new CustomerManager(scanner);
        this.productManager = new ProductManager(scanner);
    }

    public History sellProductToCustomer(List<Customer>customers, List<Product> products) {
        System.out.println("------------- Sell the Product to Customer ----------------");
        History history = new History();

        int countCustomersInList = customerManager.printListCustomers(customers);
        System.out.print("Enter number customer: ");
        int readerNumber = InputFromKeyboard.inputNumberFromRange(1, 
                countCustomersInList);
        history.setCustomer(customers.get(readerNumber - 1));

        int countProductsInList = productManager.printListProducts(products);
        System.out.print("Enter number product: ");
        int bookNumber = InputFromKeyboard.inputNumberFromRange(1, 
                countProductsInList);
        if (products.get(bookNumber - 1).getCount() > 0) {
            history.setProduct(products.get(bookNumber - 1));
            products.get(bookNumber - 1).setCount(products.get
        (bookNumber - 1).getCount() - 1);
            history.setSellProductToCustomerDate(new 
        GregorianCalendar().getTime());
            return history;
        } else {
            System.out.println("All products are sold ");
            return null;
        }
    }

public void printCustomersByPurchaseCount(List<History> histories, List<Customer> customers) {
    Map<Customer, Integer> customerPurchaseCount = new HashMap<>();

    // Подсчитываем количество покупок для каждого покупателя
    for (History history : histories) {
        Customer currentCustomer = history.getCustomer();
        if (history.getReturnProduct() == null) {
            if (customerPurchaseCount.containsKey(currentCustomer)) {
                customerPurchaseCount.put(currentCustomer, customerPurchaseCount.get(currentCustomer) + 1);
            } else {
                customerPurchaseCount.put(currentCustomer, 1);
            }
        }
    }

    // Сортируем покупателей по количеству покупок
    Map<Customer, Integer> sortedCustomersByPurchaseCount = customerPurchaseCount.entrySet()
            .stream()
            .sorted(Map.Entry.<Customer, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue,
                    LinkedHashMap::new));

    System.out.println("Customers by purchase count:");
    int count = 1;
    for (Map.Entry<Customer, Integer> entry : sortedCustomersByPurchaseCount.entrySet()) {
        System.out.printf("%d. %s %s - Number of purchases: %d%n",
                count++, entry.getKey().getFirstname(), entry.getKey().getLastname(), entry.getValue());
    }
}




    public void printRankingOfProductsBeingSold(List<History> histories) {
        Map<Product, Integer> mapProducts = new HashMap<>();
        for (int i = 0; i < histories.size(); i++) {
            Product product = histories.get(i).getProduct();
            if (mapProducts.containsKey(product)) {
                mapProducts.put(product, mapProducts.get(product) + 1);
            } else {
                mapProducts.put(product, 1);
            }
        }

        Map<Product, Integer> sortedMap = mapProducts.entrySet()
                .stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

           System.out.println("Ranking of products being sold:");
    int n = 1;
    for (Map.Entry<Product, Integer> entry : sortedMap.entrySet()) {
        System.out.printf("%d. %s - %d times%n",
                n++, entry.getKey().getTitle(), entry.getValue());
    }
}
  
      public void calculateTotalSales(List<History> histories) {
       int totalSalesAmount = 0;
        int totalSoldProducts = 0;

        for (History history : histories) {
            if (history.getReturnProduct() == null) {
                totalSalesAmount += history.getProduct().getPrice();
                totalSoldProducts++;
            }
        }

        System.out.println("Total sales amount: " + totalSalesAmount);
        System.out.println("Total sold products: " + totalSoldProducts);
    }
 
}
