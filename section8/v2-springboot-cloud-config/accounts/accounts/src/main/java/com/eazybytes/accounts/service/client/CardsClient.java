package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardsDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api")
public interface CardsClient {
//    what declared here should match whats in cards controller -- path and return type
    @GetExchange(value = "/fetch", accept = "application/json")
    ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam String mobileNumber);

}
