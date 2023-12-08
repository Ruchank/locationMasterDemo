package com.example.locationMasterDemo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LocationConfigRouter {

  /*  @Autowired
    private LocationDBHandler locationDBHandler;
*/

  /*  @Bean
    public RouterFunction<ServerResponse> routerFunction() {
       /return route().path(
                        "/router", builder ->
                                builder.GET("/id/{id}", locationDBHandler::getLocation)
                                        .GET("/name/{locName}", locationDBHandler::getLocByName)
                                        .GET("/locationType/{locType}", locationDBHandler::getLocByType)
                                        .GET("/locCode/{locCode}", locationDBHandler::getLocByCode)
                                        .GET("/locCodeType/{locCodeType}", locationDBHandler::getLocByCodeType))
                .build();
    }*/
}
