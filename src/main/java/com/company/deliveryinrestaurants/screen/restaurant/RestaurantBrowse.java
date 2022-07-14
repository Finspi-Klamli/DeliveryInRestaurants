package com.company.deliveryinrestaurants.screen.restaurant;

import com.company.deliveryinrestaurants.entity.DeliveryArea;
import io.jmix.mapsui.component.layer.style.GeometryStyle;
import io.jmix.mapsui.component.layer.style.GeometryStyles;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Restaurant.browse")
@UiDescriptor("restaurant-browse.xml")
@LookupComponent("restaurantsTable")
public class RestaurantBrowse extends StandardLookup<Restaurant> {

    @Autowired
    GeometryStyles geometryStyles;

    @Install(to = "map.restaurantLayer", subject = "tooltipContentProvider")
    private String mapRestaurantLayerTooltipContentProvider(Restaurant restaurant) {
        return restaurant.getName();
    }

    @Install(to = "map.deliveryAreaLayer", subject = "styleProvider")
    private GeometryStyle setDeliveryAreaStyleProvider(DeliveryArea deliveryArea){
        return geometryStyles.polygon()
                .setFillColor(String.format("#%s",deliveryArea.getColor()))
                .setFillOpacity(0.4)
                .setStrokeColor("#2CA860");
    }

    @Install(to = "map.restaurantLayer", subject = "styleProvider")
    private GeometryStyle setRestaurantStyleProvider(Restaurant restaurant){
        if(restaurant.getDeliveryArea() == null)
            return geometryStyles.point()
                    .withFontIcon(JmixIcon.SHOPPING_BASKET)
                    .setIconPathFillColor("#343aeb");
        return geometryStyles.point()
                .withFontIcon(JmixIcon.SHOPPING_BASKET)
                .setIconPathFillColor(String.format("#%s",restaurant.getDeliveryArea().getColor()));
    }
}