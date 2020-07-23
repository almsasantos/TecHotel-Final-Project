package com.ironhack.edgeservice.model.entities.users;

import com.ironhack.edgeservice.model.enums.TypeOfUser;

public abstract class User {
    /**
     * Attribute id from type Long
     */
    protected Long id;

    /**
     * Attribute name from type String
     */
    protected String name;

    /**
     * Attribute username from type String
     */
    protected String username;

    /**
     * Attribute password from type String
     */
    protected String password;

    /**
     * Attribute typeOfUser from type TypeOfUser
     */
    protected TypeOfUser typeOfUser;

    /**
     * Empty User's Constructor
     */
    public User() {}

    /**
     * User's Constructor
     * @param name receives a String with name
     * @param username receives a String with username
     * @param password receives a String with password
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter of Id
     * @return a Long with id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter of Id
     * @param id receives a Long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter of Name
     * @return a String with name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of Name
     * @param name receives a String with name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of Username
     * @return a String with username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of Username
     * @param username receives a String with username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter of Password
     * @return a String with password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of Password
     * @param password receives a String with password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter of TypeOfUser
     * @return a typeOfUser from enum TypeOfUser
     */
    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    /**
     * Setter of TypeOfUser
     * @param typeOfUser receives a enum TypeOfUser
     */
    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }
}
