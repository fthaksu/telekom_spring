package com.works.java8New;

import java.util.List;

public class MainNew {
    // Stream Finis : 11.143
    // Paralel Stream Finis : 1515
    public static void main(String[] args) {

        Result r = new Result();
        List<Customer> ls = r.call(1000);

        long start = System.currentTimeMillis();
        ls.parallelStream()
        .filter( item -> item.getName().length() > 5  )
        .forEach( item -> {
            try {
                Thread.sleep(10);
            }catch (Exception ex){};
            System.out.println(item.getName());
        });
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println("Finis : " + between);


    }

}
