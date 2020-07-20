package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.DrinkMenu;

import javax.validation.constraints.NotNull;

public class UpdateDrinkMenuDto {
    @NotNull(message = "Room Food Id cannot be null")
    private Long roomFoodId;
    @NotNull(message = "Drink option from Menu cannot be null")
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
