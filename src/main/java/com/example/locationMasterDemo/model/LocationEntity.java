package com.example.locationMasterDemo.model;
import com.example.locationMasterDemo.serializer.LocationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.r2dbc.postgresql.codec.Json;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.Column;



import java.util.UUID;


@Data
@Entity(name = "location")
@Table
public class LocationEntity {
    @Id
    private UUID id;
    @Column(name = "locationData")
    @JsonSerialize(using = LocationSerializer.class)
    private Json locationData;


    public LocationEntity(UUID id, Json locationData) {
        this.id = id;
        this.locationData = locationData;
    }
}
