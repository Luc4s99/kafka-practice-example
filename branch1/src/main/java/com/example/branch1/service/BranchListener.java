package com.example.branch1.service;

import com.example.branch1.model.Change;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BranchListener {

    ObjectMapper mapper = new ObjectMapper();
    MenuService menuService;

    public BranchListener(MenuService menuService) {

        this.menuService = menuService;
    }

    @KafkaListener(topics = "kfk-menu-tpc", groupId = "branch1-menu-group")
    public void listen(@Payload String message) {
    //public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {

        //When you don't have a requirement to consume some messages in the same order as they were produced then not specifying a key is the best option
        //Getting the key just for educational purposes

        System.out.println("************************");
        System.out.println("\n\nSincronizando banco:");

        try {

            Change chnReceived = mapper.readValue(message, Change.class);

            if(chnReceived.getType() != null && chnReceived.getType().equals("POST")) {

                System.out.println("\tInserindo registro...");
                menuService.save(chnReceived.getProduct());
            }else if(chnReceived.getType() != null && chnReceived.getType().equals("PUT")) {

                System.out.println("\tAtualizando registro...");
                menuService.save(chnReceived.getProduct());
            }else if(chnReceived.getType() != null && chnReceived.getType().equals("DELETE")) {

                System.out.println("\tRemovendo registro...");
                menuService.delete(chnReceived.getProduct());
            }else {

                System.out.println("\tAtualizando...");
                menuService.save(chnReceived.getProduct());
            }
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }
}
