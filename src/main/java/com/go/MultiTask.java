package com.go;

public class MultiTask extends Thread {

    public void run() {
        try {
            Task task = new Task();
            // Displaying the thread that is running
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running");
            task.readTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses();

        } catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}

