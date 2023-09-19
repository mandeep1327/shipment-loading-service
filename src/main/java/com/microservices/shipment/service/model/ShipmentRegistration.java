package com.microservices.shipment.service.model;

import com.microservices.shipment.service.domain.Dimension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentRegistration {

    private Dimension dimension;
    private String source;
    private String destination;
    private double weight;
    private Instant shipmentDate;
    private String id;

}
