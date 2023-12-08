package com.example.locationMasterDemo.model;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Data
@Table("location")
public class LocationEntity {

    private UUID id;

    private String locationData;
}
