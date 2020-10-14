package com.opensource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MyWindow extends JPanel implements ActionListener {
    public Timer timer = new Timer(1000, this);
    public Bar[] bars;
    public Bar[] backupBars;
    final int HEIGHT = 700;
    final int WIDTH = 800;
    public Font myFont, myTitle;
    int size = 6;
    int tempSize = 6;
    int outerLoop = 0;
    int innerLoop = 0;
    int minVal = 0;
    boolean isSorted = false;
    int swaps = 0;
    int doneLoop = 0;
    boolean doneDone = false;
    boolean start = false;
    Sorting whichSorting;

    public MyWindow(Sorting whichSorting) {
        this.backupBars = new Bar[size];

        this.backupBars[0] = (new Bar(20, HEIGHT, WIDTH, 0, size));
        this.backupBars[1] = (new Bar(50, HEIGHT, WIDTH, 1, size));
        this.backupBars[2] = (new Bar(10, HEIGHT, WIDTH, 2, size));
        this.backupBars[3] = (new Bar(60, HEIGHT, WIDTH, 3, size));
        this.backupBars[4] = (new Bar(30, HEIGHT, WIDTH, 4, size));
        this.backupBars[5] = (new Bar(90, HEIGHT, WIDTH, 5, size));
        this.whichSorting = whichSorting;

        this.bars = new Bar[size];
        this.bars = Arrays.stream(backupBars).map(Bar::new).toArray(Bar[]::new);
        this.tempSize = this.size;
        myFont = new Font("Helvetica Neue", Font.BOLD, 24);
        myTitle = new Font("Helvetica Neue", Font.BOLD, 44);
    }

    public void setBars(Bar[] bars, int size) {
        this.backupBars = bars;
        this.bars = new Bar[size];
        this.bars = Arrays.stream(backupBars).map(Bar::new).toArray(Bar[]::new);
        this.size = size;
        this.initToZero();
    }

    public void setWhichSorting(Sorting whichSorting) {
        this.whichSorting = whichSorting;
        this.bars = new Bar[size];
        this.bars = Arrays.stream(backupBars).map(Bar::new).toArray(Bar[]::new);
        this.initToZero();
    }

    public void initToZero() {
        this.doneDone = false;
        this.start = false;
        this.swaps = 0;
        this.outerLoop = 0;
        this.innerLoop = 0;
        this.minVal = 0;
        this.doneLoop = 0;
        this.isSorted = false;
        this.tempSize = this.size;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void faster() {
        this.timer.setDelay(Math.max(this.timer.getDelay() - 100, 1));
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
                if(this.size < 13) {
                    graphics2D.setFont(myFont);
                    graphics2D.drawString(String.valueOf(bar.value), bar.stratingOfString, bar.heightOfString);
                }
            }

            if(doneDone) {
                graphics2D.setColor(Color.RED);
                graphics2D.setFont(myTitle);
                graphics2D.drawString("Done", (int) (WIDTH* 0.43), (int)(HEIGHT * 0.6));
            }
        };
        runnable.run();
        Toolkit.getDefaultToolkit().sync();
    }

    public void bubbleSort() {
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
                swaps = 0;
            }
        }
    }

    public void optimizeBubbleSort() {
        this.bars[Math.max(0, innerLoop-1)].unselect();
        if(outerLoop < size) {
            if(innerLoop < tempSize-1) {
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
                swaps = 0;
                tempSize--;
            }
        }
    }

    public void selectionSort() {
        this.bars[Math.max(1, innerLoop-1)].unselect();
        if(outerLoop < size) {
            if(innerLoop < size) {
                this.bars[outerLoop].select();
                this.bars[innerLoop].select();
                if(bars[outerLoop].value > bars[innerLoop].value) {
                    bars[outerLoop].swap(bars[innerLoop]);
                    swaps++;
                }
                innerLoop++;
            } else {
                this.bars[outerLoop].unselect();
                outerLoop ++;
                innerLoop = outerLoop+1;
                isSorted = (swaps == 0);
                swaps = 0;
            }
        }
    }

    public void optimizeSelectionSort() {
        this.bars[Math.max(1, innerLoop-1)].unselect();
        if(outerLoop < size) {
            if(innerLoop < size) {
                this.bars[minVal].halfSelect();
                this.bars[outerLoop].select();
                this.bars[innerLoop].select();
                if(bars[minVal].value > bars[innerLoop].value) {
                    this.bars[minVal].unselect();
                    minVal = innerLoop;
                    swaps++;
                }
                innerLoop++;
            } else {
                isSorted = (swaps == 0 && outerLoop == (size -1));
                if(!this.isSorted) {
                    bars[outerLoop].swap(bars[minVal]);
                    this.bars[outerLoop].unselect();
                    this.bars[minVal].unselect();
                }

                outerLoop ++;
                minVal = outerLoop;
                innerLoop = outerLoop+1;
                swaps = 0;
            }
        }
    }

    public void doneScene() {
        if(doneLoop < size) {
            this.bars[doneLoop].select();
            doneLoop++;
        } else {
            doneDone = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Runnable runnable = () -> {
            if(start) {
                if(!isSorted) {
                    if(this.whichSorting == Sorting.BubbleSort) {
                        bubbleSort();
                    } else if(this.whichSorting == Sorting.OptimizeBubbleSort) {
                        optimizeBubbleSort();
                    } else if(this.whichSorting == Sorting.SelectionSort) {
                        selectionSort();
                    } else {
                        optimizeSelectionSort();
                    }
                } else {
                    doneScene();
                }
            }
            this.repaint();
        };
        runnable.run();
    }
}
