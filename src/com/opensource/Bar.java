package com.opensource;

import java.awt.*;
import java.util.Random;

public class Bar {
    public int value;
    public int height;
    public int width;
    public Color myColor;
    public int startingPoint;
    public int stratingOfString;
    public int heightOfString;
    public int size;
    public Color tempColor;

    public Bar(int value, int height, int width, int index, int size) {
        Random random = new Random();
        this.value = value;
        this.height = (int)Math.ceil(((double)value/(double) height) * 2000);
        this.width = ((int)Math.ceil(((double)width/(double) size) * 0.93));
        this.startingPoint = this.width * index + (3 * index + 4);
        this.stratingOfString = this.startingPoint + (int)(Math.floor(this.width * 0.25));
        this.heightOfString = this.height+20;
        int rr = random.nextInt(255);
        int br = random.nextInt(255);
        int gr = random.nextInt(255);
        int r = Math.min(100 + rr, 220);
        int g = Math.min(100 + br, 220);
        int b = Math.min(100 + gr, 220);
        this.myColor = new Color(r, g, b);
        this.tempColor = this.myColor;
    }

    public void swap(Bar b) {
        int value = this.value;
        int height = this.height;
        int heightOfString = this.heightOfString;

        this.value = b.value;
        this.height = b.height;
        this.heightOfString = b.heightOfString;

        b.value = value;
        b.height = height;
        b.heightOfString = heightOfString;
    }

    public void select() {
        this.myColor = new Color(0, 255, 0);
    }

    public void unselect() {
        this.myColor = this.tempColor;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "value=" + value +
                ", height=" + height +
                ", width=" + width +
                ", myColor=" + myColor +
                ", startingPoint=" + startingPoint +
                '}';
    }
}
