package me.dio.sdw2024.domain.model;

public record ChampionRec(
        Long id,
        String name,
        String role,
        String lore,
        String image_url
) {

    public String generateContextByQuestion(String question){
        return """ 
                Pergunta: %s
                Nome do campeão: %s
                Rota do campeão: %s
                História do campeão: %s
                """.formatted(question, this.name, this.role, this.lore);
    }

    public String toString(){
        return """ 
                id: %d
                Name: %s
                Rota: %s
                História: %s
                """.formatted(this.id, this.name, this.role, this.lore);
    }
}
