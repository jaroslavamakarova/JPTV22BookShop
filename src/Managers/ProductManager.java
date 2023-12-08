package Managers;

import Managers.AuthorManager;
import entity.Author;
import entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays; 
import tools.InputFromKeyboard;

public class ProductManager {

    private final EntityManager entityManager;
    private final Scanner scanner;

    public ProductManager(Scanner scanner, EntityManager entityManager) {
        this.scanner = scanner;
        this.entityManager = entityManager;
    }

       public void addProduct() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            System.out.println("----- Add new product -----");
            Product product = new Product();
            System.out.print("Enter title: ");
            product.setTitle(scanner.nextLine());
            System.out.print("Enter published year: ");
            product.setPublishedYear(Integer.parseInt(scanner.nextLine()));
            List<Author> authors = new ArrayList<>();
            do {
                System.out.println("If there are no authors in the list, press 0; if there are, press 1");
                int isAuthor = Integer.parseInt(scanner.nextLine());
                if (isAuthor == 0) {
                    // Создание автора с помощью AuthorManager
                    authorManager.createAuthor();
                } else {
                    break;
                }
            } while (true);
            System.out.print("Number of authors in the product: ");
            int countAuthors = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Choose a number to add to the product (author " + (i + 1) + "): ");
                long authorId = Long.parseLong(scanner.nextLine());
                Author author = authorManager.findAuthorById(authorId);
                authors.add(author);
            }
            product.setAuthors(authors);
            System.out.print("Enter quantity of copies: ");
            product.setQuantity(Integer.parseInt(scanner.nextLine()));
                   entityManager.persist(product);
            transaction.commit();
            System.out.println("Added product: " + product.toString());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    public void updateProducts(List<Product> products) {
        int productIndex = selectProductIndex(products);
        if (productIndex != -1) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                Product productToUpdate = products.get(productIndex);


            System.out.println("Enter new data for the product:");
            System.out.print("Title: ");
            productToUpdate.setTitle(scanner.nextLine());
            System.out.print("Published Year: ");
            productToUpdate.setPublishedYear(InputFromKeyboard.inputNumberFromRange(1800, 2050));

            System.out.print("Authors (separate authors with comma): ");
            String authorsInput = scanner.nextLine();
            String[] authorsArray = authorsInput.split(",");

            List<Author> authorsList = new ArrayList<>();
            for (String authorName : authorsArray) {
                String[] authorNames = authorName.trim().split(" ");
                if (authorNames.length == 2) {
                    Author author = new Author(authorNames[0], authorNames[1]);
                    authorsList.add(author);
                } else {
                    System.out.println("Invalid author format for: " + authorName);
                }
            }
            productToUpdate.setAuthors(authorsList);

            System.out.print("Quantity: ");
            productToUpdate.setQuantity(InputFromKeyboard.inputNumberFromRange(1, 10));

            System.out.print("Price: ");
            productToUpdate.setPrice(InputFromKeyboard.inputNumberFromRange(1, 145));

            productToUpdate.setCount(productToUpdate.getQuantity());
entityManager.merge(productToUpdate);
                transaction.commit();

                System.out.println("Product data updated successfully:");
                System.out.println(productToUpdate.toString());
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } else {
            System.out.println("Product not found.");
        }
    }
        
  private int selectProductIndex(List<Product> products) {
        int count = printListProducts(products);
        if (count > 0) {
            System.out.print("Enter the number of the product to update: ");
            int productNumber = scanner.nextInt();
            scanner.nextLine();
            if (productNumber >= 1 && productNumber <= count) {
                return productNumber - 1;
            }
        }
        return -1;
    }

 public int printListProducts(List<Product> products) {
        int count = 0;
        System.out.println("List products: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s. %d. Authors: %s. In stock: %d. Price: %d%n",
                    i + 1,
                    products.get(i).getTitle(),
                    products.get(i).getPublishedYear(),
                    getAuthorsAsString(products.get(i).getAuthors()),
                    products.get(i).getCount(),
                    products.get(i).getPrice()
            );
            count++;
        }
        return count;
    }

    private String getAuthorsAsString(List<Author> authors) {
        StringBuilder authorsString = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            authorsString.append(authors.get(i).getFirstname()).append(" ").append(authors.get(i).getLastname());
            if (i != authors.size() - 1) {
                authorsString.append(", ");
            }
        }
        return authorsString.toString();
    }

}
