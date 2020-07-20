package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.FoodMenu;

import javax.validation.constraints.NotNull;

public class UpdateRoomFoodMenuDto {
    @NotNull(message = "Room Food Request cannot be null")
    private Long roomFoodId;
    @NotNull(message = "Food option from Menu cannot be null")
    private FoodMenu foodMenu;

    public UpdateRoomFoodMenuDto(Long roomFoodId, FoodMenu foodMenu) {
        this.roomFoodId = roomFoodId;
        this.foodMenu = foodMenu;
    }

    public Long getRoomFoodId() {
        return roomFoodId;
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }
}
