package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Scene extends Canvas implements Runnable
{
    private final Window window;
    private Thread thread;
    private boolean running = false;

    private final Handler handler;
    private final MouseInput mouse_input;
    private final KeyInput key_input;
    public static boolean start = false;

    public Scene()
    {
        handler = new Handler();

        for(int x = 0; x < Settings.rows; x++)
        {
            for(int y = 0; y < Settings.cols; y++)
            {
                handler.add(new Cell(x, y, handler));
            }
        }

        mouse_input = new MouseInput(handler);
        key_input = new KeyInput();
        this.addMouseListener(mouse_input);
        this.addKeyListener(key_input);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(Settings.width, Settings.height));
        
        // Code below makes a shape (+) automatically. Here can be written a code for any shape you want.

        for(int x = 0; x < Settings.rows; x++)
        {
            int index = x * Settings.cols + Settings.cols / 2;
            Cell cell = handler.cells.get(index);
            cell.setAlive(true);
        }

        for(int y = 0; y < Settings.cols; y++)
        {
            int index = (Settings.rows / 2) * Settings.cols + y;
            Cell cell = handler.cells.get(index);
            cell.setAlive(true);
        }

        window = new Window(Settings.width, Settings.height, Settings.title, this);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = Settings.ticks;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                update();
                delta--;
            }
            if(running)
            {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                window.setTitle(frames + " fps | " + Settings.title);
                frames = 0;
            }
        }
        stop();
    }

    private void update()
    {
        handler.update();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Settings.bg_dead);
        g2d.fillRect(0, 0, Settings.width, Settings.height);

        handler.render(g2d);

        g2d.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        new Scene();
    }
}
