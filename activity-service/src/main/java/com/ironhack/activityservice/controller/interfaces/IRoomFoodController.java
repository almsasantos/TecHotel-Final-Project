package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;

import java.util.List;

public interface IRoomFoodController {
    public List<RoomFood> findAllRoomFood();

    public RoomFood findRoomFoodById(Long roomFoodId);

    public RoomFood createRoomFoodRequest(RoomFoodViewModel roomFoodViewModel);

    public void deliverRoomFood(Long roomFoodId);

    public void updateFoodMenu(UpdateRoomFoodMenuDto updateRoomFoodMenuDto);

    public void updateDrinkMenu(UpdateDrinkMenuDto updateDrinkMenuDto);

    public void removeRoomFoodRequest(Long roomFoodId);
}
