package com.example.locationMasterDemo.controller;

import com.example.locationMasterDemo.model.Location;
import com.example.locationMasterDemo.model.LocationEntity;
import com.example.locationMasterDemo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/location")

public class LocationController {
    @Autowired
    private LocationService locationService;

  /*  @PostMapping("/loadJson")
    public Mono<Void> loadJson(@RequestBody String message) {
        return locationService.saveLocation(message);
    }
*/
    @GetMapping("/byName/{name}")
    public Flux<LocationEntity> searchByName(@PathVariable("name") String name) throws Exception {
        if(name==null){
            throw new Exception("Name parameter is null");
        }
        return locationService.searchByName(name);
    }

    @GetMapping("/byCode/{code}")
    public Flux<LocationEntity> searchByLocationCode(@PathVariable("code") String code){
        return locationService.searchByLocationCode(code);
    }

    @GetMapping("/byCodeType/{type}")
    public Flux<LocationEntity> searchByLocationType(@PathVariable("type") String type){
        return locationService.searchByLocationType(type);
    }

    @GetMapping("/byGeoType/{type}")
    public Flux<LocationEntity> searchByGeoType(@PathVariable("type") String type){
        return locationService.searchByGeoType(type);
    }

}
