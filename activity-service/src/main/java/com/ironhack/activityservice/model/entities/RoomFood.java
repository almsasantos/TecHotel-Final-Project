package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.enums.DrinkMenu;
import com.ironhack.activityservice.model.enums.FoodMenu;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "room_food_services")
public class RoomFood extends Activity {
    @Enumerated(EnumType.STRING)
    private FoodMenu foodMenu;
    @Enumerated(EnumType.STRING)
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
