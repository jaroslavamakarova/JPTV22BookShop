/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    private final Scanner scanner;
    private final EntityManager entityManager;

    public CustomerManager(Scanner scanner, EntityManager entityManager) {
        this.scanner = scanner;
        this.entityManager = entityManager;
    }

    public Customer addCustomer() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        System.out.println(" ----- Add new customer -----");
        Customer customer = new Customer();
        System.out.print("Firstname: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Lastname: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Phone: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Initial Balance: ");
        int initialBalance = Integer.parseInt(scanner.nextLine());
        customer.setBalance(initialBalance);

        entityManager.persist(customer);
        transaction.commit();

        System.out.println("Added customer " + customer.toString());
        return customer;
    }

    public void viewBalance(Customer customer) {
        System.out.println("Customer Balance:");
        System.out.println(customer.toString());
    }

    public void replenishBalance(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        System.out.print("Enter the amount to replenish: ");
        int replenishAmount = Integer.parseInt(scanner.nextLine());

        int currentBalance = customer.getBalance();
        int newBalance = currentBalance + replenishAmount;
        customer.setBalance(newBalance);

        entityManager.merge(customer);
        transaction.commit();

        System.out.println("Balance replenished successfully.");
        System.out.println("New balance for " + customer.getFirstname() + ": " + newBalance);
    }
    
      public void updateCustomer(Customer customerToUpdate) {
        try {
            entityManager.getTransaction().begin();

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

            entityManager.merge(customerToUpdate);
            entityManager.getTransaction().commit();

            System.out.println("Customer data updated successfully:");
            System.out.println(customerToUpdate.toString());
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("Error occurred while updating customer data.");
        }
    }

    public void deductBalanceForProduct(Customer customer, int productPrice) {
        try {
            entityManager.getTransaction().begin();

            if (customer.getBalance() >= productPrice) {
                int newBalance = customer.getBalance() - productPrice;
                customer.setBalance(newBalance);
                entityManager.merge(customer);

                entityManager.getTransaction().commit();

                System.out.println("Price deducted successfully.");
                System.out.println("New balance for " + customer.getFirstname() + ": " + newBalance);
            } else {
                System.out.println("Insufficient funds. Unable to deduct price.");
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("Error occurred while deducting balance for the product.");
        }
    }

    public int printListCustomers() {
        List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        System.out.println("List customers: ");
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.printf("%d. %s %s - Phone: %s, Balance: %d%n",
                    i + 1,
                    customer.getFirstname(),
                    customer.getLastname(),
                    customer.getPhone(),
                    customer.getBalance()
            );
        }
        return customers.size();
    }
}

