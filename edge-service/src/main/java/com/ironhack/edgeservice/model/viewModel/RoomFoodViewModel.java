package com.ironhack.edgeservice.model.viewModel;

import com.ironhack.edgeservice.model.enums.DrinkMenu;
import com.ironhack.edgeservice.model.enums.FoodMenu;

public class RoomFoodViewModel {
    private Long userId;
    private Integer roomId;
    private FoodMenu foodMenu;
    private DrinkMenu drinkMenu;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
}
