package me.dio.sdw2024.application;

import me.dio.sdw2024.adapters.in.GlobalExceptionHandler;
import me.dio.sdw2024.domain.exception.ChampionNotFoundException;
import me.dio.sdw2024.domain.model.ChampionRec;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {

    public String askChampion(Long championId, String question) {

        ChampionRec champion = repository.findById(championId).orElseThrow(
                    () -> new ChampionNotFoundException(championId));
        

        //TODO: Evoluir a lógica para considerar a integração com IAs generativas.

        return champion.generateContextByQuestion(question);
    }
}
