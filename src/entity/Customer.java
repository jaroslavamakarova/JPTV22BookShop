/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.Objects;
import java.io.Serializable;

public class Customer implements Serializable {
    private String firstname;
    private String lastname;
    private String phone;
    private int balance;  // Change to int for balance

    public Customer() {
    }

    public void addToBalance(int replenishAmount) {
        if (replenishAmount > 0) {
            this.balance += replenishAmount;
            System.out.println("Balance replenished. New balance: " + this.balance);
        } else {
            System.out.println("Invalid amount. Please provide a positive value.");
        }
    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + this.balance;
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return this.balance == other.balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
 @Override
    public String toString() {
        return "Customer " 
                + " " + firstname 
                + " " + lastname 
                + ", phone " + phone 
                + "balance: " + balance;
    }   
}