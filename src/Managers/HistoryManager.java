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

    public History sellProductToCustomer(List<Customer> customers, List<Product> products) {
        System.out.println("------------- Sell the Product to Customer ----------------");
        History history = new History();

        int countCustomersInList = customerManager.printListCustomers(customers);
        System.out.print("Enter number customer: ");
        int readerNumber = InputFromKeyboard.inputNumberFromRange(1, countCustomersInList);
        history.setCustomer(customers.get(readerNumber - 1));

        int countProductsInList = productManager.printListProducts(products);
        System.out.print("Enter number product: ");
        int bookNumber = InputFromKeyboard.inputNumberFromRange(1, countProductsInList);
        if (products.get(bookNumber - 1).getCount() > 0) {
            history.setProduct(products.get(bookNumber - 1));
            products.get(bookNumber - 1).setCount(products.get(bookNumber - 1).getCount() - 1);
            history.setSellProductToCustomerDate(new GregorianCalendar().getTime());
            return history;
        } else {
            System.out.println("All products are sold ");
            return null;
        }
    }

    public void returnProduct(List<History> histories) {
        System.out.println("-------- Return product to shop ---------");

        int countSoldProducts = printListSoldProduct(histories);
        if (countSoldProducts < 1) {
            System.out.println("No products");
            return;
        }

        System.out.print("Enter number product: ");
        int historyNumber = InputFromKeyboard.inputNumberFromRange(1, histories.size());
        if (histories.get(historyNumber - 1).getProduct().getCount() < histories.get(historyNumber - 1).getProduct().getQuantity()) {
            histories.get(historyNumber - 1).setReturnProduct(new GregorianCalendar().getTime());
            histories.get(historyNumber - 1).getProduct().setCount(histories.get(historyNumber - 1).getProduct().getCount() + 1);
            System.out.printf("Product \"%s\" returned%n", histories.get(historyNumber - 1).getProduct().getTitle());
        } else {
            System.out.println("All products are already in stock");
        }
    }

public void processTransaction(List<History> histories, boolean sell) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter the index of the history item: ");
    int index = InputFromKeyboard.inputNumberFromRange(1, histories.size()) - 1;

    History history = histories.get(index);
    Product product = history.getProduct();
    Customer customer = history.getCustomer();

    if (sell) {
        int totalPrice = product.getPrice();

        if (customer.getBalance() >= totalPrice) {
            customer.setBalance(customer.getBalance() - totalPrice);
            System.out.println("Transaction successful. Balance after purchase: " + customer.getBalance());
        } else {
            System.out.println("Insufficient funds. Transaction failed.");
        }
    } else {
        customer.setBalance(customer.getBalance() + product.getPrice());
        System.out.println("Product returned. Balance after return: " + customer.getBalance());
    }
}


    public int printListSoldProduct(List<History> histories) {
        int countSoldProduct = 0;
        System.out.println("List selling products:");

        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getReturnProduct() == null) {
                System.out.printf("%d. %s. selling %s %s%n",
                        i + 1,
                        histories.get(i).getProduct().getTitle(),
                        histories.get(i).getCustomer().getFirstname(),
                        histories.get(i).getCustomer().getLastname()
                );
                countSoldProduct++;
            }
        }

        if (countSoldProduct < 1) {
            System.out.println("\tNo products to sell");
        }

        return countSoldProduct;
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
}