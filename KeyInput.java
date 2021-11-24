package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Space - start / pause the game
 * **/

public class KeyInput extends KeyAdapter
{
    private int counter = 0;

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            counter++;
            Scene.start = counter % 2 != 0;
            if(counter > 1) counter = 0;
        }
    }
}