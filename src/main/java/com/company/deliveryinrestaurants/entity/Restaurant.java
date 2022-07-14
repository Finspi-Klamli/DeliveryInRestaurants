package com.company.deliveryinrestaurants.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.maps.Geometry;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "RESTAURANT", indexes = {
        @Index(name = "IDX_RESTAURANT_DELIVERY_AREA", columnList = "DELIVERY_AREA_ID")
})
@Entity
public class Restaurant {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Composition
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    @NotNull
    private String address;

    @Geometry
    @Column(name = "LOCATION", nullable = false)
    @NotNull
    private Point location;

    @JoinColumn(name = "DELIVERY_AREA_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private DeliveryArea deliveryArea;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public DeliveryArea getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(DeliveryArea deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}