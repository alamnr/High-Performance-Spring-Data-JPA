package com.example.highperformancespringdatajpa.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String street, String city, String zipCode) {
    
}
