package com.company;

import java.awt.*;

public class Settings
{
    public static int width = 1200;
    public static int height = 800;
    public static int cell_size = 5;
    public static int rows = width / cell_size;
    public static int cols = height / cell_size;
    public static int ticks = 10;

    public static final String title = "Maze Generator";
    public static final Color bg_dead = new Color(255, 255, 255);
    public static final Color bg_alive = new Color(0, 0, 0);
}