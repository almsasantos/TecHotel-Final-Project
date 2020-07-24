package com.ironhack.invoiceservice.service;

import com.ironhack.invoiceservice.client.ActivityClient;
import com.ironhack.invoiceservice.client.ReservationClient;
import com.ironhack.invoiceservice.client.RoomClient;
import com.ironhack.invoiceservice.client.UserClient;
import com.ironhack.invoiceservice.enums.InvoiceType;
import com.ironhack.invoiceservice.exception.DataNotFoundException;
import com.ironhack.invoiceservice.model.entities.Invoice;
import com.ironhack.invoiceservice.model.rooms.RegularRoom;
import com.ironhack.invoiceservice.model.rooms.SuiteRoom;
import com.ironhack.invoiceservice.model.users.Basic;
import com.ironhack.invoiceservice.model.users.Premium;
import com.ironhack.invoiceservice.model.viewmodel.InvoiceViewModel;
import com.ironhack.invoiceservice.repository.InvoiceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Invoice Service
 */
@Service
public class InvoiceService {
    /**
     * Autowired of InvoiceRepository
     */
    @Autowired
    private InvoiceRepository invoiceRepository;
    /**
     * Autowired of UserClient
     */
    @Autowired
    private UserClient userClient;
    /**
     * Autowired of ActivityClient
     */
    @Autowired
    private ActivityClient activityClient;
    /**
     * Autowired of RoomClient
     */
    @Autowired
    private RoomClient roomClient;
    /**
     * Autowired of ReservationClient
     */
    @Autowired
    private ReservationClient reservationClient;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(InvoiceService.class);

    /**
     * Find All Invoices
     * @return a list of invoices
     */
    public List<Invoice> findAll(){
        LOGGER.info("Find all Invoices");
        return invoiceRepository.findAll();
    }

    /**
     * Check information
     * @param invoiceViewModel receives a Invoice View Model
     * @return a String
     */
    public String checkInformation(InvoiceViewModel invoiceViewModel){
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(invoiceViewModel.getUserId())).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(invoiceViewModel.getUserId())).collect(Collectors.toList());
        Basic basic = null; Premium premium = null; List<RegularRoom> regularRooms = null; List<SuiteRoom> suiteRooms = null;

        LOGGER.info("Make sure user " + invoiceViewModel.getUserId() + " exists and has a reservation in the hotel");
        if (basicUsers.size() == 0 && premiumUsers.size() == 0) throw new DataNotFoundException("User id doesn't exist");

        else if (basicUsers.size() > 0) { LOGGER.info("User with id " + invoiceViewModel.getUserId() + " is from type Basic");
            basic = userClient.findBasicUserById(invoiceViewModel.getUserId());
            reservationClient.userIdMatchesRoom(basic.getId(), invoiceViewModel.getRoomId());
            return basic.getName();
        }

        else if (premiumUsers.size() > 0) { LOGGER.info("User with id " + invoiceViewModel.getUserId() + " is from type Premium");
            premium = userClient.findPremiumUserById(invoiceViewModel.getUserId());
            if (premium.getRoomId() == 0 && premium.getRoomId().equals(null)) throw new DataNotFoundException("Premium user hasn't reservation on going");
            reservationClient.userIdMatchesRoom(premium.getId(), invoiceViewModel.getRoomId());
            return premium.getName(); }
        return null;
    }

    /**
     * Create a Invoice based on Activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice
     */
    public Invoice createInvoiceActivity(InvoiceViewModel invoiceViewModel){
        LOGGER.info("[INIT] Create new Invoice for user " + invoiceViewModel.getUserId() + " of activity " + invoiceViewModel.getInvoiceType());

        String name = checkInformation(invoiceViewModel);
        Invoice invoice = new Invoice(invoiceViewModel.getUserId(), name, invoiceViewModel.getRoomId(), invoiceViewModel.getInvoiceType(), invoiceViewModel.getPriceOwed());
        LOGGER.info("[END] Create new Invoice for user " + invoiceViewModel.getUserId() + " of activity " + invoiceViewModel.getInvoiceType());
        return invoiceRepository.save(invoice);
    }

    public Invoice createFinalInvoice(Long userId){
        LOGGER.info("[INIT] Create new Invoice for user " + userId + " of end of stay");

        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        Basic basic = null;
        Premium premium = null;
        List<RegularRoom> regularRooms = null;
        List<SuiteRoom> suiteRooms = null;

        LOGGER.info("Make sure user " + userId + " exists and has a reservation in the hotel");
        if (basicUsers.size() == 0 && premiumUsers.size() == 0) throw new DataNotFoundException("User id doesn't exist");

        else if (basicUsers.size() > 0) {
            LOGGER.info("User with id " + userId + " is from type Basic");
            basic = userClient.findBasicUserById(userId);
            if (basic.getRoomId() == 0 && basic.getRoomId().equals(null)) throw new DataNotFoundException("Basic user hasn't reservation on going");

            if (!basic.getRoomId().equals(userId)) throw new DataNotFoundException("Basic user room doesn't match invoice's room id provided");

            if (!basic.getName().equals(userId)) throw new DataNotFoundException("Basic user name doesn't match invoice's name provided");

            Basic finalBasic = basic;
            regularRooms = roomClient.findAllRegularRooms().stream().filter(regularRoom -> regularRoom.getRoomId().equals(finalBasic.getRoomId())).collect(Collectors.toList());
            suiteRooms = roomClient.findAllSuites().stream().filter(regularRoom -> regularRoom.getRoomId().equals(finalBasic.getRoomId())).collect(Collectors.toList());
        }

        else {
            LOGGER.info("User with id " + userId + " is from type Premium");
            premium = userClient.findPremiumUserById(userId);
            if (premium.getRoomId() == 0 && premium.getRoomId().equals(null)) throw new DataNotFoundException("Premium user hasn't reservation on going");

            if (!premium.getRoomId().equals(userId)) throw new DataNotFoundException("Premium user room doesn't match invoice's room id provided");

            if(!premium.getName().equals(userId)) throw new DataNotFoundException("Premium user name doesn't match invoice's name provided");

            Premium finalPremium = premium;
            regularRooms = roomClient.findAllRegularRooms().stream().filter(regularRoom -> regularRoom.getRoomId().equals(finalPremium.getRoomId())).collect(Collectors.toList());
            suiteRooms = roomClient.findAllSuites().stream().filter(regularRoom -> regularRoom.getRoomId().equals(finalPremium.getRoomId())).collect(Collectors.toList());
        }

        LOGGER.info("Calculate all expenses from user " + userId);

        List<BigDecimal> price = new ArrayList<BigDecimal>();
        activityClient.findAllMassages().stream().forEach(massage -> { if(massage.getUserId().equals(userId)){
                                                                price.add(massage.getTotalPrice().getAmount()); } });
        activityClient.findAllPoolRents().stream().forEach(poolRent -> { if(poolRent.getUserId().equals(userId)){
                                                                price.add(poolRent.getTotalPrice().getAmount()); } });
        activityClient.findAllRoomFood().stream().forEach(roomFood -> { if(roomFood.getUserId().equals(userId)){
                                                                price.add(roomFood.getTotalPrice().getAmount()); } });

        regularRooms.stream().forEach(regularRoom -> price.add(regularRoom.getPrice()));
        suiteRooms.stream().forEach(suiteRoom -> price.add(suiteRoom.getPrice()));

        BigDecimal finalPrice = price.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        LOGGER.info("Set total price of Stay in new Invoice");
        Invoice invoice = new Invoice();
        if(basic != null){ invoice.setUserId(basic.getId()); invoice.setName(basic.getName()); invoice.setPriceOwed(finalPrice); invoice.setRoomId(basic.getRoomId()); }
        else if (premium != null){ invoice.setUserId(premium.getId()); invoice.setName(premium.getName()); invoice.setPriceOwed(finalPrice); invoice.setRoomId(premium.getRoomId()); }

        invoice.setInvoiceType(InvoiceType.END_OF_STAY_AT_THE_HOTEL);
        LOGGER.info("[END] Create new Invoice for user " + userId + " of end of stay");
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findInvoiceByUserId(Long userId){
        LOGGER.info("Find Invoices by User Id " + userId);
        List<Invoice> invoices = new ArrayList<Invoice>();
        invoiceRepository.findAll().stream().filter(invoice -> invoice.getUserId().equals(userId))
                .forEach(invoice -> {invoice.getInvoiceId(); invoice.getName(); invoice.getRoomId(); invoice.getPriceOwed(); invoices.add(invoice);});

        return invoices;
    }

}
