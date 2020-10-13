package com.opensource;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainFrame = new Main();
                mainFrame.setTitle("Sorting-Visualizer");
                MyWindow myWindow = new MyWindow();
                mainFrame.setSize(new Dimension(500, 500));
                mainFrame.setResizable(false);
                mainFrame.add(myWindow);
                mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }
}
