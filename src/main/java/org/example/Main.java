package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.example.decider.DeciderExecutor;
import org.example.repository.DeciderTemplateMockImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        //testGetNestedProperty();
        String jsonResultA = "{\"score\": \"800\" }";
        String jsonResultB = "{\"employer\": { \"status\": \"ACTIVE\" }}";
        List<Step> listStep = List.of(new Step("3", "TELESING", "FINISHED" ),
                new Step("2", "THINK_DATA", "FINISHED" ),
                new Step("1", "BIGDATA", "FINISHED" ));
       Flow flow = new Flow("1", listStep);
       ObjectMapper objectMapper = new ObjectMapper();
       Transaction transaction = new Transaction("1", flow, objectMapper.readValue(jsonResultA, Object.class), objectMapper.readValue(jsonResultB, Object.class), new ArrayList<>());
       DeciderExecutor deciderExecutor = new DeciderExecutor(new DeciderTemplateMockImpl());

       //test
       String typeResponse = deciderExecutor.getTypeOfNextStep(transaction, listStep.get(listStep.size()-1));
       System.out.println(typeResponse);
    }

    public static void testGetNestedProperty() {
        String json = "{\"name\":\"John\",\"age\":25,\"address\":[{\"city\":\"New York\",\"country\":\"USA\"}, {\"city\":\"Boston\",\"country\":\"USA\"}]}";

        // Create an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert the JSON string to a Map object
            var map = mapper.readValue(json, Object.class);
            var city2 = PropertyUtils.getNestedProperty (map, "address.[1].city");
            System.out.println(city2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}