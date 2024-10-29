package com.nicoarbelaez.ratebook.endpoint.item.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nicoarbelaez.ratebook.endpoint.item.Item;
import com.nicoarbelaez.ratebook.endpoint.item.ItemService;
import com.nicoarbelaez.ratebook.endpoint.item.dto.ItemCreationDto;
import com.nicoarbelaez.ratebook.endpoint.item.dto.ItemResponseDto;
import com.nicoarbelaez.ratebook.endpoint.item.dto.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Iterable<ItemResponseDto>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.getAllItem().stream().map(ItemMapper::toResponseDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Item> item = itemService.getItemById(id);
        return item.map(value -> ResponseEntity.status(HttpStatus.OK).body(ItemMapper.toResponseDto(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemCreationDto dto) {
        Optional<Item> createdItem = itemService.createItem(dto);

        if (!createdItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ItemMapper.toResponseDto(createdItem.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Optional<Item> updatedItem = itemService.updateItem(id, item);

        if (!updatedItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(ItemMapper.toResponseDto(updatedItem.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id)
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
