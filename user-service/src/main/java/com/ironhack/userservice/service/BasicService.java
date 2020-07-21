package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.repository.BasicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service of Basic
 */
@Service
public class BasicService {

    /**
     * Autowired of Basic Repository
     */
    @Autowired
    private BasicRepository basicRepository;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(BasicService.class);

    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    public List<Basic> findAll(){
        LOGGER.info("Find All Basic Clients");
        return basicRepository.findAll();
    }

    /**
     * Find Basic by id
     * @param id receives a Long Basic's id
     * @return a Basic user
     */
    public Basic findById(Long id){
        LOGGER.info("Find Basic Client with id " + id);
        return basicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Basic user id not found"));
    }

    /**
     * Create a Basic User
     * @param basicViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    public Basic createBasicUser(BasicAndPremiumViewModel basicViewModel) {
        LOGGER.info("[INIT] Create new Basic Client");

        LOGGER.info("Check if username already exists");
        restrictUsername(basicViewModel.getUsername());

        Basic basic = new Basic();
        basic.setName(basicViewModel.getName());
        basic.setUsername(basicViewModel.getUsername());
        basic.setPassword(basicViewModel.getPassword());
        basic.setPhoneNumber(basicViewModel.getPhoneNumber());
        basic.setEmail(basicViewModel.getEmail());
        basic.setBirthDate(basicViewModel.getBirthDate());

        Address address = new Address(basicViewModel.getCountry(), basicViewModel.getCity(),
                                        basicViewModel.getStreet(), basicViewModel.getPostalCode());

        basic.setAddress(address);

        Account account = new Account(new Money(basicViewModel.getBalance()));
        basic.setBankAccount(account);
        basicRepository.save(basic);
        LOGGER.info("[END] Create new Basic Client");
        return basic;
    }

    /**
     * Update Room Id
     * @param basicId receives a Long with basic's Id
     * @param roomId receives a Long with room's Id
     */
    public void updateRoomId(Long basicId, Integer roomId){
        LOGGER.info("Update Basic client " + basicId + " to room " + roomId);
        Basic basic = findById(basicId);
        basic.setRoomId(roomId);
        basicRepository.save(basic);
    }

    /**
     * Update Number of Stays
     * @param basicId receives a Long Basic's id
     * @param numberOfStays receives an Integer with number of stays
     */
    public void updateNumberOfStays(Long basicId, Integer numberOfStays){
        LOGGER.info("Update Basic client " + basicId + " with number " + numberOfStays + " of stays");
        Basic basic = findById(basicId);
        basic.setNumberOfStays(numberOfStays);
        basicRepository.save(basic);
    }

    /**
     * Update Basic Balance
     * @param basicId receives a Long Basic's id
     * @param updatedBalance receives a balance
     */
    public void updateBasicBalance(Long basicId, BigDecimal updatedBalance){
        LOGGER.info("Update Basic client " + basicId + " with balance " + updatedBalance);
        Basic basic = findById(basicId);
        basic.getBankAccount().setBalance(new Money(updatedBalance));
        basicRepository.save(basic);
    }

    /**
     * Delete Basic User
     * @param basicId receives a Long Basic's id
     */
    public void deleteBasicUser(Long basicId){
        LOGGER.info("Delete Basic Client with id " + basicId);
        Basic basic = findById(basicId);
        basicRepository.delete(basic);
    }

    /**
     * Restrict Username
     * @param username receives a String with username
     * @throws UsernameExistsException an exception
     */
    public void restrictUsername(String username) throws UsernameExistsException {
        LOGGER.info("Make sure username " + username + " is available to new basic user" );
        List<Basic> basicList = basicRepository.findAll().stream().filter(basic -> basic.getUsername().equals(username)).collect(Collectors.toList());
        if(basicList.size()>0)
            throw new UsernameExistsException("This username already exists");

    }

    public Long findBasicByUsername(String username){
        LOGGER.info("Find Basic with username " + username);
        return basicRepository.findBasicByUsername(username);
    }

    public Boolean basicUserIdMatchesName(Long basicId, String name){
        LOGGER.info("Make sure basic user " + basicId + " matches name " + name);
        List<Basic> basicUsers = basicRepository.findAll().stream().filter(user -> user.getId().equals(basicId)).collect(Collectors.toList());
        Basic basic = null;

        LOGGER.info("Make sure user " + basicId + " exists and has a reservation in the hotel");
        if (basicUsers.size() == 0)
            throw new DataNotFoundException("User id doesn't exist");

        else if (basicUsers.size() > 0) {
            basic = findById(basicId);
            if (!basic.getName().equals(name))
                throw new DataNotFoundException("Basic user name doesn't match invoice's name provided");
        }
        return true;
    }
}
