/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import entity.Customer;
import entity.History;
import entity.Product;
import java.util.GregorianCalendar;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import tools.InputFromKeyboard;

public class HistoryManager {
    
    private final Scanner scanner;
    private final EntityManager entityManager;

    public HistoryManager(Scanner scanner, EntityManager entityManager) {
        this.scanner = scanner;
        this.entityManager = entityManager;
    }

    public History sellProductToCustomer(List<Customer> customers, List<Product> products) {
        System.out.println("------------- Sell the Product to Customer ----------------");
        History history = new History();

        CustomerManager customerManager = new CustomerManager(scanner);
        int countCustomersInList = customerManager.printListCustomers(customers);
        System.out.print("Enter number customer: ");
        int readerNumber = InputFromKeyboard.inputNumberFromRange(1, countCustomersInList);
        history.setCustomer(customers.get(readerNumber - 1));

        ProductManager productManager = new ProductManager(scanner);
        int countProductsInList = productManager.printListProducts(products);
        System.out.print("Enter number product: ");
        int productNumber = InputFromKeyboard.inputNumberFromRange(1, countProductsInList);
        Product selectedProduct = products.get(productNumber - 1);

        if (selectedProduct.getCount() > 0) {
            history.setProduct(selectedProduct);
            selectedProduct.setCount(selectedProduct.getCount() - 1);
            history.setSellProductToCustomerDate(new GregorianCalendar().getTime());

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(history);
                transaction.commit();
                return history;
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
                System.out.println("Error occurred while selling the product to the customer.");
            }
        } else {
            System.out.println("All products are sold");
        }
        return null;
    }

    public void printCustomersByPurchaseCount() {
        List<History> histories = entityManager.createQuery("SELECT h FROM History h WHERE h.returnProduct IS NULL", History.class)
                .getResultList();

        Map<Customer, Long> customerPurchaseCount = histories.stream()
                .collect(Collectors.groupingBy(History::getCustomer, Collectors.counting()));

        Map<Customer, Long> sortedCustomersByPurchaseCount = customerPurchaseCount.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        System.out.println("Customers by purchase count:");
        int count = 1;
        for (Map.Entry<Customer, Long> entry : sortedCustomersByPurchaseCount.entrySet()) {
            System.out.printf("%d. %s %s - Number of purchases: %d%n",
                    count++, entry.getKey().getFirstname(), entry.getKey().getLastname(), entry.getValue());
        }
    }

    public void printRankingOfProductsBeingSold() {
        List<History> histories = entityManager.createQuery("SELECT h FROM History h", History.class)
                .getResultList();

        Map<Product, Long> mapProducts = histories.stream()
                .collect(Collectors.groupingBy(History::getProduct, Collectors.counting()));

        Map<Product, Long> sortedMap = mapProducts.entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        System.out.println("Ranking of products being sold:");
        int n = 1;
        for (Map.Entry<Product, Long> entry : sortedMap.entrySet()) {
            System.out.printf("%d. %s - %d times%n",
                    n++, entry.getKey().getTitle(), entry.getValue());
        }
    }

    public void calculateTotalSales() {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT SUM(h.product.price) FROM History h WHERE h.returnProduct IS NULL", Integer.class);
        Integer totalSalesAmount = query.getSingleResult();

        TypedQuery<Long> query2 = entityManager.createQuery("SELECT COUNT(h) FROM History h WHERE h.returnProduct IS NULL", Long.class);
        Long totalSoldProducts = query2.getSingleResult();

        System.out.println("Total sales amount: " + totalSalesAmount);
        System.out.println("Total sold products: " + totalSoldProducts);
    }
}
