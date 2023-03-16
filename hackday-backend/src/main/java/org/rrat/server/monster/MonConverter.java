package org.rrat.server.monster;

import org.rrat.server.dto.MonsterDTO;
import org.rrat.server.monster.model.Monster;

public class MonConverter {

    public static MonsterDTO convertToDto(Monster monster) {
        return new MonsterDTO(monster.getMonsterId(), monster.getMonsterName(),
                monster.getMonsterGroup(), monster.getMonsterSecondGroup());
    }

    public static Monster convertFromDto(MonsterDTO monsterDTO) {
        return new Monster(monsterDTO.monsterId(), monsterDTO.monsterName(),
                monsterDTO.monsterGroup(), monsterDTO.monsterSecondGroup());
    }
}
