package com.example.demo.service;

import com.example.demo.dao.PlantDao;
import com.example.demo.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlantService {

    private final PlantDao plantDao;

    @Autowired
    public PlantService(@Qualifier("postgres") PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public int addPlant(Plant plant) {
        return plantDao.insertPlant(plant);
    }

    public List<Plant> getAllPlants() {
        return plantDao.selectAllPlants();
    }

    public Optional<Plant> getPlantById(UUID id) {
        return plantDao.selectPlantById(id);
    }

    public int deletePlant(UUID id) {
        return plantDao.deletePlantById(id);
    }

    public int updatePlant(UUID id, Plant newPlant) {
        return plantDao.updatePlantById(id, newPlant);
    }
}
