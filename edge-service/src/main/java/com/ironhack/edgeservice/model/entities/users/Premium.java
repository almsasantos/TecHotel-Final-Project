package com.ironhack.edgeservice.model.entities.users;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.Address;
import com.ironhack.edgeservice.model.enums.TypeOfUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Premium extends User {
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Address address;
    private Account bankAccount;
    private LocalDateTime registrationDate;
    private Integer numberOfStays;
    private Integer roomId;

    public Premium() {
        this.typeOfUser = TypeOfUser.PREMIUM;
        this.registrationDate = LocalDateTime.now();
        this.numberOfStays = 0;
    }

    public Premium(String name, String username, String password, String phoneNumber, String email, LocalDate birthDate, Address address, Account bankAccount) {
        super(name, username, password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.bankAccount = bankAccount;
        this.registrationDate = LocalDateTime.now();
        this.numberOfStays = 0;
        this.roomId = null;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getNumberOfStays() {
        return numberOfStays;
    }

    public void setNumberOfStays(Integer numberOfStays) {
        this.numberOfStays = numberOfStays;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
