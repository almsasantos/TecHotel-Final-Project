package com.ironhack.invoiceservice.model.activities;

import com.ironhack.invoiceservice.enums.DrinkMenu;
import com.ironhack.invoiceservice.enums.FoodMenu;

public class RoomFood extends Activity {
    private FoodMenu foodMenu;
    private DrinkMenu drinkMenu;
    private Boolean delivered;

    public RoomFood() {this.delivered = false;}

    public RoomFood(Long userId, Integer roomId, FoodMenu foodMenu, DrinkMenu drinkMenu) {
        super(userId, roomId);
        this.foodMenu = foodMenu;
        this.drinkMenu = drinkMenu;
        this.delivered = false;
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public DrinkMenu getDrinkMenu() {
        return drinkMenu;
    }

    public void setDrinkMenu(DrinkMenu drinkMenu) {
        this.drinkMenu = drinkMenu;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }
}
