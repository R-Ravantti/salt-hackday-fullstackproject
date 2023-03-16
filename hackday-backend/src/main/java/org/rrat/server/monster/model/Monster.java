package org.rrat.server.monster.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salt_monsters")
public class Monster {
    @Id
    @Column(name="monster_id")
    private Integer monsterId;

    @Column(name="monster_name")
    @NotNull
    private String monsterName;

    @Column(name="monster_group")
    @NotNull
    private String monsterGroup;

    @Column(name="monster_second_group")
    private String monsterSecondGroup;

    public Monster() {
    }

    public Monster(Integer monsterId, String monsterName, String monsterGroup, String monsterSecondGroup) {
        this.monsterId = monsterId;
        this.monsterName = monsterName;
        this.monsterGroup = monsterGroup;
        this.monsterSecondGroup = monsterSecondGroup;
    }

    public String getMonsterGroup() {
        return monsterGroup;
    }

    public String getMonsterSecondGroup() {
        return monsterSecondGroup;
    }

    public Integer getMonsterId() { return monsterId; }

    public String getMonsterName() { return monsterName; }
}
