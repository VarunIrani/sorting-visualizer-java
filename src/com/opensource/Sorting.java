package com.opensource;

public enum Sorting {
    BubbleSort("Bubble Sort"),
    OptimizeBubbleSort("Optimize Bubble Sort"),
    SelectionSort("Selection Sort"),
    OptimizeSelectionSort(" Optimize Selection Sort");

    private String s;
    Sorting(String s) {
        this.s = s;
    }
    public String getValue() {
        return s;
    }

}
