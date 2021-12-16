package com.works.useThread;

public class Action extends Thread {

    String path = "";
    public Action( String path ) {
        this.path = path;
    }

    @Override
    public void run() {

        try {
            System.out.println("Thread Start" + path);
            Thread.sleep(1000);
            System.out.println("Thread Finish" + path);
        }catch (Exception ex) {
            System.err.println("Action Error " + ex);
        }

    }
}
