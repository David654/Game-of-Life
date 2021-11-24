package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Left click - place a cell
 * Right click - remove a cell
 * **/

public class MouseInput extends MouseAdapter
{
    private final Handler handler;
    private int counter = 0;

    public MouseInput(Handler handler)
    {
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e)
    {
        int x = e.getX() / Settings.cell_size;
        int y = e.getY() / Settings.cell_size;
        int index = x * Settings.cols + y;

        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(!Scene.start)
            {
                for(Cell cell : handler.cells)
                {
                    if(handler.cells.indexOf(cell) == index)
                    {
                        cell.setAlive(true);
                    }
                }
            }
        }

        if(e.getButton() == MouseEvent.BUTTON3)
        {
            if(!Scene.start)
            {
                for(Cell cell : handler.cells)
                {
                    if(handler.cells.indexOf(cell) == index)
                    {
                        cell.setAlive(false);
                    }
                }
            }
        }
    }
}