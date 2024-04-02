package me.dio.sdw2024.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.AskChampionUseCase;
import org.springframework.web.bind.annotation.*;


@Tag(name="Pergunta", description = "Faz uma pergunta ao campeão")
@RestController
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {

    @PostMapping("/{championId}/{question}")
    public askChampionResponse askChampion(
            @PathVariable Long championId,
            @RequestBody askChampionRequest request){

        String answer = useCase.askChampion(championId, request.question());

        return new askChampionResponse(answer);
    }

    public record askChampionRequest(String question){}
    public record askChampionResponse(String answer){}

}
