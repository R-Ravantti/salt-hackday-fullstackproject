package org.rrat.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PairResponseDTO(@JsonProperty boolean pair) {
}
