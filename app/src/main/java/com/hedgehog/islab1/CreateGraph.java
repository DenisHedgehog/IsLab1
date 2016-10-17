package com.hedgehog.islab1;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

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
        String g = Arrays.toString(getVertices().get(number).getAdjacentVertices());

    }

    private ArrayList<Vertex> vertices;


    public CreateGraph(int count) {

        vertices = new ArrayList<Vertex>();

        for (int i = 0; i < count; i++) {

            addVertex(i, new Vertex(i));
        }

    }

    /*public String[] numbersToString() {
        String[] strings = new String[];
        for (int i = 0; i < getVertices().size(); i++) {
            strings[i] = getVertices().get(i).toString();
        }
        return strings;
    }*/

    /*@Override
    public String toString() {
        return getClass().getField()
    }*/

}
