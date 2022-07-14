package com.company.deliveryinrestaurants.screen.order;

import com.company.deliveryinrestaurants.entity.DeliveryArea;
import com.company.deliveryinrestaurants.entity.Restaurant;
import io.jmix.mapsui.component.layer.style.GeometryStyle;
import io.jmix.mapsui.component.layer.style.GeometryStyles;
import io.jmix.ui.component.EntitySuggestionField;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.Order;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Optional;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
    @Autowired
    private GeometryStyles geometryStyles;
    @Autowired
    private EntitySuggestionField<Restaurant> restaurantField;

    @Install(to = "map.restaurantLayer", subject = "tooltipContentProvider")
    private String mapRestaurantLayerTooltipContentProvider(Restaurant restaurant) {
        return restaurant.getName();
    }

    @Autowired
    private CollectionContainer<Restaurant> restaurantDc;

    @Subscribe("locationField")
    public void onLocationFieldValueChange(HasValue.ValueChangeEvent<Point> event) {
        Optional<Restaurant> min = restaurantDc.getItems().stream()
                .filter(restaurant -> restaurant.getDeliveryArea() != null && restaurant.getDeliveryArea().getPolygon().contains(event.getValue()))
                .min(Comparator.comparingDouble(value -> DistanceOp.distance(value.getLocation(), event.getValue())));
        if(min.isEmpty()){
            restaurantField.clear();
            return;
        }
        min.ifPresent(restaurant -> restaurantField.setValue(restaurant));
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