package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.service.Interface.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("sport")
public class SportController {

    private final SportService sportService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItem(@RequestBody Item item) throws InvalidEntity {
        sportService.saveItem(item);
    }

    @PutMapping("/items")
    public ResponseEntity<?> updateItem (@RequestBody Item item) /*throws InvalidEntity*/ {
        try {
            sportService.updateItem(item);
            return ResponseEntity.noContent().build();
        } catch (InvalidEntity | InvalidOperationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getOneItemById(@PathVariable Long id)/* throws InvalidEntity*/ {
        try {
            return ResponseEntity.ok(sportService.getOneItemById(id));
        } catch (InvalidEntity | InvalidOperationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items/all-items")
    public ResponseEntity<?> getAllItems() {
        return new ResponseEntity<>(sportService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/items/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<>(sportService.count(), HttpStatus.OK);
    }

}
