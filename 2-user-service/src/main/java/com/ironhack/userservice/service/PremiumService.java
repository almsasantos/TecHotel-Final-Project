package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.repository.PremiumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service of Premium User
 */
@Service
public class PremiumService {

    /**
     * Autowired of Premium Repository
     */
    @Autowired
    private PremiumRepository premiumRepository;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PremiumService.class);

    /**
     * Find All Premium Users
     * @return a list of Premium
     */
    public List<Premium> findAll(){
        LOGGER.info("Find All Premium Clients");
        return premiumRepository.findAll();
    }

    /**
     * Find Premium by id
     * @param id receives a Long with Premium's Id
     * @return a Premium User
     */
    public Premium findById(Long id){
        LOGGER.info("Find Premium Client with id " + id);
        return premiumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Premium user id not found"));
    }

    /**
     * Create a new Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User
     */
    public Premium createPremiumUser(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.info("[INIT] Create new Premium Client");

        LOGGER.info("Check if username already exists");
        restrictUsername(basicAndPremiumViewModel.getUsername());

        Premium premium = new Premium();
        premium.setName(basicAndPremiumViewModel.getName());
        premium.setUsername(basicAndPremiumViewModel.getUsername());
        premium.setPassword(basicAndPremiumViewModel.getPassword());
        premium.setPhoneNumber(basicAndPremiumViewModel.getPhoneNumber());
        premium.setEmail(basicAndPremiumViewModel.getEmail());
        premium.setBirthDate(basicAndPremiumViewModel.getBirthDate());

        Address address = new Address(basicAndPremiumViewModel.getCountry(), basicAndPremiumViewModel.getCity(),
                basicAndPremiumViewModel.getStreet(), basicAndPremiumViewModel.getPostalCode());

        premium.setAddress(address);

        Account account = new Account(new Money(basicAndPremiumViewModel.getBalance()));
        premium.setBankAccount(account);
        premiumRepository.save(premium);

        LOGGER.info("[END] Create new Premium Client");
        return premium;
    }

    /**
     * Update Room Id
     * @param premiumId receives a Long with Premium's Id
     * @param roomId receives a Integer with Room's Id
     */
    public void updateRoomId(Long premiumId, Integer roomId){
        LOGGER.info("Update Premium client " + premiumId + " to room " + roomId);
        Premium premium = findById(premiumId);
        premium.setRoomId(roomId);
        premiumRepository.save(premium);
    }

    /**
     * Update number of stays
     * @param premiumId receives a Long with Premium's Id
     * @param numberOfStays receives a Integer with number of stays
     */
    public void updateNumberOfStays(Long premiumId, Integer numberOfStays){
        LOGGER.info("Update Premium client " + premiumId + " with number " + numberOfStays + " of stays");
        Premium premium = findById(premiumId);
        premium.setNumberOfStays(numberOfStays);
        premiumRepository.save(premium);
    }

    /**
     * Update Premium balance
     * @param premiumId receives a Long with Premium's Id
     * @param updatedBalance receives a BigDecimal
     */
    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance){
        LOGGER.info("Update Premium client " + premiumId + " with balance " + updatedBalance);
        Premium premium = findById(premiumId);
        premium.getBankAccount().setBalance(new Money(updatedBalance));
        premiumRepository.save(premium);
    }

    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's Id
     */
    public void deletePremiumUser(Long premiumId){
        LOGGER.info("Delete Premium Client with id " + premiumId);
        Premium premium = findById(premiumId);
        premiumRepository.delete(premium);
    }

    /**
     * Restrict Username
     * @param username receives a String with username
     * @throws UsernameExistsException an Exception
     */
    public void restrictUsername(String username) throws UsernameExistsException {
        LOGGER.info("Make sure username " + username + " is available to new premium user" );
        List<Premium> premiumList = premiumRepository.findAll().stream().filter(premium -> premium.getUsername().equals(username)).collect(Collectors.toList());
        if(premiumList.size()>0)
            throw new UsernameExistsException("This username already exists");

    }

    public Long findPremiumByUsername(String username){
        LOGGER.info("Find Premium with username " + username);
        return premiumRepository.findPremiumByUsername(username);
    }

    /**
     * Check if id matches name
     * @param premiumId receives a Long with Premium's Id
     * @param name receives a String with name
     * @return a Boolean
     */
    public Boolean premiumUserIdMatchesName(Long premiumId, String name){
        LOGGER.info("Make sure premium user " + premiumId + " matches name " + name);
        List<Premium> premiumUsers = findAll().stream().filter(user -> user.getId().equals(premiumId)).collect(Collectors.toList());
        Premium premium = null;

        LOGGER.info("Make sure user " + premiumId + " exists and has a reservation in the hotel");
        if (premiumUsers.size() == 0)
            throw new DataNotFoundException("User id doesn't exist");

        else if (premiumUsers.size() > 0) {
            premium = findById(premiumId);
            if (!premium.getName().equals(name))
                throw new DataNotFoundException("Basic user name doesn't match invoice's name provided");
        }
        return true;
    }
}
