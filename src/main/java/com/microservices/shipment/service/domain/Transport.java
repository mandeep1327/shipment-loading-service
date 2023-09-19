package com.microservices.shipment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "transport")
public class Transport implements Persistable<String> {

    @Id
    @Column("transport_id")
    private String transportId;

    @Column("current_load")
    private double currentLoad;

    @Column("capacity")
    private double capacity;

    @Column("dimension")
    private Dimension dimension;

    @Transient
    private boolean newTransport;

    @Override
    @Transient
    public boolean isNew() {
        return this.newTransport;
    }

    public Transport setAsNew(boolean isNew) {
        this.newTransport = isNew;
        return this;
    }

    @Override
    public String getId() {
        return this.transportId;
    }
}
