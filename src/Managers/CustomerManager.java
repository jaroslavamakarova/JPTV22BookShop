/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import entity.Customer;
import java.util.List;
import java.util.Scanner;
import tools.InputFromKeyboard;
/**
 *
 * @author user
 */
public class CustomerManager {

    private final Scanner scanner;

    public CustomerManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public Customer addCustomer() {
        Customer customer;
        customer = new Customer();
        System.out.println(" ----- Add new customer -----");
        System.out.print("Firstname: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Lastname: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Phone: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Initial Balance: ");
        int initialBalance = scanner.nextInt();
        customer.setBalance(initialBalance);
        System.out.println("Added customer " + customer.toString());
        return customer;
    }

    public void viewBalance(Customer customer) {
        System.out.println("Customer Balance:");
        System.out.println(customer.toString());
    }

public void replenishBalance(Customer customer) {
    System.out.print("Enter the amount to replenish: ");
    int replenishAmount = InputFromKeyboard.inputNumberFromRange(1, Integer.MAX_VALUE);
    
    int currentBalance = customer.getBalance();
    int newBalance = currentBalance + replenishAmount;
    customer.setBalance(newBalance);

    System.out.println("Balance replenished successfully.");
    System.out.println("New balance for " + customer.getFirstname() + ": " + newBalance);
}
    public int printListCustomers(List<Customer> customer) {
        
    int count = 0;
        System.out.println("List customers: ");
        for (int i = 0; i < customer.size(); i++) {
            System.out.printf("%d. %s %s - Phone: %s, Balance: %d%n",
                    i + 1,
                    customer.get(i).getFirstname(),
                    customer.get(i).getLastname(),
                    customer.get(i).getPhone(),
                    customer.get(i).getBalance()
            );
            count++;
        }
        return count;
    
}
    public void updateCustomers(List<Customer> customers) {
        int customerIndex = selectCustomerIndex(customers);
        if (customerIndex != -1) {
            Customer customerToUpdate = customers.get(customerIndex);

            System.out.println("Enter new data for the customer:");
            System.out.print("Firstname: ");
            customerToUpdate.setFirstname(scanner.nextLine());
            System.out.print("Lastname: ");
            customerToUpdate.setLastname(scanner.nextLine());
            System.out.print("Phone: ");
            customerToUpdate.setPhone(scanner.nextLine());
            System.out.print("Initial Balance: ");
            int initialBalance = scanner.nextInt();
            customerToUpdate.setBalance(initialBalance);

            System.out.println("Customer data updated successfully:");
            System.out.println(customerToUpdate.toString());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private int selectCustomerIndex(List<Customer> customers) {
        int count = printListCustomers(customers);
        if (count > 0) {
            System.out.print("Enter the number of the customer to update: ");
            int customerNumber = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            if (customerNumber >= 1 && customerNumber <= count) {
                return customerNumber - 1; // adjusting index for the selected customer
            }
        }
        return -1; // customer not found or invalid input
    }
}

