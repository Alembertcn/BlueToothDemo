package com.android.bluetoothdemo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Pattern pattern = Pattern.compile("\\d+[.]\\d+");
        Matcher matcher = pattern.matcher("22.00");
        boolean b = matcher.find();
        assertEquals(4, 2 + 2);
    }
}