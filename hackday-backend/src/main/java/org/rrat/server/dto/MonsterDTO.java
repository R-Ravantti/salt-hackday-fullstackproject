package org.rrat.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MonsterDTO(@JsonProperty("monster_id") Integer monsterId,
                         @JsonProperty("monster_name") String monsterName,
                         @JsonProperty("monster_group") String monsterGroup,
                         @JsonProperty("monster_second_group") String monsterSecondGroup) {
}
