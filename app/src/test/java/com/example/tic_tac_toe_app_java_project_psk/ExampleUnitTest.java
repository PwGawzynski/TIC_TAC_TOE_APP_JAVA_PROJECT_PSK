package com.example.tic_tac_toe_app_java_project_psk;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import AI.AIUtil;

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
    @Test
    public  void check_winner_works(){
        char board[][] = {{ '_', '_', '_' },//00 01 02
                { '_', '_', '_' },//10 11 12
                { '_', '_', '_' }};//20 21 21
        Assert.assertEquals(true, AIUtil.isMovesLeft(board));

    }
}