package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.FoodMenu;

import javax.validation.constraints.NotNull;

/**
 * Update Room Food Menu Dto
 */
public class UpdateRoomFoodMenuDto {
    /**
     * Attribute massageId of type Long
     */
    @NotNull(message = "Room Food Request cannot be null")
    private Long roomFoodId;
    /**
     * Attribute massageId of type Long
     */
    @NotNull(message = "Food option from Menu cannot be null")
    private FoodMenu foodMenu;

    /**
     * UpdateRoomFoodMenuDto's Constructor
     * @param roomFoodId receives a Long with roomFoodId
     * @param foodMenu receives a FoodMenu with foodMenu
     */
    public UpdateRoomFoodMenuDto(Long roomFoodId, FoodMenu foodMenu) {
        this.roomFoodId = roomFoodId;
        this.foodMenu = foodMenu;
    }

    /**
     * Getter of roomFoodId
     * @return a Long with roomFoodId
     */
    public Long getRoomFoodId() {
        return roomFoodId;
    }

    /**
     * Getter of foodMenu
     * @return a FoodMenu with foodMenu
     */
    public FoodMenu getFoodMenu() {
        return foodMenu;
    }
}

