package com.example.odayk.mensaapp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


/*    @Test
    public void test(){
        Date date = new Date();
//        int h = date.getHours();

        DateFormat f = new SimpleDateFormat("dd.MM.yyyy");

        String currentTime =  f.format(date);
        System.out.println(currentTime);
        System.out.println("@Hello");

      /*  Calendar kalender = Calendar.getInstance();
        SimpleDateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
        datumsformat.format(kalender.getTime());

        System.out.println(datumsformat);
        System.out.println("@Hello");*/
//    }
}
