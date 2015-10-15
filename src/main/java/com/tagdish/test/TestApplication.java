package com.tagdish.test;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tagdish.dao.elasticsearch.impl.ZipCodeQuery;
import com.tagdish.dao.repository.DishRepository;
import com.tagdish.dao.repository.RestaurantRepository;
import com.tagdish.dao.repository.ZipCodeRepository;
import com.tagdish.domain.elasticsearch.Dish;
import com.tagdish.domain.elasticsearch.Restaurant;
import com.tagdish.domain.location.Address;

public class TestApplication {
	
    // add dish to elastic search
    private void addSomeDishs(ApplicationContext applicationContext) {
    	
    	DishRepository dishRepo = applicationContext.getBean(DishRepository.class);
        Dish starWars = getFirstDish();
        dishRepo.save(starWars);

        Dish princessBride = getSecondDish();
        dishRepo.save(princessBride);
    }

    private Dish getSecondDish() {
        Dish secondDish = new Dish();
        secondDish.setDishId(23l);
        secondDish.setDishName("Biryani");
        
        Address address = new Address();
        address.setCity("Santa Monica");
        address.setState("CA");
        
        secondDish.setAddress(address);
        secondDish.setRestaurantType("Restaurant");
        

        return secondDish;
    }

    private Dish getFirstDish() {
        Dish firstDish = new Dish();
        firstDish.setDishId(12l);

        firstDish.setDishName("Pizza");
        
        firstDish.setRestaurantId(21l);
        Address address = new Address();
        address.setCity("Torrance");
        address.setState("CA");
        
        firstDish.setAddress(address);
        firstDish.setRestaurantType("Truck");

        return firstDish;
    }
    
    // add dish to elastic search
    private void addSomeRestaurants(ApplicationContext applicationContext) {
    	
    	RestaurantRepository restaurantRepo = applicationContext.getBean(RestaurantRepository.class);
    	Restaurant starWars = getFirstRestaurant();
    	restaurantRepo.save(starWars);

        Restaurant princessBride = getSecondRestaurant();
        restaurantRepo.save(princessBride);
    }

    private Restaurant getSecondRestaurant() {
    	Restaurant secondRestaurant = new Restaurant();
    	secondRestaurant.setRestaurantId(2l);
    	secondRestaurant.setRestaurantName("Pizza Hut");
    	Address address = new Address();
    	address.setCity("Torrance");
    	address.setZipcode(90505l);
    	secondRestaurant.setAddress(address);

        return secondRestaurant;
    }

    private Restaurant getFirstRestaurant() {
    	Restaurant firstRestaurant = new Restaurant();
    	firstRestaurant.setRestaurantId(4l);

    	firstRestaurant.setRestaurantName("Thai Restaurant");
    	
    	Address address = new Address();
    	address.setCity("El Segundo");
    	address.setZipcode(90503l);
    	firstRestaurant.setAddress(address);
    	ArrayList<String> paymentList = new ArrayList<String>();
    	paymentList.add("Visa123");
    	paymentList.add("Amex");
    	System.out.println(paymentList.size());
    	firstRestaurant.setPaymentList(paymentList);

        return firstRestaurant;
    }    
    
    private void searchDishs(ApplicationContext applicationContext)  {
    	
    	DishRepository dishRepo = applicationContext.getBean(DishRepository.class);
        //Lets query if we have a movie with Star Wars as name
        List < Dish > starWarsNameQuery = dishRepo.findByDishName("Star Wars");
        if(starWarsNameQuery != null) {
        	System.out.println("Content of star wars name query is {}" + starWarsNameQuery.size());	
        	
        	for (Dish dish : starWarsNameQuery) {
				System.out.println(dish.getDishId());
				System.out.println(dish.getDishName());
			}
        } else {
        	System.out.println("No result foudn");
        }
        

        //Lets query if we have a movie with The Princess Bride as name
        List < Dish >  brideQuery = dishRepo.findByDishName("The Princess Bride");
        if(brideQuery != null) {
        	System.out.println("Content of princess bride name query is {}" + brideQuery.size());
        	
        	for (Dish dish : brideQuery) {
				System.out.println(dish.getDishId());
				System.out.println(dish.getDishName());
			}

        } else {
        	System.out.println("No result foudn");
        }
    }
    
    private void searchRestaurant(ApplicationContext applicationContext)  {
    	
    	RestaurantRepository restaurantRepo = applicationContext.getBean(RestaurantRepository.class);
        //Lets query if we have a movie with Star Wars as name
        List < Restaurant > starWarsNameQuery = restaurantRepo.findByRestaurantName("Pizza Hut");
        if(starWarsNameQuery != null) {
        	System.out.println("Content of star wars name query is {}" + starWarsNameQuery.size());	
        	
        	for (Restaurant dish : starWarsNameQuery) {
				System.out.println(dish.getRestaurantId());
				System.out.println(dish.getAddress().getCity());
				
				if(dish.getPaymentList() != null && dish.getPaymentList().size() > 0) {
					System.out.println(dish.getPaymentList().get(0));
				}
			}
        } else {
        	System.out.println("No result foudn");
        }
        

        //Lets query if we have a movie with The Princess Bride as name
        List < Restaurant >  brideQuery = restaurantRepo.findByRestaurantName("Thai Restaurant");
        if(brideQuery != null) {
        	System.out.println("Content of star wars name query is {}" + brideQuery.size());	
        	
        	for (Restaurant dish : brideQuery) {
				System.out.println(dish.getRestaurantId());
				System.out.println(dish.getAddress().getCity());
				
				if(dish.getPaymentList() != null && dish.getPaymentList().size() > 0) {
					
					for (String payment : dish.getPaymentList()) {
						System.out.println(payment);	
					}
				}				
			}
        } else {
        	System.out.println("No result foudn");
        }
    }
	

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		
    	System.out.println(applicationContext.getBean(ZipCodeRepository.class).findByCityAndState("LAGRANGEVILLE", "NY").get(0).getZipCode());
		System.out.println(applicationContext.getBean(DishRepository.class).findByDishId(1l).getDishName());
		System.out.println(applicationContext.getBean(ZipCodeQuery.class).getZipCode(90503l).getHits().getHits().length);
		
		SearchResponse response = applicationContext.getBean(ZipCodeQuery.class).getZipCode(90503l);
		for(SearchHit searchHit : response.getHits()){
			if(response.getHits().getHits().length <= 0) {
				System.out.println("Nothing found");
			}
			
			System.out.println(searchHit.getId());
			System.out.println((String)searchHit.getSource().get("zipClass"));
			System.out.println((String)searchHit.getSource().get("city"));
			
			}
	}
}