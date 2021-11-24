package com.company;

import java.awt.*;

public class Cell
{
    private int x, y;
    private final Handler handler;
    private boolean alive = false;
    private int neighbours = 0;

    public Cell(int x, int y, Handler handler)
    {
        this.x = x;
        this.y = y;
        this.handler = handler;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public int getNeighbours()
    {
        return neighbours;
    }

    public void setNeighbours(int neighbours)
    {
        this.neighbours = neighbours;
    }

    public void update()
    {
        if(Scene.start)
        {
            checkNeighbours();
        }
    }

    public boolean checkCell(int x, int y)
    {
        return x >= 0 && x <= Settings.rows - 1 && y >= 0 && y <= Settings.cols - 1 && handler.cells.get(x * Settings.cols + y).isAlive();
    }

    public void checkNeighbours()
    {
        neighbours = 0;

        boolean top = checkCell(x, y - 1);
        boolean top_right = checkCell(x + 1, y - 1);
        boolean right = checkCell(x + 1, y);
        boolean bottom_right = checkCell(x + 1, y + 1);
        boolean bottom = checkCell(x, y + 1);
        boolean bottom_left = checkCell(x - 1, y + 1);
        boolean left = checkCell(x - 1, y);
        boolean top_left = checkCell(x - 1, y - 1);

        if(top) neighbours++;
        if(top_right) neighbours++;
        if(right) neighbours++;
        if(bottom_right) neighbours++;
        if(bottom) neighbours++;
        if(bottom_left) neighbours++;
        if(left) neighbours++;
        if(top_left) neighbours++;
    }

    public void render(Graphics2D g2d)
    {
        g2d.setColor(alive ? Settings.bg_alive : Settings.bg_dead);
        g2d.fillRect(x * Settings.cell_size, y * Settings.cell_size, Settings.cell_size, Settings.cell_size);

        g2d.setColor(alive ? Settings.bg_dead : Settings.bg_alive);
        g2d.drawRect(x * Settings.cell_size, y * Settings.cell_size, Settings.cell_size, Settings.cell_size);
    }
}