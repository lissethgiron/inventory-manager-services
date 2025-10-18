package com.lisseth.inventory.common.application.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Setter
public class JsonApiResponse<T> {

    private Object data;

    public JsonApiResponse(String type, String id, T attributes) {
        this.data = Map.of(
                "type", type,
                "id", id,
                "attributes", attributes
        );
    }

    public JsonApiResponse(String type, int status, Map<String, String>  errors) {
        this.data = Map.of(
                "type", type,
                "status", status,
                "errors", errors
        );
    }

    public JsonApiResponse(String type, List<String> ids, List<T> resources) {
        if (resources.size() != ids.size()) {
            throw new IllegalArgumentException("resources y ids deben tener el mismo tamaño");
        }

        this.data = IntStream.range(0, resources.size())
                .mapToObj(i -> Map.of(
                        "type", type,
                        "id", ids.get(i),
                        "attributes", resources.get(i)
                ))
                .toList(); // Java 16+ para convertir a lista inmutable

    }
}
