package com.works.useThread;

public class MainUseThread {

    public static void main(String[] args) {

        // java 8 Thread
        Runnable rn = () -> {
            Action ac1 = new Action("image-1");
            Action ac2 = new Action("image-2");
            Action ac3 = new Action("image-3");
            Action ac4 = new Action("image-4");
            Action ac5 = new Action("image-5");

            try {
                ac1.start();
                ac1.join();

                ac2.start();
                ac2.join();

                ac3.start();
                ac3.join();

                ac4.start();
                ac4.join();

                ac5.start();
                ac5.join();
            }catch (Exception ex) {};

        };
        new Thread(rn).start();

        System.out.println("This Line Call");

    }

}
