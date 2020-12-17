package com.example.demo.web.controllers;

import com.example.demo.dto.ItemDto;
import com.example.demo.service.Interface.AdminService;
import com.example.demo.serviceDto.AdminServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/items")
public class AdminController {

    private final AdminService adminService;
    private final AdminServiceDto adminServiceDto;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> saveItem(@RequestBody @Valid ItemDto item) {
        adminServiceDto.saveItem(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemDto item) {
        adminServiceDto.updateItem(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SneakyThrows
    @DeleteMapping
    public ResponseEntity<?> deleteItem(@RequestBody ItemDto item) {
        adminServiceDto.deleteItem(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItemById(@PathVariable Long id) {
        return new ResponseEntity<>(adminServiceDto.getOneItem(id), HttpStatus.OK);
    }

    @GetMapping("/all-items")
    public ResponseEntity<?> getAllItems() {
        return new ResponseEntity<>(adminServiceDto.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countItems() {
        return new ResponseEntity<>(adminServiceDto.countItems(), HttpStatus.OK);
    }
}
