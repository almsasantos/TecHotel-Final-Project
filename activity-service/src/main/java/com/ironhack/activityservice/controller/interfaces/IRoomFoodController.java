package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;

import java.util.List;

public interface IRoomFoodController {
    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    public List<RoomFood> findAllRoomFood();

    /**
     * Find RoomFood by id
     * @param roomFoodId receives a Long with roomFoodId
     * @return a RoomFood
     */
    public RoomFood findRoomFoodById(Long roomFoodId);

    /**
     * Create new Room Food Request
     * @param roomFoodViewModel receives a RoomFoodViewModel
     * @return a RoomFood
     */
    public RoomFood createRoomFoodRequest(RoomFoodViewModel roomFoodViewModel);

    /**
     * Deliver Room Food
     * @param roomFoodId receives a Long with roomFoodId
     */
    public void deliverRoomFood(Long roomFoodId);

    /**
     * Update Food Menu
     * @param updateRoomFoodMenuDto receives a UpdateRoomFoodMenuDto
     */
    public void updateFoodMenu(UpdateRoomFoodMenuDto updateRoomFoodMenuDto);

    /**
     * Update Drink Menu
     * @param updateDrinkMenuDto receives an UpdateDrinkMenuDto
     */
    public void updateDrinkMenu(UpdateDrinkMenuDto updateDrinkMenuDto);

    /**
     * Cancel Room Food Request
     * @param roomFoodId receives a Long with roomFoodId
     * @throws ReservationException an Exception
     */
    public void removeRoomFoodRequest(Long roomFoodId);

    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of objects
     */
    public List<Object[]> filterRoomFoodByUserId(Long userId);
}
