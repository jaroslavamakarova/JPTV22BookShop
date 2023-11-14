
package Managers;

import entity.Author;
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
        
        // Set basic product information
        System.out.print("Enter title: ");
        product.setTitle(scanner.nextLine());
        System.out.print("Enter published year: ");
        product.setPublishedYear(InputFromKeyboard.inputNumberFromRange(1800, 2050));
        
        // Set authors
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
        
        // Set quantity copy and price
        System.out.print("Enter quantity copy: ");
        product.setQuantity(InputFromKeyboard.inputNumberFromRange(1, 10));

        System.out.print("Enter price: ");
        product.setPrice(InputFromKeyboard.inputNumberFromRange(1, 145));

        product.setCount(product.getQuantity());
        System.out.println("Added product: " + product.toString());
        return product;
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
public void CopyOfAnExistingBookInTheShop(List<Product> products) {
        this.printListProducts(products);
        System.out.println("Enter the book number to add copies: "); 
        int bookNumber = InputFromKeyboard.inputNumberFromRange(1,products.size());
        System.out.println("How many copies of the book should I add?: "); 
        int copyNumber = InputFromKeyboard.inputNumberFromRange(1,10);
        products.get(bookNumber - 1).setQuantity(products.get(bookNumber - 1)
                .getQuantity() + copyNumber);
         products.get(bookNumber - 1).setCount(products.get(bookNumber - 1)
                .getCount() + copyNumber);
    }  
}
