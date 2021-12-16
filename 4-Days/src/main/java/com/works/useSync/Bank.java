package com.works.useSync;

public class Bank extends Thread{

    Customer customer;
    int size;
    String status;
    public Bank(Customer customer, int size, String status) {
        this.customer = customer;
        this.size = size;
        this.status = status;
    }


    @Override
    public void run() {
        customer.minus(size, status);
    }


}
