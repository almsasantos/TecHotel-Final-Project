package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Premium Controller Interface
 */
public interface IPremiumController {
    /**
     * Find All Premium users
     * @return a list of Premium
     */
    public List<Premium> findAllPremiumUsers();

    /**
     * Find Premium User by Id
     * @param premiumId receives a Long with Premium's id
     * @return a Premium
     */
    public Premium findPremiumUserById(Long premiumId);

    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    public Premium createPremiumUser(BasicAndPremiumViewModel basicAndPremiumViewModel);

    /**
     * Update Premium Room Id
     * @param premiumId receives a Long with Premium's id
     * @param roomId receives an Integer with Room's id
     */
    public void updatePremiumRoomId(Long premiumId, Integer roomId);

    /**
     * Update Premium Balance
     * @param premiumId receives a Long with Premium's id
     * @param updatedBalance receives a BigDecimal
     */
    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance);

    /**
     * Update Premium Number of Stays
     * @param premiumId receives a Long with Premium's id
     * @param numberOfStays receives an Integer with number of stays
     */
    public void updatePremiumNumberOfStays(Long premiumId, Integer numberOfStays);

    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's id
     */
    public void deletePremiumUser(Long premiumId);

    /**
     * Check if Premium Id matches name
     * @param premiumId receives a Long with Premium's id
     * @param name receives a String with name
     * @return a Boolean Value
     */
    public Boolean premiumUserIdMatchesName(Long premiumId, String name);
}
