package com.example.locationMasterDemo.repository;

import com.example.locationMasterDemo.model.Location;
import com.example.locationMasterDemo.model.LocationEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LocationRepository extends ReactiveCrudRepository<LocationEntity, Integer> {

    @Query(value = "SELECT * FROM location_entity WHERE location_data->>'name' LIKE '%'||:name||'%'")
    Flux<LocationEntity> findByName(String name);

    @Query(value = "SELECT * FROM location_entity t,jsonb_array_elements(location_data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'code' = $1")
    Flux<LocationEntity> findByLocationCode(String code);

    @Query(value = "SELECT * FROM location_entity t,jsonb_array_elements(location_data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'codeType' = $1")
    Flux<LocationEntity> findByLocationType(String type);

    @Query(value = "SELECT * FROM location_entity WHERE location_data->>'geoType' = $1")
    Flux<LocationEntity> findByGeoType(String geotype);
}
