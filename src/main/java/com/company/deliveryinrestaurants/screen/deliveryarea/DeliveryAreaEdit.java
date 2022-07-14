package com.company.deliveryinrestaurants.screen.deliveryarea;

import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.DeliveryArea;

@UiController("DeliveryArea.edit")
@UiDescriptor("delivery-area-edit.xml")
@EditedEntityContainer("deliveryAreaDc")
public class DeliveryAreaEdit extends StandardEditor<DeliveryArea> {
}