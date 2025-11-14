package com.example.branch2.service;

import com.example.branch2.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BranchListener {

    ObjectMapper mapper = new ObjectMapper();
    MenuService menuService;

    public BranchListener(MenuService menuService) {

        this.menuService = menuService;
    }

    @KafkaListener(topics = "kfk-menu-tpc", groupId = "branch2-menu-group")
    public void listen(String message) {

        System.out.println("Produto recebido para a criação: " + message);

        try {

            Product prodReceived = mapper.readValue(message, Product.class);

            menuService.save(prodReceived);
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }
}
