package com.example.highperformancespringdatajpa.connections;

import org.springframework.stereotype.Service;

@Service
public class ExternalService {
    public void externalCall(){
        System.out.println("I'm external call sleeping for 200 ms");
        Sleep.sleep(200);
    }
}
