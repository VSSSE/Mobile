package org.dhbw.se.movietunes.extract;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


@RunWith(AndroidJUnit4.class)
public class ExtractorTest {

    Extractor codeUnderTest;

    @Before
    public  void setUp(){
        codeUnderTest = new Extractor();
    }


    @Test
    public void testExtractPlaylistIdFromSearchResult(){
        String responseString = readStringFromFile (R.raw.test_result_search_track);

        String result = codeUnderTest.extractPlaylistIdFromSearchResult(responseString);

        assertEquals( "6lwDOP2ZW0h2jOccLB0342", result);
    }


    @Test
    public void testEextractUserIdFromSearchResult (){
        String responseString = readStringFromFile (R.raw.test_result_search_track);

        String result = codeUnderTest.extractUserIdFromSearchResult (responseString);

        assertEquals( "moyomba", result);
    }


    private String readStringFromFile (int resourceId){
        try {
            Context appContext = InstrumentationRegistry.getTargetContext();
            InputStream in = appContext.getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder result = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append("\n");
            }


            reader.close();
            return result.toString();
        }
        catch (Exception e){
           throw new RuntimeException(e);
        }

    }

}
