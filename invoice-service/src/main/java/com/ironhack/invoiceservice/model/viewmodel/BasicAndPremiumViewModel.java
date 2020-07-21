package com.ironhack.invoiceservice.model.viewmodel;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * View Model to create Basic and Premium Users
 */
public class BasicAndPremiumViewModel {
    /**
     * Attribute name from type String
     */
    private String name;
    /**
     * Attribute username from type String
     */
    private String username;
    /**
     * Attribute password from type String
     */
    private String password;
    /**
     * Attribute phoneNumber from type String
     */
    private String phoneNumber;
    /**
     * Attribute email from type String
     */
    private String email;
    /**
     * Attribute birthDate from type LocalDate
     */
    private LocalDate birthDate;
    /**
     * Attribute country from type String
     */
    private String country;
    /**
     * Attribute city from type String
     */
    private String city;
    /**
     * Attribute street from type String
     */
    private String street;
    /**
     * Attribute postalCode from type String
     */
    private String postalCode;
    /**
     * Attribute balance from type BigDecimal
     */
    private BigDecimal balance;

    /**
     * Getter of name
     * @return a String with name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name receives a String with name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of username
     * @return a String with username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of username
     * @param username receives a String with username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter of password
     * @return a String with password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password
     * @param password receives a String with password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter of phoneNumber
     * @return a String with phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter of phoneNumber
     * @param phoneNumber receives a String with phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter of email
     * @return a String with email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email
     * @param email receives a String with email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of birthDate
     * @return a LocalDate with birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Setter of birthDate
     * @param birthDate receives a LocalDate with birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter of country
     * @return a String with country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter of country
     * @param country receives a String with country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter of city
     * @return a String with city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter of city
     * @param city receives a String with city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter of street
     * @return a String with street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter of street
     * @param street receives a String with street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter of postalCode
     * @return a String with postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter of postalCode
     * @param postalCode receives a String with postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter of balance
     * @return a BigDecimal with balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Setter of balance
     * @param balance receives a BigDecimal with balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
