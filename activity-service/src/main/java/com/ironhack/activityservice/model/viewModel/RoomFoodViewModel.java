package com.ironhack.activityservice.model.viewModel;

import com.ironhack.activityservice.model.enums.DrinkMenu;
import com.ironhack.activityservice.model.enums.FoodMenu;

/**
 * Room Food View Model
 */
public class RoomFoodViewModel {
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;
    /**
     * Attribute foodMenu of type FoodMenu
     */
    private FoodMenu foodMenu;
    /**
     * Attribute drinkMenu of type DrinkMenu
     */
    private DrinkMenu drinkMenu;

    /**
     * Getter of userId
     * @return a Long with userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter of userId
     * @param userId receives a Long with userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter of roomId
     * @return a Integer with roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter of roomId
     * @param roomId receives a Integer with roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * Getter of foodMenu
     * @return a FoodMenu with foodMenu
     */
    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    /**
     * Setter of foodMenu
     * @param foodMenu receives a FoodMenu with foodMenu
     */
    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    /**
     * Getter of drinkMenu
     * @return a DrinkMenu with drinkMenu
     */
    public DrinkMenu getDrinkMenu() {
        return drinkMenu;
    }

    /**
     * Setter of drinkMenu
     * @param drinkMenu receives a DrinkMenu with drinkMenu
     */
    public void setDrinkMenu(DrinkMenu drinkMenu) {
        this.drinkMenu = drinkMenu;
    }
}
