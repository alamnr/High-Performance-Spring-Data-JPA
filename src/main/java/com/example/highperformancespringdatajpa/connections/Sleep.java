package com.example.highperformancespringdatajpa.connections;

public class Sleep {
    static void sleep(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}