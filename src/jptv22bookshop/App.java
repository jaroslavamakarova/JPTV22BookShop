/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv22bookshop;

import Managers.CustomerManager;
import Managers.HistoryManager;
import Managers.ProductManager;
import Managers.SaveManager;
import entity.Customer;
import entity.History;
import entity.Product;
import java.util.List;
import java.util.Scanner;
import tools.InputFromKeyboard;

public class App {
    private final Scanner scanner;
    private List<Product> products;
    private List<Customer> customers;
    private List<History> histories;
    private ProductManager productManager;
    private CustomerManager customerManager;
    private HistoryManager historyManager;
    private SaveManager saveManager;

    public App() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new SaveManager();
        this.products = saveManager.loadProducts();
        this.customers = saveManager.loadCustomers();
        this.histories = saveManager.loadHistories();
        this.productManager = new ProductManager(scanner);
        this.customerManager = new CustomerManager(scanner);
        this.historyManager = new HistoryManager(scanner);
    }

    void run() {
        boolean repeat = true;
        System.out.println("------ Welcome to Book Shop -------");
        System.out.println("------- 'Book to the house' -------");
        do {
            System.out.println("List tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add Product");
            System.out.println("2. Add Customer");
            System.out.println("3. Print list products");
            System.out.println("4. Print list customers");
            System.out.println("5. Sell product to customer");
            System.out.println("6. Customers By Purchase Count");
            System.out.println("7. Ranking of products being sold");
            System.out.println("8. Top up balance");
            System.out.println("9. Store rating");
            System.out.print("Enter number task: ");
            int task = InputFromKeyboard.inputNumberFromRange(0, 10);
            System.out.printf("Selected task %d, continue? (y/n): ", task);
            String continueRun = InputFromKeyboard.inputSymbolYesOrNo();
            if (continueRun.equals("n")) {
                continue;
            }
            switch (task) {
                case 0:
                    repeat = false;
                    System.out.println("Bye bye!");
                    break;
                case 1:
                    products.add(productManager.addProduct());
                    saveManager.saveProducts(this.products);//save to file
                    break;
                case 2:
                    customers.add(customerManager.addCustomer());
                    saveManager.saveCustomers(customers);
                    break;
                case 3:
                    productManager.printListProducts(products);
                    boolean updateProducts = InputFromKeyboard.askForProductUpdate();
                    if (updateProducts) {
                        productManager.updateProducts(products);
                        saveManager.saveProducts(products);
                    }
                    break;
                case 4:
                    customerManager.printListCustomers(customers);
                    boolean updateCustomers = InputFromKeyboard.askForCustomerUpdate();
                    if (updateCustomers) {
                        customerManager.updateCustomers(customers);
                        saveManager.saveCustomers(customers);
                    }
                    break;
                case 5:
                    History history = historyManager.sellProductToCustomer(customers, products);
                    if (history != null) {
                        this.histories.add(history);
                        saveManager.saveHistories(histories);
                        customerManager.deductBalanceForProduct(history.getCustomer(), history.getProduct().getPrice());
                        saveManager.saveCustomers(customers);
                    }
                    break;
                case 6:
                    historyManager.printCustomersByPurchaseCount(histories, customers);
                    break;
                case 7:
                    historyManager.printRankingOfProductsBeingSold(this.histories);
                    break;
                case 8:
                    customerManager.printListCustomers(customers);
                    System.out.print("Enter the number of the customer to replenish balance: ");
                    int customerNumber = InputFromKeyboard.inputNumberFromRange(1, customers.size());
                    Customer selectedCustomer = customers.get(customerNumber - 1);
                    customerManager.replenishBalance(selectedCustomer);
                    saveManager.saveCustomers(customers);
                    break;
                case 9:
                    historyManager.calculateTotalSales(histories);
                    break;
                default:
                    System.out.println("Select number from list tasks!");
            }
            System.out.println("-------------------------");
        } while (repeat);
    }
}