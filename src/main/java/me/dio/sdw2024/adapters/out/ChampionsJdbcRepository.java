package me.dio.sdw2024.adapters.out;

import me.dio.sdw2024.domain.model.ChampionRec;
import me.dio.sdw2024.domain.ports.ChampionsRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<ChampionRec> rowMapper;


    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> new ChampionRec(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_url")
        );
    }

    @Override
    public List<ChampionRec> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS ", rowMapper);
    }

    @Override
    public Optional<ChampionRec> findById(Long id) {
        String sqlQuery = "SELECT * FROM CHAMPIONS WHERE ID = ?";


        List<ChampionRec> lstChampion = jdbcTemplate.query(sqlQuery, rowMapper, id);

        return lstChampion.stream().findFirst();
    }
}
