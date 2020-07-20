package com.ironhack.invoiceservice.client;

import com.ironhack.invoiceservice.model.rooms.RegularRoom;
import com.ironhack.invoiceservice.model.rooms.SuiteRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "room-service")
public interface RoomClient {
    @GetMapping("/regular-rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RegularRoom> findAllRegularRooms();

    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites();
}
