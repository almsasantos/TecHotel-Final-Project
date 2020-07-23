package com.ironhack.reservationservice.model.classes;

import javax.persistence.Embeddable;

/**
 * Class Account which will be embeddable in Basic and Premium users
 */
@Embeddable
public class Account {
    /**
     * Attribute balance from class Money
     */
    private Money balance;

    /**
     * Empty Account's Constructor
     */
    public Account() {}

    /**
     * Account's Constructor
     * @param balance receives a balance which is from type Money
     */
    public Account(Money balance) {
        this.balance = balance;
    }

    /**
     * Getter for Balance
     * @return balance from type Money
     */
    public Money getBalance() {
        return balance;
    }

    /**
     * Setter for Balance
     * @param balance receives balance from type Money
     */
    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
