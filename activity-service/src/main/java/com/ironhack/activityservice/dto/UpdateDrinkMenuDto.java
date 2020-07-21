package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.DrinkMenu;

import javax.validation.constraints.NotNull;

/**
 * Update Drink Menu Dto
 */
public class UpdateDrinkMenuDto {
    /**
     * Attribute roomFoodId of type Long
     */
    @NotNull(message = "Room Food Id cannot be null")
    private Long roomFoodId;
    /**
     * Attribute drinkMenu of type DrinkMenu
     */
    @NotNull(message = "Drink option from Menu cannot be null")
    private DrinkMenu drinkMenu;

    /**
     * UpdateDrinkMenuDto's Constructor
     * @param roomFoodId receives a Long with roomFoodId
     * @param drinkMenu receives a DrinkMenu with drinkMenu
     */
    public UpdateDrinkMenuDto(Long roomFoodId, DrinkMenu drinkMenu) {
        this.roomFoodId = roomFoodId;
        this.drinkMenu = drinkMenu;
    }

    /**
     * Getter of roomFoodId
     * @return a Long with roomFoodId
     */
    public Long getRoomFoodId() {
        return roomFoodId;
    }

    /**
     * Getter of drinkMenu
     * @return a DrinkMenu with drinkMenu
     */
    public DrinkMenu getDrinkMenu() {
        return drinkMenu;
    }
}
