package com.works.completable;

import java.util.concurrent.CompletableFuture;

public class Settings {

    CompletableFuture<Void> cf1 = CompletableFuture.runAsync( ()-> {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("cf1 : " + i);
            }catch (Exception ex) {}
        }
    } );


    CompletableFuture<Void> cf2 = CompletableFuture.runAsync( ()-> {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("cf2 : " + i);
            }catch (Exception ex) {}
        }
    } );


    CompletableFuture<Void> cf3 = CompletableFuture.runAsync( ()-> {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("cf3 : " + i);
            }catch (Exception ex) {}
        }
    } );


    public void call() {

        CompletableFuture all = CompletableFuture.allOf(cf1, cf2, cf3);
        Object r = all.join();
        System.out.println("Return : " + r);
    }


    public static void main(String[] args) {
        Settings s = new Settings();
        s.call();
    }

}
