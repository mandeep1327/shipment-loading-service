package com.microservices.shipment.service.model;

import com.microservices.shipment.service.domain.Dimension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ShipmentDetails {

    private String loadId;
    private String shipmentId;
    private String transportId;

}
