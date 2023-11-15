
package Managers;

import entity.Author;
import entity.Customer;
import entity.Product;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import tools.InputFromKeyboard;


/**
 *
 * @author user
 */
public class ProductManager {

    private final Scanner scanner;

    public ProductManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public Product addProduct() {
        System.out.println("----- Add new product -----");
        Product product = new Product();
        
        System.out.print("Enter title: ");
        product.setTitle(scanner.nextLine());
        System.out.print("Enter published year: ");
        product.setPublishedYear(InputFromKeyboard.inputNumberFromRange(1800, 2050));

        System.out.print("How many authors: ");
        int countAuthors = InputFromKeyboard.inputNumberFromRange(1, 5);
        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("Author %d:%n", i + 1);
            System.out.print("Enter firstname: ");
            String authorFirstname = scanner.nextLine();
            System.out.print("Enter lastname: ");
            String authorLastname = scanner.nextLine();
            product.addAuthor(new Author(authorFirstname, authorLastname));
        }

        System.out.print("Enter quantity copy: ");
        product.setQuantity(InputFromKeyboard.inputNumberFromRange(1, 10));

        System.out.print("Enter price: ");
        product.setPrice(InputFromKeyboard.inputNumberFromRange(1, 145));

        product.setCount(product.getQuantity());
        System.out.println("Added product: " + product.toString());
        return product;
    }  
public void updateProducts(List<Product> products) {
        int productIndex = selectProductIndex(products);
        if (productIndex != -1) {
            Product productToUpdate = products.get(productIndex);

            System.out.println("Enter new data for the product:");
            System.out.print("Title: ");
            productToUpdate.setTitle(scanner.nextLine());
            System.out.print("Published Year: ");
            productToUpdate.setPublishedYear(InputFromKeyboard.inputNumberFromRange(1800, 2050));

            System.out.print("Authors (separate authors with comma): ");
            String authorsInput = scanner.nextLine();
            String[] authorsArray = authorsInput.split(","); 
            
            Author[] authors = new Author[authorsArray.length]; 
            for (int i = 0; i < authorsArray.length; i++) {
                String[] authorNames = authorsArray[i].trim().split(" ");
                if (authorNames.length == 2) {
                    authors[i] = new Author(authorNames[0], authorNames[1]);
                } else {
                    System.out.println("Invalid author format for: " + authorsArray[i]);
                }
            }

            productToUpdate.setAuthors(authors);
            
            System.out.print("Quantity: ");
            productToUpdate.setQuantity(InputFromKeyboard.inputNumberFromRange(1, 10));

            System.out.print("Price: ");
            productToUpdate.setPrice(InputFromKeyboard.inputNumberFromRange(1, 145));

            productToUpdate.setCount(productToUpdate.getQuantity());

            System.out.println("Product data updated successfully:");
            System.out.println(productToUpdate.toString());
        } else {
            System.out.println("Product not found.");
        }
    }


    private int selectProductIndex(List<Product> products) {
        int count = printListProducts(products);
        if (count > 0) {
            System.out.print("Enter the number of the product to update: ");
            int productNumber = scanner.nextInt();
            scanner.nextLine(); // очистить буфер

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
            System.out.printf("%d. %s. %d. %s. In stock: %d.  Price: %d%n",
                    i + 1,
                    products.get(i).getTitle(),
                    products.get(i).getPublishedYear(),
                    Arrays.toString(products.get(i).getAuthors()),
                    products.get(i).getCount(),
                    products.get(i).getPrice()
            );
            count++;
        }
        return count;
    }
}
