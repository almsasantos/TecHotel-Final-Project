package com.ironhack.edgeservice.model.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money implements Transactional{
    private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
    private final Currency currency;
    private BigDecimal balance;
    /**
     * Class constructor specifying amount, currency, and rounding
     **/
    public Money(BigDecimal balance, Currency currency, RoundingMode rounding) {
        this.currency = currency;
        setAmount(balance.setScale(currency.getDefaultFractionDigits(), rounding));
    }
    /**
     * Class constructor specifying amount, and currency. Uses default RoundingMode HALF_EVEN.
     **/
    public Money(BigDecimal balance, Currency currency) {
        this(balance, currency, DEFAULT_ROUNDING);
    }
    /**
     * Class constructor specifying amount. Uses default RoundingMode HALF_EVEN and default currency USD.
     **/
    public Money(BigDecimal balance) {
        this(balance, USD, DEFAULT_ROUNDING);
    }

    /**
     * Void constructor
     */
    public Money() {
        this.currency = USD;
    }

    /**
     * Method which will increase the amount passed
     * @param money receives money from type Money
     * @return balance from type BigDecimal
     */
    public BigDecimal increaseAmount(Money money) {
        setAmount(this.balance.add(money.balance));
        return this.balance;
    }

    /**
     * Method which will increase the amount passed
     * @param addAmount receives a BigDecimal value
     * @return a BigDecimal value
     */
    public BigDecimal increaseAmount(BigDecimal addAmount) {
        setAmount(this.balance.add(addAmount));
        return this.balance;
    }

    /**
     * Method which will decrease the amount passed
     * @param money receives money from type Money
     * @return a BigDecimal value
     */
    public BigDecimal decreaseAmount(Money money) {
        setAmount(this.balance.subtract(money.getAmount()));
        return this.balance;
    }

    /**
     * Method which will decrease the amount passed
     * @param addAmount a BigDecimal value
     * @return a BigDecimal value
     */
    public BigDecimal decreaseAmount(BigDecimal addAmount) {
        setAmount(this.balance.subtract(addAmount));
        return this.balance;
    }

    /**
     * Getter from Currency
     * @return the Currency
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * Getter from Amount
     * @return returns the balance from type BigDecimal
     */
    public BigDecimal getAmount() {
        return this.balance;
    }

    /**
     * Setter from Amount
     * @param amount receives an amount from type BigDecimal
     */
    private void setAmount(BigDecimal amount) {
        this.balance = amount;
    }

    /**
     * String method
     * @return a string
     */
    public String toString() {
        return getCurrency().getSymbol() + " " + getAmount();
    }

    /**
     * Method that overrides method equals
     * @param o receives an object
     * @return a boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(currency, money.currency) &&
                Objects.equals(balance, money.balance);
    }
}
