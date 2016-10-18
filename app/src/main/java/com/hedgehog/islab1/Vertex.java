package com.hedgehog.islab1;

/**
 * Created by hedgehog on 15.10.16.
 */

public class Vertex {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int number;
    private int[] adjacentVertices;



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getAdjacentVertices() {
        return adjacentVertices;
    }

    public String getStringAdjacentVertices() {
        String s = "";
        for (int i = 0; i < getAdjacentVertices().length; i++) {
            s = s + " " + (getAdjacentVertices()[i] + 1);
        }
        return s;
    }

    public void setAdjacentVertices(int[] adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public Vertex(int number, int[] adjacentVertices) {
        setAdjacentVertices(adjacentVertices);
        setNumber(number);
        setName("" + (number + 1));
    }

    public Vertex(int number) {
        setNumber(number);
        setName("" + (number + 1));
    }

    @Override
    public String toString() {
        return name;
    }

}
