/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import entity.Product;
import entity.History;
import entity.Customer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private final String PRODUCT_FILENAME = "books";
    private final String CUSTOMER_FILENAME = "readers";
    private final String HISTORY_FILENAME = "histories";
    public void saveProducts(List<Product> products){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(PRODUCT_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        }   
    }
    public List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(PRODUCT_FILENAME);
            ois = new ObjectInputStream(fis);
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
        return products;
    }
    public void saveCustomers(List<Customer> readers){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(CUSTOMER_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        }
        
    }
    public List<Customer> loadCustomers(){
        List<Customer> customers = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(CUSTOMER_FILENAME);
            ois = new ObjectInputStream(fis);
            customers = (List<Customer>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
        return customers;
    }
    public void saveHistories(List<History> histories){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(HISTORY_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        }
        
    }
    public List<History> loadHistories(){
        List<History> histories = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(HISTORY_FILENAME);
            ois = new ObjectInputStream(fis);
            histories = (List<History>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount");
        } catch (IOException ex) {
            System.out.println("I/O error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
        return histories;
    }
}
