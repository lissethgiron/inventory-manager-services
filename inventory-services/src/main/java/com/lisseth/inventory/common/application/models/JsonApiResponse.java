package com.lisseth.product.common.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonApiResponse<T> {

    private Object data;
    private Object meta;

    public JsonApiResponse(String type, String id, T attributes) {
        this.data = Map.of(
                "type", type,
                "id", id,
                "attributes", attributes
        );
    }

    public JsonApiResponse(String type, List<String> ids, List<T> resources, Map<String, Object> meta) {
        if (resources == null || ids == null || resources.size() != ids.size()) {
            throw new IllegalArgumentException("Resource and id lists must be the same size");
        }

        this.data = IntStream.range(0, resources.size())
                .mapToObj(i -> Map.of(
                        "type", type,
                        "id", ids.get(i),
                        "attributes", resources.get(i)
                ))
                .toList();
        this.meta = meta;
    }

    public JsonApiResponse(String type, int status, Map<String, String>  errors) {
        this.data = Map.of(
                "type", type,
                "status", status,
                "errors", errors
        );
    }
}
