/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import entity.Customer;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author user
 */
import java.util.List;
import java.util.Scanner;

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
        int replenishAmount = scanner.nextInt();
        customer.addToBalance(replenishAmount);
    }
    int pirntListCustomers(List<Customer> customers) {
      int count = 0;
        System.out.println("List customers: ");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s %s - Phone: %s, Balance: %d%n",
                    i + 1,
                    customers.get(i).getFirstname(),
                    customers.get(i).getLastname(),
                    customers.get(i).getPhone(),
                    customers.get(i).getBalance()
            );
            count++;
        }
        return count;
    }
}

