package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.DrinkMenu;

public class UpdateDrinkMenuDto {
    private Long roomFoodId;
    private DrinkMenu drinkMenu;

    public UpdateDrinkMenuDto(Long roomFoodId, DrinkMenu drinkMenu) {
        this.roomFoodId = roomFoodId;
        this.drinkMenu = drinkMenu;
    }

    public Long getRoomFoodId() {
        return roomFoodId;
    }

    public DrinkMenu getDrinkMenu() {
        return drinkMenu;
    }
}
