package me.dio.sdw2024.domain.exception;

public class ChampionNotFoundException extends RuntimeException {
    public ChampionNotFoundException(Long championId) {
        super("Champion with id: %d not found.".formatted(championId));
    }
}
