package com.opensource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyWindow extends JPanel implements ActionListener {
    public Timer timer = new Timer(500, this);
    public int sec = 0;
    public ArrayList<Bar> bars;
    final int HEIGHT = 500;
    final int WIDTH = 500;
    public Font myFont;
    int selected = 0;
    int unselected = 0;
    int size = 5;

    public MyWindow() {
        bars = new ArrayList<Bar>();
        bars.add(new Bar(20, HEIGHT, WIDTH, 0, size));
        bars.add(new Bar(50, HEIGHT, WIDTH, 1, size));
        bars.add(new Bar(10, HEIGHT, WIDTH, 2, size));
        bars.add(new Bar(60, HEIGHT, WIDTH, 3, size));
        bars.add(new Bar(30, HEIGHT, WIDTH, 4, size));
        bars.add(new Bar(90, HEIGHT, WIDTH, 5, size));

        for(Bar b : bars) {
            System.out.println(b);
        }
        myFont = new Font("Helvetica Neue", Font.BOLD, 24);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(new Color(255, 255, 255));
        for(Bar bar : bars) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setColor(bar.myColor);
            graphics2D.fillRect(bar.startingPoint, 0, bar.width, bar.height);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(myFont);
            graphics2D.drawString(String.valueOf(bar.value), bar.stratingOfString, bar.heightOfString);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        sec++;

        if(unselected > size) {
            unselected = 0;
        }

        if(selected > size) {
            selected = 0;
        }

        if(unselected != selected) {
            bars.get(unselected).unselect();
            unselected++;
        }

        bars.get(selected).select();
        selected++;
        this.repaint();
    }
}
