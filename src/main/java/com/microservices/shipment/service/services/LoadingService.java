package com.microservices.shipment.service.services;

import com.microservices.shipment.service.domain.Dimension;
import com.microservices.shipment.service.domain.LoadAssignment;
import com.microservices.shipment.service.domain.Transport;
import com.microservices.shipment.service.repositories.LoadAssignmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadingService {
    private final TransportService availableTransports;
    private final ShipmentService unassignedShipment;
    private final LoadAssignmentRepository loadAssignmentRepository;

    public Mono<String> asignmentShipment() {
        var sortedTransportList = availableTransports.getTransports()
                .collectSortedList(Comparator.comparingDouble(Transport::getCapacity))
                .switchIfEmpty(Mono.error(new Exception("No Record")));

        var loadAssignment = unassignedShipment.getShipments()
                .flatMap(shipment -> sortedTransportList
                        .map(transport -> transport.stream()
                                .filter(tr -> (tr.getCapacity() - tr.getCurrentLoad()) >= shipment.getWeight())
                                .filter(dm -> getVolume(dm.getDimension()) >= getVolume(shipment.getDimension()))
                                .findFirst())
                        .map(transport -> LoadAssignment.builder()
                                .shipment(shipment)
                                .transport(transport.get()).build()))                                     //Availabe Transport
                .collectList();
        var assignedList = loadAssignment
                .map(loadAssignments -> loadAssignments
                        .stream().map(loadAssignmentRepository::save));
        return Mono.just("successfully assigned shipment to transport " +
                assignedList.doOnNext(a -> log.info("assigned list {}", a.count())));
    }

    private double getVolume(Dimension dimension) {
        return dimension.getHeight() * dimension.getWidth() * dimension.getLength();
    }

    public Flux<LoadAssignment> getShipments() {
        return loadAssignmentRepository.findAll()
                .switchIfEmpty(Flux.error(new Exception("NO Record Found")));
    }
}
