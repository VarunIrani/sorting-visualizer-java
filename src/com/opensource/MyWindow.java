package com.opensource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JPanel implements ActionListener {
    public Timer timer = new Timer(1000, this);
    public int sec = 0;
    public Bar[] bars;
    final int HEIGHT = 700;
    final int WIDTH = 800;
    public Font myFont, myTitle;
    int size = 6;
    int outerLoop = -1;
    int innerLoop = outerLoop + 1;
    boolean isSorted = false;
    int swaps = 0;
    int doneLoop = 0;
    boolean doneDone = false;
    boolean start = false;

    public MyWindow() {
        bars = new Bar[6];
        bars[0] = (new Bar(20, HEIGHT, WIDTH, 0, size));
        bars[1] = (new Bar(50, HEIGHT, WIDTH, 1, size));
        bars[2] = (new Bar(10, HEIGHT, WIDTH, 2, size));
        bars[3] = (new Bar(60, HEIGHT, WIDTH, 3, size));
        bars[4] = (new Bar(30, HEIGHT, WIDTH, 4, size));
        bars[5] = (new Bar(90, HEIGHT, WIDTH, 5, size));

//        for(Bar b : bars) {
//            System.out.println(b);
//        }

        myFont = new Font("Helvetica Neue", Font.BOLD, 24);
        myTitle = new Font("Helvetica Neue", Font.BOLD, 44);
    }

    public void setBars(Bar[] bars, int size) {
        this.bars = bars;
        this.size = size;

        this.doneDone = false;
        this.start = false;
        this.swaps = 0;
        this.outerLoop = -1;
        this.innerLoop = outerLoop + 1;
        this.doneLoop = 0;
        this.isSorted = false;

//        for(Bar b : bars) {
//            System.out.println(b);
//        }

    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void faster() {
        this.timer.setDelay(Math.max(this.timer.getDelay() - 100, 100));
    }

    public void slower() {
        this.timer.setDelay(Math.min(this.timer.getDelay() + 100, 1300));
    }

    public void paintComponent(Graphics graphics) {
        Runnable runnable = () -> {
            timer.start();
            super.paintComponent(graphics);
            this.setBackground(new Color(255, 255, 255));
            Graphics2D graphics2D = (Graphics2D) graphics;

            for(Bar bar : bars) {
                graphics2D.setColor(bar.myColor);
                graphics2D.fillRect(bar.startingPoint, 0, bar.width, bar.height);
                graphics2D.setColor(Color.BLACK);
                graphics2D.setFont(myFont);
                graphics2D.drawString(String.valueOf(bar.value), bar.stratingOfString, bar.heightOfString);
            }

            if(doneDone) {
                graphics2D.setColor(Color.RED);
                graphics2D.setFont(myTitle);
                graphics2D.drawString("Done", (int) (WIDTH* 0.4), (int)(HEIGHT * 0.6));
            }
        };
        runnable.run();
        Toolkit.getDefaultToolkit().sync();
    }

    public void bubbleSort() {
        Runnable runnable = () -> {
            this.bars[Math.max(0, innerLoop-1)].unselect();
            if(outerLoop < size) {
                if(innerLoop < size-1) {
                    this.bars[innerLoop].select();
                    this.bars[innerLoop+1].select();
                    if(bars[innerLoop].value > bars[innerLoop+1].value) {
                        bars[innerLoop].swap(bars[innerLoop+1]);
                        swaps++;
                    }
                    innerLoop++;
                } else {
                    this.bars[innerLoop].unselect();
                    outerLoop ++;
                    innerLoop = 0;

                    isSorted = (swaps == 0);
                    System.out.println("Is Sorted - " + isSorted);
                    swaps = 0;
                }
            }
        };
        runnable.run();
    }

    public void doneScene() {
        Runnable runnable = () -> {
            if(doneLoop < size) {
                this.bars[doneLoop].select();
                doneLoop++;
            } else {
                doneDone = true;
            }
        };
        runnable.run();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Runnable runnable = () -> {
            sec++;
            if(start) {
                if(!isSorted) {
                    bubbleSort();
                } else {
                    doneScene();
                }
            }
            this.repaint();
        };
        runnable.run();
    }
}
