/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author user
 */
public class History implements Serializable{
    private Product product;
    private Customer customer;
    private Date sellProducttoCustomerDate;
    private Date returnProduct;
 public History() {
    }

    public History(Product product, Customer customer, Date sellProducttoCustomerDate, Date returnProduct) {
        this.product = product;
        this.customer = customer;
        this.sellProducttoCustomerDate = sellProducttoCustomerDate;
        this.returnProduct = returnProduct;
    }

    public Date getReturnProduct() {
        return returnProduct;
    }

    public void setReturnProduct(Date returnProduct) {
        this.returnProduct = returnProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getSellProductToCustomerDate() {
        return sellProducttoCustomerDate;
    }

    public void setSellProductToCustomerDate(Date sellProducttoCustomerDate) {
        this.sellProducttoCustomerDate = sellProducttoCustomerDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.product);
        hash = 17 * hash + Objects.hashCode(this.customer);
        hash = 17 * hash + Objects.hashCode(this.sellProducttoCustomerDate);
        hash = 17 * hash + Objects.hashCode(this.returnProduct);
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
        final History other = (History) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.sellProducttoCustomerDate, other.sellProducttoCustomerDate)) {
            return false;
        }
        if (!Objects.equals(this.returnProduct, other.returnProduct)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "History " 
                + "Product " + product.getTitle()
                + ", Customer" + customer.getFirstname()
                + " " + customer.getLastname()
                + ", Sold Product" + sellProducttoCustomerDate 
                + ", Return product " + returnProduct 
                + ' ';
    }
}  
