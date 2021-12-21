package com.example.demo.dao;

import com.example.demo.model.Plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantDao {

    int insertPlant(UUID id, Plant plant);

    default int insertPlant(Plant plant){
        UUID id = UUID.randomUUID();
        return insertPlant(id, plant);
    }

    List<Plant> selectAllPlants();

    Optional<Plant> selectPlantById(UUID id);

    int deletePlantById(UUID id);

    int updatePlantById(UUID id, Plant plant);


}
