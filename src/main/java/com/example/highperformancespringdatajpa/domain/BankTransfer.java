package com.example.highperformancespringdatajpa.domain;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class BankTransfer {
    
    public enum State {
        CREATED,
        SETTLED
    }

    @Id
    private String id;

    @Version
    private Long version;

    private String reference;

    @ManyToOne(fetch= FetchType.LAZY)
    private Account sender;

    @ManyToOne(fetch= FetchType.LAZY)
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
