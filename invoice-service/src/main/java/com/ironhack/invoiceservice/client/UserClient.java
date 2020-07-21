package com.ironhack.invoiceservice.client;

import com.ironhack.invoiceservice.model.users.Basic;
import com.ironhack.invoiceservice.model.users.Premium;
import com.ironhack.invoiceservice.model.viewmodel.BasicAndPremiumViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/basics")
    @ResponseStatus(HttpStatus.OK)
    public List<Basic> findAllBasicUser();

    @GetMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basic findBasicUserById(@PathVariable("id") Long basicId);

    @GetMapping("/users/basics/check-name/{basicId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean basicUserIdMatchesName(@PathVariable("basicId") Long basicId, @PathVariable("name") String name);

    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    @PostMapping("/users/basics")
    @ResponseStatus(HttpStatus.CREATED)
    public Basic createBasicUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);

    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers();

    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId);

    @GetMapping("/users/premiums/check-name/{premiumId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean premiumUserIdMatchesName(@PathVariable("premiumId") Long premiumId, @PathVariable("name") String name);

    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    @PostMapping("/users/premiums")
    @ResponseStatus(HttpStatus.CREATED)
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);
}

