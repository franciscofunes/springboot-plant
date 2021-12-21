package com.example.demo.dao;

import com.example.demo.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PlantDataAccessService implements PlantDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlantDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPlant(UUID id, Plant plant) {
        return 0;
    }

    @Override
    public List<Plant> selectAllPlants() {
        final String sql = "SELECT id, name FROM plant";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Plant(id, name);
        });
    }

    @Override
    public Optional<Plant> selectPlantById(UUID id) {
        final String sql = "SELECT id, name FROM plant WHERE id = ?";
        Plant plant = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID plantId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Plant(plantId, name);
                });
        return Optional.ofNullable(plant);
    }

    @Override
    public int deletePlantById(UUID id) {
        return 0;
    }

    @Override
    public int updatePlantById(UUID id, Plant plant) {
        return 0;
    }
}
