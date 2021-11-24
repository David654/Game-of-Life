package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Handler
{
    public ArrayList<Cell> cells = new ArrayList<>();

    public void add(Cell cell)
    {
        cells.add(cell);
    }

    public void update()
    {
        for(Cell cell : cells)
        {
            cell.update();
        }

        for(int i = 0; i < cells.size(); i++)
        {
            Cell cell = cells.get(i);
            if(Scene.start)
            {
                if(cell.isAlive())
                {
                    cell.setAlive(cell.getNeighbours() == 2 || cell.getNeighbours() == 3);
                    //cell.setAlive(cell.getNeighbours() >= 0);
                }
                else
                {
                    cell.setAlive(cell.getNeighbours() == 3);
                    //cell.setAlive(cell.getNeighbours() == 1);
                }
                cells.set(cell.getX() * Settings.cols + cell.getY(), cell);
            }
        }
    }

    public void render(Graphics2D g2d)
    {
        for(Cell cell : cells)
        {
            cell.render(g2d);
        }
    }
}