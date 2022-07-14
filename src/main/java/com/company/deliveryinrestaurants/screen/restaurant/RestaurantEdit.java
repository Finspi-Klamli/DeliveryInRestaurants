package com.company.deliveryinrestaurants.screen.restaurant;

import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.Restaurant;

@UiController("Restaurant.edit")
@UiDescriptor("restaurant-edit.xml")
@EditedEntityContainer("restaurantDc")
public class RestaurantEdit extends StandardEditor<Restaurant> {
}