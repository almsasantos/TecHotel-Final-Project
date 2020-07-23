package com.ironhack.edgeservice.model.entities.activities;

import com.ironhack.edgeservice.model.enums.DrinkMenu;
import com.ironhack.edgeservice.model.enums.FoodMenu;

/**
 * RoomFood
 */

public class RoomFood extends Activity {
    /**
     * Attribute foodMenu of type FoodMenu
     */
    private FoodMenu foodMenu;
    /**
     * Attribute drinkMenu of type DrinkMenu
     */
    private DrinkMenu drinkMenu;
    /**
     * Attribute delivered of type Boolean
     */
    private Boolean delivered;

    /**
     * Empty RoomFood's Constructor
     */
    public RoomFood() {this.delivered = false;}

    /**
     * RoomFood's Constructor
     * @param userId receives a Long with userId
     * @param roomId receives a Integer with roomId
     * @param foodMenu receives a FoodMenu with foodMenu
     * @param drinkMenu receives a DrinkMenu with drinkMenu
     */
    public RoomFood(Long userId, Integer roomId, FoodMenu foodMenu, DrinkMenu drinkMenu) {
        super(userId, roomId);
        this.foodMenu = foodMenu;
        this.drinkMenu = drinkMenu;
        this.delivered = false;
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

    /**
     * Getter of delivered
     * @return a Boolean with delivered
     */
    public Boolean getDelivered() {
        return delivered;
    }

    /**
     * Setter of delivered
     * @param delivered receives a Boolean with delivered
     */
    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }
}