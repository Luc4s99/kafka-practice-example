package com.example.main.controller;

import com.example.main.model.Product;
import com.example.main.service.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private final MenuService menuService;

    public Controller(MenuService menuService) {

        this.menuService = menuService;
    }

    @RequestMapping("/new")
    @PostMapping
    public Product salvarPedido(@RequestBody Product p) {

        try {

            kafkaTemplate.send("kfk-menu-tpc", ow.writeValueAsString(p));
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }

        return menuService.save(p);
    }
}
