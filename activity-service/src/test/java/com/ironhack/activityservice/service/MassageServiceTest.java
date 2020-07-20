package com.ironhack.activityservice.service;

import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.enums.MassageType;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;
import com.ironhack.activityservice.repository.MassageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MassageServiceTest {
    @Autowired
    private MassageService massageService;

    @Autowired
    private MassageRepository massageRepository;

    private Massage massage;

    @BeforeEach
    void setUp() {
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY, LocalDateTime.now());
        massageRepository.save(massage);
    }

    @AfterEach
    void tearDown() {
        massageRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, massageService.findAll().size());
    }

    @Test
    void findMassageById() {
        assertEquals(massage.getRoomId(), massageService.findMassageById(massage.getActivityId()).getRoomId());
    }

    @Test
    void changeMassageType_withoutMicroservice() {
        UpdateMassageTypeDto updateMassageTypeDto = new UpdateMassageTypeDto(1L, MassageType.COUPLES);
        massageService.changeMassageType(updateMassageTypeDto);
        assertEquals(MassageType.AROMATHERAPY, massage.getMassageType());
    }

    @Test
    void createMassageAppointment() {
        MassageViewModel massageViewModel = new MassageViewModel();
        massageViewModel.setUserId(1L);
        massageViewModel.setRoomId(1);
        massageViewModel.setBeginOfActivity(LocalDateTime.now());
        massageViewModel.setMassageType(MassageType.COUPLES);
        Massage newMassage = massageService.createMassageAppointment(massageViewModel);
        assertEquals(null, newMassage);
    }

    @Test
    void cancelMassageAppointment() {

    }

    @Test
    void confirmData() {
    }

    @Test
    void checkEnoughBalance() {
    }

    @Test
    void resetMoney() {
    }

    @Test
    void confirmDataFail() {
        massageService.confirmDataFail(1L, 1, new Money(new BigDecimal("20.99")));
        assertEquals(MassageType.AROMATHERAPY.getPrice(), massage.getMassageType().getPrice());
    }

    @Test
    void resetMoneyFail() {
    }

    @Test
    void checkEnoughBalanceFail() {
    }

    @Test
    void createMassageAppointmentFail() {
    }

    @Test
    void updateUserMassageFail() {
    }
}