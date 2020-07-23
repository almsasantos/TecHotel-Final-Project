package com.ironhack.activityservice.client;

import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.users.Premium;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    // --- BASIC USERS ---
    @GetMapping("/users/basics")
    @ResponseStatus(HttpStatus.OK)
    public List<Basic> findAllBasicUser();

    @GetMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basic findBasicUserById(@PathVariable("id") Long basicId);

    @PatchMapping("/users/basics/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicRoomId(@PathVariable("id") Long basicId, @PathVariable("roomId") Integer roomId);

    @PatchMapping("/users/basics/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicBalance(@PathVariable("id") Long basicId, @PathVariable("balance") BigDecimal updatedBalance);

    @GetMapping("/users/basics/check-name/{basicId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean basicUserIdMatchesName(@PathVariable("basicId") Long basicId, @PathVariable("name") String name);

    // --- PREMIUM USERS ---
    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers();

    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId);

    @PatchMapping("/users/premiums/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId);

    @PatchMapping("/users/premiums/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance);

    @GetMapping("/users/premiums/check-name/{premiumId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean premiumUserIdMatchesName(@PathVariable("premiumId") Long premiumId, @PathVariable("name") String name);
}
