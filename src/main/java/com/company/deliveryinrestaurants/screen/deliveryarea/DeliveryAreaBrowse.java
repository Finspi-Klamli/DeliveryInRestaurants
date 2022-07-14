package com.company.deliveryinrestaurants.screen.deliveryarea;

import io.jmix.ui.screen.*;
import com.company.deliveryinrestaurants.entity.DeliveryArea;

@UiController("DeliveryArea.browse")
@UiDescriptor("delivery-area-browse.xml")
@LookupComponent("deliveryAreasTable")
public class DeliveryAreaBrowse extends StandardLookup<DeliveryArea> {
}