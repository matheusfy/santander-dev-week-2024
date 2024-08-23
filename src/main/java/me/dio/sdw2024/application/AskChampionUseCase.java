package me.dio.sdw2024.application;

import me.dio.sdw2024.domain.exception.ChampionNotFoundException;
import me.dio.sdw2024.domain.model.ChampionRec;
import me.dio.sdw2024.domain.ports.ChampionsRepository;
import me.dio.sdw2024.domain.ports.GenerativeAiService;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiService genAiApi) {

    public String askChampion(Long championId, String question) {

        ChampionRec champion = repository.findById(championId).orElseThrow(
                    () -> new ChampionNotFoundException(championId));

        System.out.println("%s".formatted(question));
        String objective = """ 
                Atue como um assistente com a habilidade de se como comportar como os campeões do League of Legends (LOL).
                Responda as perguntas incorporando a personalidade e linguajar de cada campeão.
                Segue a pergunta, o nome do campeão e a sua respectiva lore (história):
                """;
        String championContext = champion.generateContextByQuestion(question);

        return genAiApi.generateContent(objective, championContext);
    }
}
