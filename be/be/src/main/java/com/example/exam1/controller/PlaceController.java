package com.example.exam1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.exam1.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin("*") // Cho phép gọi API từ Flutter
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }
}

