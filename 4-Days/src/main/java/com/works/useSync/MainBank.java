package com.works.useSync;

public class MainBank {

    public static void main(String[] args) {

        Customer customer = new Customer(1000);

        Bank b1 = new Bank(customer, 100, "Bank - 1");
        Bank b2 = new Bank(customer, 50, "Bank - 2");
        Bank b3 = new Bank(customer, 150, "Bank - 3");
        Bank b4 = new Bank(customer, 100, "Bank - 4");
        Bank b5 = new Bank(customer, 200, "Bank - 5");

        b1.start();
        b2.start();
        b3.start();
        b4.start();
        b5.start();


    }

}
