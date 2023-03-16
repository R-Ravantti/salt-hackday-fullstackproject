package org.rrat.server.monster;

import org.rrat.server.monster.model.MonRepository;
import org.rrat.server.monster.model.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonService {
    @Autowired
    MonRepository repo;
    public MonService(){
    }
    public MonService(MonRepository repo) {
        this.repo = repo;
    }

    public List<Monster> getMonsters() {return repo.getMonsters();}

    public Monster getMonster(int id) { return repo.getMonsterById(id); }

    public boolean checkPair(int id1, int id2) {
        if(id1 == id2) {
            return true;
        }
        Monster monster1 = repo.getMonsterById(id1);
        Monster monster2 = repo.getMonsterById(id2);
        String[] groups1 = {monster1.getMonsterGroup(), monster1.getMonsterSecondGroup()};
        String[] groups2 = {monster2.getMonsterGroup(), monster2.getMonsterSecondGroup()};
        if(groups1[0].equals("Impossible") || groups2[0].equals("Impossible")) {
            return false;
        }
        for (String s1 : groups1) {
            for (String s2 : groups2) {
                if (s1 != null && s1.equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Monster saveMonster(Monster monster) {
        return repo.saveMonster(monster);
    }

    public void deleteMonster(int id) {
        repo.deleteMonsterById(id);
    }
}
