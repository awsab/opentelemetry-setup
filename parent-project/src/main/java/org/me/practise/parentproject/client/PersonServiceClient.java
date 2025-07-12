package org.me.practise.parentproject.client;

import org.me.practise.parentproject.config.FeignTracingConfig;
import org.me.practise.parentproject.dto.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "person-service",
    url = "http://localhost:8090/api", // base URL for person-service
    configuration = FeignTracingConfig.class
)
public interface PersonServiceClient {
    @GetMapping("/person/{personId}")
    PersonResponse getPersonByPersonId(@PathVariable("personId") Long personId);
}