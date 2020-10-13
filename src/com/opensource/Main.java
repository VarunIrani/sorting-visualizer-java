package com.opensource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame {
    final static int HEIGHT = 700;
    final static int WIDTH = 800;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainFrame = new Main();
                JPanel labelPanel = new JPanel(new FlowLayout());
                JLabel label = new JLabel("Bubble Sort");
                label.setFont(new Font("Curiour", Font.BOLD, 28));

                JPanel buttonPanel = new JPanel(new GridLayout(1,5));
                JButton randomValueBtn = new JButton("Genrate Values");
                JButton startBtn = new JButton("Start Sorting");
                JButton fastBtn = new JButton("Speedup");
                JButton slowBtn = new JButton("Speeddown");
                SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
                spinnerNumberModel.setValue(5);
                spinnerNumberModel.setMinimum(5);
                spinnerNumberModel.setMaximum(12);
                spinnerNumberModel.setStepSize(1);

                JSpinner spinner = new JSpinner(spinnerNumberModel);

                buttonPanel.add(randomValueBtn, 0);
                buttonPanel.add(spinner, 1);
                buttonPanel.add(startBtn, 2);
                buttonPanel.add(fastBtn, 3);
                buttonPanel.add(slowBtn, 4);

                labelPanel.add(label);
                mainFrame.setTitle("Sorting-Visualizer");
                MyWindow myWindow = new MyWindow();
                mainFrame.setSize(new Dimension(WIDTH, HEIGHT));
                mainFrame.setResizable(false);
                mainFrame.setLocationRelativeTo(null);

                randomValueBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int size = (int) spinner.getValue();
                        int[] vals = new int[size];
                        Random random = new Random();
                        for(int i=0; i<size; i++) {
                            vals[i] = 20 + random.nextInt(120);
                        }
                        Bar[] bars = new Bar[size];
                        for(int i=0; i<size; i++) {
                            bars[i] = new Bar(vals[i], HEIGHT, WIDTH, i, size);
                        }

                        myWindow.setBars(bars, size);
                        mainFrame.invalidate();
                        mainFrame.validate();

                    }
                });

                startBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        myWindow.setStart(true);
                    }
                });

                fastBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        myWindow.faster();
                    }
                });

                slowBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        myWindow.slower();
                    }
                });

                mainFrame.setLayout(new BorderLayout());
                mainFrame.add(labelPanel, BorderLayout.NORTH);
                mainFrame.add(myWindow, BorderLayout.CENTER);
                mainFrame.add(buttonPanel, BorderLayout.SOUTH);
                mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }
}
