package com.technobitia.entities.examples;

import com.technobitia.entities.client.EntityClient;
import com.technobitia.entities.exceptions.EntityException;
import com.technobitia.entities.model.Entity;
import com.technobitia.entities.request.EntityRequest;

public class EntityExtractorApp {

    public static void main(String[] args) throws EntityException {
        String name = "Susan Cain";
        EntityRequest request = new EntityRequest.Builder(name)
                                                 .build();
        EntityClient client = new EntityClient();
        Entity recipe = client.extractEntityInformation(request);
        System.out.println(recipe);

    }

}
