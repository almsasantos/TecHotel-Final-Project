package com.ironhack.activityservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Account;
import com.ironhack.activityservice.model.classes.Address;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.enums.DrinkMenu;
import com.ironhack.activityservice.model.enums.FoodMenu;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.invoices.Invoice;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;
import com.ironhack.activityservice.repository.RoomFoodRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RoomFoodServiceTest {

    @Autowired
    private RoomFoodService roomFoodService;

    @Autowired
    private RoomFoodRepository roomFoodRepository;

    @MockBean
    private InvoiceClient invoiceClient;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private UserClient userClient;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private RoomFood roomFood;
    List<RoomFood> roomFoodList = new ArrayList<RoomFood>();
    List<Object[]> list = new ArrayList<Object[]>();
    private Account account = new Account();
    private Address address = new Address();
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Invoice invoice;
    private Basic basic;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        roomFood = new RoomFood(1L, 1, FoodMenu.BEEF_STROGANOFF, DrinkMenu.BEER);
        roomFoodList.add(roomFood);
        roomFoodRepository.save(roomFood);
        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
    }

    @AfterEach
    void tearDown() {
        roomFoodRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, roomFoodService.findAll().size());
    }

    @Test
    void findRoomFoodById() {
        assertEquals(roomFood.getRoomId(), roomFoodService.findRoomFoodById(roomFoodList.get(0).getActivityId()).getRoomId());
    }

    @Test
    void createRoomFoodRequest() {
        RoomFoodViewModel roomFoodViewModel = new RoomFoodViewModel();
        roomFoodViewModel.setDrinkMenu(DrinkMenu.CAPPUCCINO);
        roomFoodViewModel.setFoodMenu(FoodMenu.FRESH_SALMON);
        roomFoodViewModel.setRoomId(2);
        roomFoodViewModel.setUserId(1L);
        RoomFood roomFood1 = new RoomFood(1L, 1, FoodMenu.BEEF_STROGANOFF, DrinkMenu.BEER);
        assertThrows(ReservationException.class, () -> {
            roomFoodService.createRoomFoodRequest(roomFoodViewModel);});

    }

    @Test
    void updateFoodMenu() {
        UpdateRoomFoodMenuDto updateRoomFoodMenuDto = new UpdateRoomFoodMenuDto(1L, FoodMenu.BEEF_STROGANOFF);
        roomFoodService.updateFoodMenu(updateRoomFoodMenuDto);
        assertEquals( FoodMenu.BEEF_STROGANOFF, roomFood.getFoodMenu());
    }

    @Test
    void updateDrinkMenu() {
        UpdateDrinkMenuDto updateDrinkMenuDto = new UpdateDrinkMenuDto(1L, DrinkMenu.BEER);
        roomFoodService.updateDrinkMenu(updateDrinkMenuDto);
        assertEquals(DrinkMenu.BEER, roomFood.getDrinkMenu());
    }

    @Test
    void deliverRoomFood() {
        roomFoodService.deliverRoomFood(1L);
        assertEquals(true, roomFood.getDelivered());
    }

    @Test
    void cancelRoomFoodRequest() {
    }

    @Test
    void confirmData() {
    }

    @Test
    void checkEnoughBalance() {
    }

    @Test
    void resetMoney() {
    }

    @Test
    void confirmDataFail() {
        roomFoodService.confirmDataFail(1L, 1, new Money(new BigDecimal("20.99")));
        assertEquals(DrinkMenu.BEER.getPrice(), roomFood.getDrinkMenu().getPrice());
    }

    @Test
    void resetMoneyFail() {
        roomFoodService.resetMoneyFail(1L, new BigDecimal("20.99"));
    }

    @Test
    void checkEnoughBalanceFail() {
        assertThrows(ReservationException.class, () -> {
            roomFoodService.checkEnoughBalanceFail(basic.getId(), TypeOfUser.BASIC, new Money(new BigDecimal("19.99")));});
    }

    @Test
    void createRoomFoodRequestFail() {
        RoomFoodViewModel roomFoodViewModel = new RoomFoodViewModel();
        roomFoodViewModel.setDrinkMenu(DrinkMenu.CAPPUCCINO);
        roomFoodViewModel.setFoodMenu(FoodMenu.FRESH_SALMON);
        roomFoodViewModel.setRoomId(2);
        roomFoodViewModel.setUserId(1L);
        assertThrows(ReservationException.class, () -> {
            roomFoodService.createRoomFoodRequestFail(roomFoodViewModel);});
    }

    @Test
    void updateFoodMenuFail() {
    }

    @Test
    void updateDrinkMenuFail() {
    }

    @Test
    void filterRoomFoodByUserId() {
        assertEquals(1, roomFoodService.filterRoomFoodByUserId(1L).size());
    }
}