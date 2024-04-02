package me.dio.sdw2024.application;

import me.dio.sdw2024.domain.model.ChampionRec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {

    @Autowired
    private ListChampionsUseCase lstChampionUseCase;

    @Test
    public void testListChampions() {
        List<ChampionRec> lstChampions = lstChampionUseCase.findAll();
        assertEquals(12, lstChampions.size());
    }
}
