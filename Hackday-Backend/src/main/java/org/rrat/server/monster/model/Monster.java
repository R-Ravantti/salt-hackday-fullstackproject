package org.rrat.server.monster.model;

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
}
