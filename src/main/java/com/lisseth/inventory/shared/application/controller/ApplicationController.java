package com.lisseth.inventory.shared.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(produces = "application/vnd.api+json")
public class ApplicationController {

    @GetMapping("/ping")
    public RepresentationModel<?> ping() {
        var message = getMessage("ping()");
        log.info("method: ping() -> Message {}", message);
        return EntityModel.of(Map.of("message", message));
    }

    protected String getMessage(final String methodName) {

        return String.format("method: %s", methodName) +
                String.format(" -> Date: %s,", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss*SSSZZZZ").format(new Date()));
    }
}
