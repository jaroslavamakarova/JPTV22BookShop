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
import static java.util.Map.entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputFromKeyboard;

/**
 *
 * @author Melnikov
 */
public class HistoryManager {

    private final Scanner scanner;
    private final CustomerManager CustomerManager;
    private final ProductManager ProductManager;
    private Integer countCustomersInList;

    public HistoryManager(Scanner scanner) {
        this.scanner = scanner;
        this.CustomerManager = new CustomerManager(scanner);
        this.ProductManager = new ProductManager(scanner);
        
    }
  
    public History sellProductToCustomer(List<Customer> customers, List<Product> products) {
        System.out.println("------------- Sell the Product to Customer ----------------");
        History history = new History();
        
        var countCustomersInList = CustomerManager.pirntListCustomers(customers);
        System.out.print("Enter number reader: ");
        int readerNumber = InputFromKeyboard.inputNumberFromRange(1, countCustomersInList);
        history.setCustomer(customers.get(readerNumber-1));

        int countProductsInList = ProductManager.pirntListProducts(products);
        System.out.print("Enter number book: ");
        int bookNumber = InputFromKeyboard.inputNumberFromRange(1, countProductsInList);
        if(products.get(bookNumber-1).getCount() > 0){
            history.setProduct(products.get(bookNumber-1));
            products.get(bookNumber-1).setCount(products.get(bookNumber-1).getCount()-1);
            history.setSellProductToCustomerDate(new GregorianCalendar().getTime());
            return history;
        }else{
            System.out.println("All products are sold ");
            return null;
        }
    }

    public void returnProduct(List<History> histories) {
        System.out.println("-------- Return product to shop ---------");
        
        if((this.printListSoldProduct(histories))<1){
            System.out.println("Not products");
            return;
        }
        System.out.print("Enter number product: ");
        int historyNumber = InputFromKeyboard.inputNumberFromRange(1, null);
        if(histories.get(historyNumber-1).getProduct().getCount() < histories.get(historyNumber-1).getProduct().getQuantity()){
            histories.get(historyNumber-1).setReturnProduct(new GregorianCalendar().getTime());
            histories.get(historyNumber-1).getProduct().setCount(histories.get(historyNumber-1).getProduct().getCount()+1);
            System.out.printf("Book \"%s\" returned%n",histories.get(historyNumber-1).getProduct().getTitle());
        }else{
            System.out.println("All books are already in stock"); 
        }
    }

   public int printListSoldProduct(List<History> histories) {
    int countSoldProduct = 0;
    System.out.println("List selling products:");

    for (int i = 0; i < histories.size(); i++) {
        if (histories.get(i).getReturnProduct() == null) {
            System.out.printf("%d. %s. reading %s %s%n",
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
       
        Map<Product,Integer> mapProducts = new HashMap<>();
        for (int i = 0; i < histories.size(); i++) {
            Product product = histories.get(i).getProduct();
            if(mapProducts.containsKey(product)){
                mapProducts.put(product,mapProducts.get(product) + 1);
            }else{
                mapProducts.put(product,1);
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
        System.out.println("Ranking of books being read:");
        int n=1;
        for (Map.Entry entry : sortedMap.entrySet()) {
            System.out.printf("%d. %s: %d%n",
                    n,
                    ((Product)entry.getKey()).getTitle(),
                    entry.getValue()
            );
            n++;
        }
    }

    
    
    
}