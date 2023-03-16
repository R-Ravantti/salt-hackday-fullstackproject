package org.rrat.server.monster;

import org.rrat.server.dto.MonsterDTO;
import org.rrat.server.dto.PairRequestDTO;
import org.rrat.server.dto.PairResponseDTO;
import org.rrat.server.monster.model.Monster;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/monsters")
public class MonController {
    private final MonService service;

    public MonController(MonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MonsterDTO>> getAllMonsters() {
        List<MonsterDTO> monsterList = service.getMonsters().stream()
                .map(MonConverter::convertToDto).toList();
        return ResponseEntity.ok(monsterList);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<MonsterDTO> getMonster(@PathVariable int id) {
        try {
            Monster monster = service.getMonster(id);
            return ResponseEntity.ok(MonConverter.convertToDto(monster));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "compatible")
    public ResponseEntity<PairResponseDTO> checkPairCompatibility(@RequestBody PairRequestDTO req) {
        try {
            return ResponseEntity.ok(new PairResponseDTO(service.checkPair(req.first(), req.second())));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MonsterDTO> addMonster(@RequestBody MonsterDTO monsterDTO) {
        try {
            Monster addedMonster = service.saveMonster(MonConverter.convertFromDto(monsterDTO));
            return ResponseEntity.created(URI.create("/api/monsters/" + monsterDTO.monsterId()))
                    .body(MonConverter.convertToDto(addedMonster));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<MonsterDTO> deleteMonster(@PathVariable int id) {
        try {
            service.deleteMonster(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
