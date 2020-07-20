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

@Service
public class PremiumService {
    @Autowired
    private PremiumRepository premiumRepository;

    private static final Logger LOGGER = LogManager.getLogger(PremiumService.class);

    public List<Premium> findAll(){
        LOGGER.info("Find All Premium Clients");
        return premiumRepository.findAll();
    }

    public Premium findById(Long id){
        LOGGER.info("Find Premium Client with id " + id);
        return premiumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Premium user id not found"));
    }

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

    public void updateRoomId(Long premiumId, Integer roomId){
        LOGGER.info("Update Premium client " + premiumId + " to room " + roomId);
        Premium premium = findById(premiumId);
        premium.setRoomId(roomId);
        premiumRepository.save(premium);
    }

    public void updateNumberOfStays(Long premiumId, Integer numberOfStays){
        LOGGER.info("Update Premium client " + premiumId + " with number " + numberOfStays + " of stays");
        Premium premium = findById(premiumId);
        premium.setNumberOfStays(numberOfStays);
        premiumRepository.save(premium);
    }

    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance){
        LOGGER.info("Update Premium client " + premiumId + " with balance " + updatedBalance);
        Premium premium = findById(premiumId);
        premium.getBankAccount().setBalance(new Money(updatedBalance));
        premiumRepository.save(premium);
    }

    public void deletePremiumUser(Long premiumId){
        LOGGER.info("Delete Premium Client with id " + premiumId);
        Premium premium = findById(premiumId);
        premiumRepository.delete(premium);
    }

    public void restrictUsername(String username) throws UsernameExistsException {
        LOGGER.info("Make sure username " + username + " is available to new premium user" );
        List<Premium> premiumList = premiumRepository.findAll().stream().filter(premium -> premium.getUsername().equals(username)).collect(Collectors.toList());
        if(premiumList.size()>0)
            throw new UsernameExistsException("This username already exists");

    }

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
