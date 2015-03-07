package com.technobitia.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.entities.client.EntityClient;
import com.technobitia.entities.exceptions.EntityException;

@RunWith(MockitoJUnitRunner.class)
public class EntityClientTest {

    @InjectMocks
    private EntityClient entityClient;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() throws EntityException {
        entityClient.extractEntityInformation(null);
    }
}
