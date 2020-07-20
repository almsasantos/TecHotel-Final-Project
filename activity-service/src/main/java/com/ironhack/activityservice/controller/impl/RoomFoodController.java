package com.ironhack.activityservice.controller.impl;

import com.ironhack.activityservice.controller.interfaces.IRoomFoodController;
import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;
import com.ironhack.activityservice.service.RoomFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoomFoodController implements IRoomFoodController {
    @Autowired
    private RoomFoodService roomFoodService;

    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood(){
        return roomFoodService.findAll();
    }

    @GetMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId){
        return roomFoodService.findRoomFoodById(roomFoodId);
    }

    @PostMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel){
        return roomFoodService.createRoomFoodRequest(roomFoodViewModel);
    }

    @PatchMapping("/activities/room-food-services/deliver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId){
        roomFoodService.deliverRoomFood(roomFoodId);
    }

    @PatchMapping("/activities/room-food-services/update-food")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto){
        roomFoodService.updateFoodMenu(updateRoomFoodMenuDto);
    }

    @PatchMapping("/activities/room-food-services/update-drink")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto){
        roomFoodService.updateDrinkMenu(updateDrinkMenuDto);
    }

    @DeleteMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId){
        roomFoodService.cancelRoomFoodRequest(roomFoodId);
    }
}
