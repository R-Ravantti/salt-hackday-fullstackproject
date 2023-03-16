package org.rrat.server.monster.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MonRepository {
    @Autowired
    private MonsterDbRepository repo;

    public List<Monster> getMonsters() {return Streamable.of(repo.findAll()).toList();}

    public Monster getMonsterById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Monster saveMonster(Monster monster) throws IllegalArgumentException {
        if(getMonsterById(monster.getMonsterId()) != null) {
            throw new IllegalArgumentException();
        }
        return repo.save(monster);
    }

    public void deleteMonsterById(int id) {
        repo.deleteById(id);
    }
}
