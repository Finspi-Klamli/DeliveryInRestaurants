package com.company.deliveryinrestaurants.screen.order;

import com.company.deliveryinrestaurants.entity.DeliveryArea;
import com.company.deliveryinrestaurants.entity.Restaurant;
import io.jmix.mapsui.component.layer.style.GeometryStyle;
import io.jmix.mapsui.component.layer.style.GeometryStyles;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {

    @Autowired
    private GeometryStyles geometryStyles;

    @Install(to = "map.orderLayer", subject = "styleProvider")
    private GeometryStyle setOrderStyleProvider(Order order) {
        if(order.getRestaurant() == null)
            return geometryStyles.point()
                    .withFontIcon(JmixIcon.FIRST_ORDER)
                    .setIconPathFillColor("#3455eb");
        if(order.getExecuteFlag())
            return geometryStyles.point()
                    .withFontIcon(JmixIcon.FIRST_ORDER)
                    .setIconPathFillColor("#050505");
        return geometryStyles.point()
                .withFontIcon(JmixIcon.FIRST_ORDER)
                .setIconPathFillColor(String.format("#%s", order.getRestaurant().getDeliveryArea().getColor()));
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