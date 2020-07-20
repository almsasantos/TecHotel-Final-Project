package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface IBasicController {
    public List<Basic> findAllBasicUser();

    public Basic findBasicUserById(Long basicId);

    public Basic createBasicUser(BasicAndPremiumViewModel basicAndPremiumViewModel);

    public void updateBasicRoomId(Long basicId, Integer roomId);

    public void updateBasicBalance(Long basicId, BigDecimal updatedBalance);

    public void updateBasicNumberOfStays(Long basicId, Integer numberOfStays);

    public void deleteBasicUser(Long basicId);
}
