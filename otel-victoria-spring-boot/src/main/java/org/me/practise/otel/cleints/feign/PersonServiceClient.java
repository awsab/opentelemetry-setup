/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 09/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package org.me.practise.otel.cleints.feign;

import org.me.practise.otel.cleints.request.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "person-service", url = "${person.service.url}")
public interface PersonServiceClient {

    @GetMapping("/person/{personId}")
    PersonResponse getPersonByPersonId(@PathVariable Long personId);
}
