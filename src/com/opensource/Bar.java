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
        this.width = ((int)Math.ceil(((double)width/(double) size) * 0.75));
        this.startingPoint = this.width * index + (4 * index + 5);
        this.stratingOfString = this.startingPoint + (int)(Math.floor(this.width * 0.25));
        this.heightOfString = this.height+20;
        int rr = random.nextInt(255);
        int br = random.nextInt(255);
        int gr = random.nextInt(255);
        int r = Math.min(100 + rr, 220);
        int g = Math.min(100 + br, 220);
        int b = Math.min(100 + gr, 220);
        myColor = new Color(r, g, b);
    }

    public void select() {
        this.tempColor = this.myColor;
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
