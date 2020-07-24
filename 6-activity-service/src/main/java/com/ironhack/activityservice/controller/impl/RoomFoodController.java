package com.ironhack.activityservice.controller.impl;

import com.ironhack.activityservice.controller.interfaces.IRoomFoodController;
import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.exception.ReservationException;
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

    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood(){
        return roomFoodService.findAll();
    }

    /**
     * Find RoomFood by id
     * @param roomFoodId receives a Long with roomFoodId
     * @return a RoomFood
     */
    @GetMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId){
        return roomFoodService.findRoomFoodById(roomFoodId);
    }

    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of objects
     */
    @GetMapping("/activities/room-food-services/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> filterRoomFoodByUserId(@PathVariable("userId") Long userId){
        return roomFoodService.filterRoomFoodByUserId(userId);
    }

    /**
     * Create new Room Food Request
     * @param roomFoodViewModel receives a RoomFoodViewModel
     * @return a RoomFood
     */
     @PostMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel){
        return roomFoodService.createRoomFoodRequest(roomFoodViewModel);
    }

    /**
     * Deliver Room Food
     * @param roomFoodId receives a Long with roomFoodId
     */
    @PatchMapping("/activities/room-food-services/deliver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId){
        roomFoodService.deliverRoomFood(roomFoodId);
    }

    /**
     * Update Food Menu
     * @param updateRoomFoodMenuDto receives a UpdateRoomFoodMenuDto
     */
    @PatchMapping("/activities/room-food-services/update-food")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto){
        roomFoodService.updateFoodMenu(updateRoomFoodMenuDto);
    }

    /**
     * Update Drink Menu
     * @param updateDrinkMenuDto receives an UpdateDrinkMenuDto
     */
    @PatchMapping("/activities/room-food-services/update-drink")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto){
        roomFoodService.updateDrinkMenu(updateDrinkMenuDto);
    }

    /**
     * Cancel Room Food Request
     * @param roomFoodId receives a Long with roomFoodId
     * @throws ReservationException an Exception
     */
    @DeleteMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId){
        roomFoodService.cancelRoomFoodRequest(roomFoodId);
    }
}
