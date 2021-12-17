package com.works.java8New;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Result {

    public List<Customer> call( int count ) {
        List<Customer> ls = new ArrayList<>();
        for ( int i = 0; i<count; i++ ) {
            Customer c = new Customer();
            c.setId(i);
            c.setName("kaan"+i);
            Random r = new Random();
            c.setStatus(r);
            ls.add(c);
        }
        return ls;
    }

}
