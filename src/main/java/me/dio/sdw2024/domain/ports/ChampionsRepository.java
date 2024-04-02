package me.dio.sdw2024.domain.ports;

import me.dio.sdw2024.domain.model.ChampionRec;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {

    List<ChampionRec> findAll();

    Optional<ChampionRec> findById(Long id);
}
