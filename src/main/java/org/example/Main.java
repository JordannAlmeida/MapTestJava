package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        testGetNestedProperty();
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