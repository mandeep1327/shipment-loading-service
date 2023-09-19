package com.microservices.shipment.service.domain;

import com.microservices.shipment.service.model.TransportRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "load_assignment")
public class LoadAssignment implements Persistable<String> {
    @Id
    @Column("load_id")
    private String loadId;
    @Column("shipment")
    private Shipment shipment;
    @Column("transport")
    private Transport transport;

    @Override
    public String getId() {
        return loadId;
    }

    @Override
    public boolean isNew() {
        return loadId==null;
    }
}
