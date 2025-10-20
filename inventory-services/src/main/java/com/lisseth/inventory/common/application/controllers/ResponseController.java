package com.lisseth.inventory.common.application.controllers;

import com.lisseth.inventory.common.application.models.JsonApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ResponseController {

    public static <T> ResponseEntity<JsonApiResponse<T>> success(String type, String id, T attributes) {
        log.info("success {} {} {}", type, id, attributes);
        return ResponseEntity.ok(new JsonApiResponse<>(type, id, attributes));
    }

    public static <T> ResponseEntity<JsonApiResponse<T>> created(String type, String id, T attributes) {
        log.info("created {} {} {}", type, id, attributes);
        URI location = URI.create("/products/" + id);
        return ResponseEntity.created(location).body(new JsonApiResponse<>(type, id, attributes));
    }

    public static  <T> ResponseEntity<JsonApiResponse<List<T>>> successList(
            String type,
            List<String> ids,
            List<T> resources,
            Map<String, Object> meta
    ) {
        return ResponseEntity.ok((JsonApiResponse<List<T>>) new JsonApiResponse<>(type, ids, resources, meta));
    }

    public static ResponseEntity<JsonApiResponse<Object>> error(String type, int status, Map<String, String> errors) {
        JsonApiResponse<Object> response = new JsonApiResponse<>(type, status, errors);
        return ResponseEntity.status(status).body(response);
    }
}
