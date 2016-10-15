package com.hedgehog.islab1;

import java.util.ArrayList;

/**
 * Created by hedgehog on 15.10.16.
 */

public class CreateGraph {

    //Создаем вершину и добавляем её в массив вершин

    private Graph graph;


    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void addVertex(int number, Vertex vertex) {
        getVertices().add(number, vertex);
    }

    private ArrayList<Vertex> vertices;


    public CreateGraph(int count) {

        vertices = new ArrayList<Vertex>();

        for (int i = 0; i < count; i++) {

            addVertex(i, new Vertex(i));
        }

    }


}
