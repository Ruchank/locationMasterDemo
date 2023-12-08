package com.example.locationMasterDemo.service;

import com.example.locationMasterDemo.model.Location;
import com.example.locationMasterDemo.model.LocationEntity;
import com.example.locationMasterDemo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Mono<Void> saveLocation(String locationData){
       return this.locationRepository.saveValues(UUID.randomUUID(), locationData);
    }

    public Flux<LocationEntity> searchByName(String name) {
        return locationRepository.findByName(name)
                .doOnError(data->{throw new RuntimeException("BadPayload in Name");});
    }

    public Flux<LocationEntity> searchByLocationCode(String code){
        return locationRepository.findByLocationCode(code)
                .doOnError(data->{throw new RuntimeException("BadPayload in locationcode");});
    }

    public Flux<LocationEntity> searchByLocationType(String type){
        return locationRepository.findByLocationType(type)
                .doOnError(data->{throw new RuntimeException("BadPayload in locationType");});
    }

    public Flux<LocationEntity> searchByGeoType(String type){
        return locationRepository.findByGeoType(type)
                .doOnError(data->{throw new RuntimeException("BadPayload in GeoType");});
    }
}
