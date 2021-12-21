package com.example.demo.api;

import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/plant")
@RestController
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping
    public void addPlant(@Valid @NonNull @RequestBody Plant plant) {
        plantService.addPlant(plant);
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping(path = "{id}")
    public Plant getPlantById(@PathVariable("id") UUID id) {
        return plantService.getPlantById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        plantService.deletePlant(id);
    }

    @PutMapping(path = "{id}")
    public void updatePlant(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Plant plantToUpdate) {
        plantService.updatePlant(id, plantToUpdate);
    }
}
