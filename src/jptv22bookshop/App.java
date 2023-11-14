/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
/**
 *
 * @author user
 */
public class App {
    private final Scanner scanner;
    private List<Product> products;
    private List<Customer> customer;
    private List<History> histories;
    private ProductManager ProductManager;
    private CustomerManager CustomerManager;
    private HistoryManager historyManager;
    private SaveManager saveManager;
    
    public App() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new SaveManager();
        this.products = saveManager.loadProducts();
        this.customer = saveManager.loadCustomers();
        this.histories = saveManager.loadHistories();
        this.ProductManager = new ProductManager(scanner);
        this.CustomerManager = new CustomerManager(scanner);
        this.historyManager = new HistoryManager(scanner);
    }
    void run() {
        boolean repeat = true;
        System.out.println("------ Welcome to Book Shop ------");
        System.out.println("-------- 'Book in Home' -------");
        do{
            System.out.println("List tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Print list products");
            System.out.println("2. Print list customers");
            System.out.println("3. Sell product to customer");
            System.out.println("4. Return product");
            System.out.println("5. Print selling products ");
            System.out.println("6. Ranking of products being sold");
            System.out.println("7. Top up balance");
            System.out.print("Enter number task: ");
            int task = InputFromKeyboard.inputNumberFromRange(0,9);
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 8:
                    products.add(ProductManager.addProduct());
                    saveManager.saveProducts(this.products);//save to file
                    break;
                case 9:
                    customer.add(CustomerManager.addCustomer());
                    saveManager.saveCustomers(customer);
                    break;
                case 1:
                    ProductManager.printListProducts(products);
                    break;
               case 2:
                       CustomerManager.printListCustomers(customer);
                        break;
                case 3:
                    History history = historyManager.sellProductToCustomer(customer, products);
                    if(history != null){
                        this.histories.add(history);
                        saveManager.saveHistories(histories);
                    }
                    break;
                case 4:
                    historyManager.returnProduct(histories);
                    saveManager.saveHistories(histories);
                    break;
                case 5:
                    historyManager.printListSoldProduct(histories);
                    break;
                case 6:
                    //System.out.println("Implementation expected");
                    historyManager.printRankingOfProductsBeingSold(this.histories);
                    break;
                case 7:
                    System.out.println("Top up your balance");
                    historyManager.processTransaction(histories, repeat);
                    break;
                default:
                    System.out.println("Select number from list tasks!");
            }
            System.out.println("-------------------------");
        }while(repeat);
    }

    
}
