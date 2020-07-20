package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.FoodMenu;

public class UpdateRoomFoodMenuDto {
    private Long roomFoodId;
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
