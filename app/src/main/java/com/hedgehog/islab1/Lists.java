package com.hedgehog.islab1;

import java.util.ArrayList;
public class Lists {
    private ArrayList <Vertex> vertices;
    private ArrayList<Vertex> open = new ArrayList<Vertex>();
    private ArrayList<Vertex> closed = new ArrayList<Vertex>();
    public Lists(Graph graph){
        vertices = new ArrayList<Vertex>();
        this.vertices.addAll(graph.getVertices());
    }
    public ArrayList<Vertex> getOpen(){
        return open;
    }
    public ArrayList<Vertex> getClosed(){
        return closed;
    }
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }
    public void deleteFirst(ArrayList<Vertex> list){
        list.remove(0);
    }
    public void addFirst(ArrayList<Vertex> list, Vertex vertex){
        list.add(0, vertex );
    }
    public void addLast(ArrayList<Vertex> list, Vertex vertex){
        list.add(list.size(),vertex);
    }
    public boolean isContains(ArrayList<Vertex> list, int j){
        boolean answer = false;
        for (int i = 0; i<list.size(); i++){
            Vertex tempTop = list.get(i);
            if (tempTop.getNumber() == j){
                answer = true;
            }
        }
        return answer;
    }
}