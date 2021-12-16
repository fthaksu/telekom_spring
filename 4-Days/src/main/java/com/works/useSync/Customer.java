package com.works.useSync;

public class Customer {

    private int total;

    public Customer(int total) {
        this.total = total;
    }

    synchronized
    public void minus(int size, String status) {
        total = total - size;
        System.out.println(status + " - " + size + " - " + total );
    }

}
