package com.ironhack.userservice.model.entities;

import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.enums.TypeOfUser;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Table with Basic Users
 */
@Entity
@Table(name = "basic_user")
@PrimaryKeyJoinColumn(name = "id")
public class Basic extends User {
    /**
     * Attribute phoneNumber from type String
     */
    @Pattern(regexp = "^\\d{9}$", message = "Please enter a valid phone number.")
    private String phoneNumber;

    /**
     * Attribute email from type String
     */
    @NotNull
    private String email;

    /**
     * Attribute birthDate from type LocalDate
     */
    @NotNull
    private LocalDate birthDate;

    /**
     * Attribute address from type Address
     */
    @NotNull
    private Address address;

    /**
     * Attribute bankAccount from type Account
     */
    @NotNull
    @Embedded
    private Account bankAccount;

    /**
     * Attribute registrationDate from type LocalDateTime
     */
    private LocalDateTime registrationDate;

    /**
     * Attribute numberOfStays from type Integer
     */
    private Integer numberOfStays;

    /**
     * Attribute roomId from type Integer
     */
    private Integer roomId;

    /**
     * Empty Basic's Constructor
     */
    public Basic() {
        this.typeOfUser = TypeOfUser.BASIC;
        this.registrationDate = LocalDateTime.now();
        this.numberOfStays = 0;
    }

    /**
     * Basic's Constructor
     * @param name receives a String with name
     * @param username receives a String with username
     * @param password receives a String with password
     * @param phoneNumber receives a String with phoneNumber
     * @param email receives a String with email
     * @param birthDate receives a LocalDate with birthDate
     * @param address receives a Address with address
     * @param bankAccount receives a Account with bankAccount
     */
    public Basic(String name, String username, String password, String phoneNumber, String email, LocalDate birthDate, Address address, Account bankAccount) {
        super(name, username, password);
        this.typeOfUser = TypeOfUser.BASIC;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.bankAccount = bankAccount;
        this.registrationDate = LocalDateTime.now();
        this.numberOfStays = 0;
        this.roomId = null;
    }

    /**
     * Getter of PhoneNumber
     * @return a String with phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter of PhoneNumber
     * @param phoneNumber receives a String with phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter of Email
     * @return a String with email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of Email
     * @param email receives a String with email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of BirthDate
     * @return a LocalDate with birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Setter of BirthDate
     * @param birthDate receives a LocalDate with birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter of Address
     * @return an Address with address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter of Address
     * @param address receives an Address with address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Getter of bankAccount
     * @return an Account with bankAccount
     */
    public Account getBankAccount() {
        return bankAccount;
    }

    /**
     * Setter of bankAccount
     * @param bankAccount receives an Account with bankAccount
     */
    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * Getter of registrationDate
     * @return a LocalDateTime with registrationDate
     */
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Getter of numberOfStays
     * @return an Integer with numberOfStays
     */
    public Integer getNumberOfStays() {
        return numberOfStays;
    }

    /**
     * Setter of numberOfStays
     * @param numberOfStays receives an Integer with numberOfStays
     */
    public void setNumberOfStays(Integer numberOfStays) {
        this.numberOfStays = numberOfStays;
    }

    /**
     * Getter of roomId
     * @return an Integer with roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter of roomId
     * @param roomId receives an Integer with roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
