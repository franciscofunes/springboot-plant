package com.example.demo.dao;

import com.example.demo.model.Plant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePlantDataAccessService implements PlantDao{

    private static List<Plant> DB = new ArrayList<>();

    @Override
    public int insertPlant(UUID id, Plant plant) {
        DB.add(new Plant(id, plant.getName()));
        return 1;
    }

    @Override
    public List<Plant> selectAllPlants() {
        return DB;
    }

    @Override
    public Optional<Plant> selectPlantById(UUID id) {
        return DB.stream()
                .filter(plant -> plant.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePlantById(UUID id) {
        Optional<Plant> plantMaybe= selectPlantById(id);
        if (plantMaybe.isEmpty()){
            return 0;
        }
        DB.remove(plantMaybe.get());
        return 1;
    }

    @Override
    public int updatePlantById(UUID id, Plant update) {
        return selectPlantById(id)
                .map(plant -> {
                    int indexOfPlantToUpdate = DB.indexOf(plant);
                    if (indexOfPlantToUpdate >= 0) {
                        DB.set(indexOfPlantToUpdate, new Plant(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
