package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends Canvas implements ComponentListener
{
    private final JFrame frame;

    public Window(int width, int height, String title, Scene scene)
    {
        this.frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(scene);
        frame.pack();
        frame.addComponentListener(this);
        frame.setVisible(true);

        scene.start();
    }

    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void componentResized(ComponentEvent e)
    {
        Settings.width = frame.getWidth();
        Settings.height = frame.getHeight();
        Settings.cell_size = Settings.width / Settings.rows;
    }
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
    public void componentHidden(ComponentEvent e) {}
}