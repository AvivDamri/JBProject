package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.service.Interface.ElectricityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("electricity")
public class ElectricityController {

    private final ElectricityService electricityService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItem(@RequestBody Item item) throws InvalidEntity {
        electricityService.saveItem(item);
    }

    @PutMapping("/items")
    public ResponseEntity<?> updateItem (@RequestBody Item item) /*throws InvalidEntity, InvalidOperationException */{
        try {
            electricityService.updateItem(item);
            return ResponseEntity.noContent().build();
        } catch (InvalidEntity | InvalidOperationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getOneItemById(@PathVariable Long id) /*throws InvalidEntity, InvalidOperationException */{
        try {
            return ResponseEntity.ok(electricityService.getOneItemById(id));
        } catch (InvalidEntity | InvalidOperationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items/all-items")
    public ResponseEntity<?> getAllItems() {
        return ResponseEntity.ok(electricityService.getAllItems());
    }

    @GetMapping("/items/count")
    @ResponseStatus(HttpStatus.OK)
    public long count() {
        return electricityService.count();
    }
}
