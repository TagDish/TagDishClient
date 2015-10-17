package com.tagdish.test;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.tagdish.constant.TagDishDomainConstant;
import com.tagdish.domain.dto.DishDTO;
import com.tagdish.domain.dto.MessageDTO;
import com.tagdish.domain.dto.NotificationDTO;
import com.tagdish.domain.dto.RestaurantDTO;
import com.tagdish.domain.dto.RestaurantDishDTO;
import com.tagdish.domain.dto.search.SearchResultDTO;
import com.tagdish.domain.elasticsearch.Dish;
import com.tagdish.domain.elasticsearch.Restaurant;
import com.tagdish.domain.location.Address;

public class TestTagDishJsonBuilder {	
	
	public static void main(String[] args) {
		 
		NotificationDTO n = new NotificationDTO();
		n.setAction(TagDishDomainConstant.VIEW_DISH_DETAIL_NOTIFY_TYPE);
		n.setEntityId("1");
		n.setTimestamp(System.currentTimeMillis());
		n.setTrasactionId("abc");
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(n));
	}
	public static void mainDish(String[] args) {
		
		Dish dish = new Dish();
		
		dish.setDishName("Name of Dish in Bold" + 1);
		dish.setDishId(1l);
		dish.setPrice(10.25f);
		
		dish.setCalories(360);
		dish.setCategory("Appetizer");
		dish.setCuisine("French - Italian - Chinese");
		dish.setMethodOfPrepration("Grilled");
		dish.setDishType("Pizza");
		
		dish.setSourLevel(2);
		dish.setSweetLevel(2);
		dish.setSavoryLevel(2);
		dish.setSpicyLevel(3);
		
		dish.setImageDishUrl("http://www.tagdish.com/tagdish/restaurant/dish/dish_name.jpg");
		dish.setIngredients("ingredients");
		dish.setDescription("description");
		dish.setPrice(10.25f);
		dish.setRating(3.5f);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(dish));
	}

	public static void mainRestaurant(String[] args) {
		
		Restaurant restaurant = new Restaurant();
		
		Address address = new Address();
		restaurant.setRestaurantId(1l);
		restaurant.setRestaurantName("Maggianos Restaurant" + 1);
		restaurant.setRestaurantType("Truck");
		restaurant.setImageUrl("http://www.tagdish.com/tagdish/restaurant/restaurant_name.jpg");
		restaurant.setAvgCostPerPerson(22f);
		restaurant.setHourOfOperation("11:00AM - 15:00PM and 18:00PM - 22:00PM");
		restaurant.setDetail("detail");
		restaurant.setParkingDesc("Free Parking - Street Parking - Valet Opt");
		restaurant.setParkingFee(2.0f);
		restaurant.setPhoneNumber(31090000000l);
		restaurant.setYelpRating(2.0f);
		
		ArrayList<String> paymentList = new ArrayList<String>();
		paymentList.add("Visa");
		paymentList.add("AMEX");
		restaurant.setPaymentList(paymentList);
		
		restaurant.setAddress(address);
		
		address.setStreetName("El Segundo Blvd");
		address.setStreetNumber("2240");
		address.setCity("El Segundo");
		address.setState("CA");
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(restaurant));
	}
	
	public static void mainRestaurantDish(String[] args) {
			
		RestaurantDishDTO restaurantDishDTO = null;
		DishDTO dishDTO = null;
		RestaurantDTO restaurantDTO = null;
		Address address = null;

		restaurantDishDTO = new RestaurantDishDTO();
		dishDTO = new DishDTO();
		restaurantDTO = new RestaurantDTO();
		
		restaurantDishDTO.setDishDTO(dishDTO);
		restaurantDishDTO.setRestaurantDTO(restaurantDTO);
		
		dishDTO.setDishName("Name of Dish in Bold" + 22);
		dishDTO.setDishId(22l);
		dishDTO.setPrice(10.25f);
		
		address = new Address();
		restaurantDTO.setRestaurantId(45l);
		restaurantDTO.setRestaurantName("Maggianos Restaurant" + 5);
		restaurantDTO.setRestaurantType("Truck");
		restaurantDTO.setPhoneNumber(31090000000l);
		restaurantDTO.setAddress(address);
		address.setCity("El Segundo");
		address.setState("CA");
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(restaurantDishDTO));;
		
	}	
	
	public static void mainSearchResult(String[] args) {
		
		SearchResultDTO tagDishResultDTO = new SearchResultDTO();
		
		LinkedList<RestaurantDishDTO> tagDishSearchDTOList = new LinkedList<RestaurantDishDTO>();
		
		RestaurantDishDTO tagDishSearchDTO = null;
		DishDTO dishDTO = null;
		RestaurantDTO restaurantDTO = null;
		Address address = null;
		for (int i = 0; i < 10; i++) {
			
			tagDishSearchDTO = new RestaurantDishDTO();
			dishDTO = new DishDTO();
			restaurantDTO = new RestaurantDTO();
			
			tagDishSearchDTO.setDishDTO(dishDTO);
			tagDishSearchDTO.setRestaurantDTO(restaurantDTO);
			
			dishDTO.setDishName("Name of Dish in Bold" + i);
			dishDTO.setDishId((long)i);
			dishDTO.setPrice(10.25f);
			
			address = new Address();
			restaurantDTO.setRestaurantId((long)i);
			restaurantDTO.setRestaurantName("Maggianos Restaurant" + i);
			restaurantDTO.setRestaurantType("Truck");
			restaurantDTO.setPhoneNumber(31090000000l);
			restaurantDTO.setAddress(address);
			address.setCity("El Segundo");
			address.setState("CA");
			
			tagDishSearchDTOList.add(tagDishSearchDTO);
		}
		
		tagDishResultDTO.setDishRestaurantList(tagDishSearchDTOList);
		tagDishResultDTO.setTransactionId("1234676");
		tagDishResultDTO.setResultToDisplay(5);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(tagDishResultDTO));
		
	}
	
	public static void mainMessageDTO(String[] args) {
		
		MessageDTO msg = new MessageDTO("No Entity Found", "Error");
		Gson gson = new Gson();
		System.out.println(gson.toJson(msg));
		
	}
}