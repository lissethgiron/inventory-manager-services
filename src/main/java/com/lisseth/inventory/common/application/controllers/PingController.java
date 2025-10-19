package com.lisseth.inventory.common.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping(produces = "application/vnd.api+json")
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<JsonApiResponse<String>> ping() {
        var message = String.format("method: %s", "ping()") +
                String.format(" -> Date: %s,", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss*SSSZZZZ")
                        .format(new Date()));

        log.info("method: ping() -> Message {}", message);
        return ResponseController.success("ping", "ping-1", message);
    }
}
