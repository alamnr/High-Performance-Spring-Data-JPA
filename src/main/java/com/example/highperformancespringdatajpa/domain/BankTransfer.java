package com.example.highperformancespringdatajpa.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class BankTransfer {
    
    public enum State {
        CREATED,
        SETTLED
    }

    @Id
    private String id;

    private String reference;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;

    @Embedded
    private Amount amount;

    @Enumerated(EnumType.STRING)
    private State state;


    public BankTransfer(String id , String reference, Account sender, Account receiver, Amount amount){
        this.id = id;
        this.reference = reference;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.state = State.CREATED;
    }
    public void settle() {
        this.state = State.SETTLED;
    }

    
}
