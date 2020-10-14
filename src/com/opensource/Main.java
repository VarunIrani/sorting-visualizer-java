package com.opensource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame {
    final static int HEIGHT = 700;
    final static int WIDTH = 800;

    public Main(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainFrame = new Main(new BorderLayout());

                JPanel mainLabelPanel = new JPanel(new GridLayout(2, 1));
                JPanel labelPanel = new JPanel((new FlowLayout()));
                JLabel label = new JLabel("Bubble Sort");
                label.setFont(new Font("Curiour", Font.BOLD, 28));

                JPanel radioButtonPanel = new JPanel(new GridLayout(1, 4));
                JRadioButton bubbleSortCheck = new JRadioButton(Sorting.BubbleSort.getValue());
                JRadioButton optimizeBubbleSortCheck = new JRadioButton(Sorting.OptimizeBubbleSort.getValue());
                JRadioButton selectionSortCheck = new JRadioButton(Sorting.SelectionSort.getValue());
                JRadioButton optimizeSelectionSortCheck = new JRadioButton(Sorting.OptimizeSelectionSort.getValue());

                ButtonGroup g = new ButtonGroup();
                g.add(bubbleSortCheck);
                g.add(optimizeBubbleSortCheck);
                g.add(selectionSortCheck);
                g.add(optimizeSelectionSortCheck);
                bubbleSortCheck.setSelected(true);

                JPanel buttonPanel = new JPanel(new GridLayout(1,5));
                JButton randomValueBtn = new JButton("Genrate Values");
                JButton startBtn = new JButton("Start Sorting");
                JButton fastBtn = new JButton("Speedup");
                JButton slowBtn = new JButton("Speeddown");

                SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
                spinnerNumberModel.setValue(5);
                spinnerNumberModel.setMinimum(5);
                spinnerNumberModel.setMaximum(99);
                spinnerNumberModel.setStepSize(1);

                JSpinner spinner = new JSpinner(spinnerNumberModel);

                mainFrame.setTitle("Sorting-Visualizer");
                MyWindow myWindow = new MyWindow(Sorting.BubbleSort);
                mainFrame.setSize(new Dimension(WIDTH, HEIGHT));
                mainFrame.setResizable(false);
                mainFrame.setLocationRelativeTo(null);

                bubbleSortCheck.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        label.setText(Sorting.BubbleSort.getValue());
                        myWindow.setWhichSorting(Sorting.BubbleSort);
                    }
                });

                optimizeBubbleSortCheck.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        label.setText(Sorting.OptimizeBubbleSort.getValue());
                        myWindow.setWhichSorting(Sorting.OptimizeBubbleSort);
                    }
                });

                selectionSortCheck.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        label.setText(Sorting.SelectionSort.getValue());
                        myWindow.setWhichSorting(Sorting.SelectionSort);
                    }
                });

                optimizeSelectionSortCheck.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        label.setText(Sorting.OptimizeSelectionSort.getValue());
                        myWindow.setWhichSorting(Sorting.OptimizeSelectionSort);
                    }
                });

                randomValueBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Runnable runnable = () -> {
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
                        };
                        runnable.run();

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

                labelPanel.add(label);
                radioButtonPanel.add(bubbleSortCheck, 0);
                radioButtonPanel.add(optimizeBubbleSortCheck, 1);
                radioButtonPanel.add(selectionSortCheck, 2);
                radioButtonPanel.add(optimizeSelectionSortCheck, 3);

                mainLabelPanel.add(labelPanel, 0);
                mainLabelPanel.add(radioButtonPanel, 1);


                buttonPanel.add(randomValueBtn, 0);
                buttonPanel.add(spinner, 1);
                buttonPanel.add(startBtn, 2);
                buttonPanel.add(fastBtn, 3);
                buttonPanel.add(slowBtn, 4);

                mainFrame.add(mainLabelPanel, BorderLayout.NORTH);
                mainFrame.add(myWindow, BorderLayout.CENTER);
                mainFrame.add(buttonPanel, BorderLayout.SOUTH);

                mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }
}
