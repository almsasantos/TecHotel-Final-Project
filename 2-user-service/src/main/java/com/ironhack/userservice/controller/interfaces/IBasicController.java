package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Basic Controller interface
 */
public interface IBasicController {
    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    public List<Basic> findAllBasicUser();

    /**
     * Find basic user by id
     * @param basicId receives a Long with Basic's Id
     * @return a Basic user
     */
    public Basic findBasicUserById(Long basicId);

    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    public Basic createBasicUser(BasicAndPremiumViewModel basicAndPremiumViewModel);

    /**
     * Update Basic Room Id
     * @param basicId receives a Long with Basic's Id
     * @param roomId receives an Integer with Room's Id
     */
    public void updateBasicRoomId(Long basicId, Integer roomId);

    /**
     * Update Basic Balance
     * @param basicId receives a Long with Basic's Id
     * @param updatedBalance receives a BigDecimal
     */
    public void updateBasicBalance(Long basicId, BigDecimal updatedBalance);

    /**
     * Update Basic number of stays
     * @param basicId receives a Long with Basic's Id
     * @param numberOfStays receives an Integer with number of stays
     */
    public void updateBasicNumberOfStays(Long basicId, Integer numberOfStays);

    /**
     * Delete Basic User
     * @param basicId receives a Long with Basic's Id
     */
    public void deleteBasicUser(Long basicId);

    /**
     * Check if Basic User matches name
     * @param basicId receives a Long with Basic's Id
     * @param name receives a String with name
     * @return a Boolean value
     */
    public Boolean basicUserIdMatchesName(Long basicId, String name);
}
