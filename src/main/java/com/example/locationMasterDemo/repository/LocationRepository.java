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
    @Query("INSERT INTO location(id, locationData) values (:id, CAST( :locationData as jsonb))")
    Mono<Void> saveValues(@Param("id") UUID id, @Param("locationData") String locationData);

    @Query(value = "SELECT * FROM location WHERE data->>'name' LIKE '%'||$1||'%'")
    Flux<LocationEntity> findByName(String name);

    @Query(value = "SELECT * FROM location t,jsonb_array_elements(data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'code' = $1")
    Flux<LocationEntity> findByLocationCode(String code);

    @Query(value = "SELECT * FROM location t,jsonb_array_elements(data->'alternateCodes') alternateCodes " +
            "WHERE alternateCodes->>'codeType' = $1")
    Flux<LocationEntity> findByLocationType(String type);

    @Query(value = "SELECT * FROM location WHERE data->>'geoType' = $1")
    Flux<LocationEntity> findByGeoType(String geotype);
}
