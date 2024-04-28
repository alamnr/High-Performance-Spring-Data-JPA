package com.example.highperformancespringdatajpa.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Account {

    @Id
    private String id;

    private String iban;
    private String firstName;
    private String lastName;

    @ElementCollection
    @CollectionTable(name="phone_number", joinColumns=@JoinColumn(name="account_id"))
    private List<PhoneNumber> phoneNumbers = new ArrayList<>() ;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name="city", column=@Column(name="home_city")),@AttributeOverride(name="street",column=@Column(name="home_street")),@AttributeOverride(name="zipCode",column=@Column(name="home_zipcode"))})
    private Address address;

    public Account(String id, String iban, String firstName, String lastName){
        this.id = id;
        this.iban = iban;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber){
        if(!phoneNumbers.contains(phoneNumber)){
            this.phoneNumbers.add(phoneNumber);
        }
    }

    public void addAddress(Address address){
        this.address = address;
    }

}
