package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

public interface IPremiumController {
    public List<Premium> findAllPremiumUsers();

    public Premium findPremiumUserById(Long premiumId);

    public Premium createPremiumUser(BasicAndPremiumViewModel basicAndPremiumViewModel);

    public void updatePremiumRoomId(Long premiumId, Integer roomId);

    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance);

    public void updatePremiumNumberOfStays(Long premiumId, Integer numberOfStays);

    public void deletePremiumUser(Long premiumId);
}
