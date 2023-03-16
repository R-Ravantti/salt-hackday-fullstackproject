package org.rrat.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PairRequestDTO(@JsonProperty int first, @JsonProperty int second) {
}
