package com.ironhack.invoiceservice.client;

import com.ironhack.invoiceservice.model.activities.Massage;
import com.ironhack.invoiceservice.model.activities.PoolRent;
import com.ironhack.invoiceservice.model.activities.RoomFood;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "activity-service")
public interface ActivityClient {
    /**
     * Find All Massages
     * @return a list of massages
     */
    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages();
    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents();
    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood();

}
