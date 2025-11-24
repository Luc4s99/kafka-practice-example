package com.example.main;

import com.example.main.model.Change;
import com.example.main.model.Product;
import com.example.main.service.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Factory {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private MenuService menuService;

    public Factory(MenuService menuService) {

        this.menuService = menuService;
    }

    public void start() {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Integer productId = 0;

        while(true) {

            try {

                System.out.println("Alteração realizada!");

                String typeGenerated = randomChangeTypeGenerator();
                Product chosenProduct;
                Change change = null;

                if(typeGenerated.equals("DELETE")) {

                    chosenProduct = menuService.randomProduct();

                    if(chosenProduct != null) {

                        change = new Change(typeGenerated, chosenProduct);
                        menuService.delete(change.getProduct());
                    }
                }else if(typeGenerated.equals("PUT")) {

                    chosenProduct = menuService.randomProduct();

                    if(chosenProduct != null) {

                        Integer chosenProductId = chosenProduct.getIdProduct();

                        chosenProduct = randomProductGenerator(chosenProductId);

                        change = new Change(typeGenerated, chosenProduct);
                        menuService.save(change.getProduct());
                    }
                }else {

                    productId++;

                    chosenProduct = randomProductGenerator(productId);

                    change = new Change(typeGenerated, chosenProduct);
                    menuService.save(change.getProduct());
                }

                if(change != null) {

                    kafkaTemplate.send("kfk-menu-tpc", ow.writeValueAsString(change));

                    Thread.sleep(3000);
                }
            } catch (InterruptedException | JsonProcessingException e) {

                throw new RuntimeException(e);
            }
        }
    }

    private String randomChangeTypeGenerator() {

        Random random = new Random();
        List<String> types = Arrays.asList("POST", "PUT", "DELETE");

        return types.get(random.nextInt(types.size()));
    }

    private Product randomProductGenerator(Integer id) {

        Random random = new Random();
        List<String> productNames = Arrays.asList("PROD 1", "PROD 2", "PROD 3", "PROD 4", "PROD 5");

        return new Product(id, productNames.get(random.nextInt(productNames.size())), random.nextDouble(100), random.nextDouble(100));
    }
}

