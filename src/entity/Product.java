package entity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
/*
/**
 *
 * @author user
 */
public class Product implements Serializable{
    private String title;
    private int publishedYear;
    private Author[] authors = new Author[0];
    private int quantity; //Всего закупленных в библиотеку экземпляров
    private int count; //экземпляров в наличии.
    private int price;
    public Product() {
    }

    public Product(String title, int publishedYear, int quantity, int count, int price) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.quantity = quantity;
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "Product " 
                + "Book  " + title 
                + ", " + publishedYear 
                + ", author/s" + Arrays.toString(authors) 
                + ", quantity " + quantity
                + ", count " + count
                +  " price" + price;
    }

    public void addAuthor(Author author) {
        this.authors = Arrays.copyOf(this.authors, this.authors.length + 1);
        this.authors[this.authors.length-1] = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + this.publishedYear;
        hash = 41 * hash + Arrays.deepHashCode(this.authors);
        hash = 41 * hash + this.quantity;
        hash = 41 * hash + this.count;
        hash = 41 * hash + this.price;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.publishedYear != other.publishedYear) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Arrays.deepEquals(this.authors, other.authors);
    }
}
