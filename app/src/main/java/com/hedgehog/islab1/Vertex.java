package com.hedgehog.islab1;

/**
 * Created by hedgehog on 15.10.16.
 */

public class Vertex {

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

    public void setAdjacentVertices(int[] adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public Vertex(int number, int[] adjacentVertices) {
        setAdjacentVertices(adjacentVertices);
        setNumber(number);
    }

    public Vertex(int number) {
        setNumber(number);
    }



}
