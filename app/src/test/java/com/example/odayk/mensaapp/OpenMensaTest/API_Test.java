package com.example.odayk.mensaapp.OpenMensaTest;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.dataprovider.OpenMensaOrg;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class API_Test {

    private OpenMensaAPI testAPI;

    @Before
    public void initialize(){
        this.testAPI = new OpenMensaAPI(new OpenMensaOrg());

    }

    @Test
    public void canteen_junit(){
        String response = null;
        try {
            response = testAPI.getCanteens();
            System.out.print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(response,notNullValue());
    }

}
