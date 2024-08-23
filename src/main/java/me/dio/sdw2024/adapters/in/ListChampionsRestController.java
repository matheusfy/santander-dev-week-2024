package me.dio.sdw2024.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.ListChampionsUseCase;
import me.dio.sdw2024.domain.model.ChampionRec;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Campeões", description = "Endpoints para acessar o domínio de campeões do lol")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @CrossOrigin(origins = "https://127.0.0.1:5500/")
    @GetMapping
    public List<ChampionRec> findAll(){
        return useCase.findAll();
    }
}
