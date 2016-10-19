package com.hedgehog.islab1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hedgehog on 18.10.16.
 */

public class Searches {

    private Lists lists;
    private Vertex startVertex;
    private Vertex destinationVertex;
    private ArrayList<Parent> parents = new ArrayList<Parent>();
    private int recCount = 0;
    private int breadthCount = 0;
    private int depthCount = 0;


    public int getDepthCount() {
        return depthCount;
    }

    public void setDepthCount(int depthCount) {
        this.depthCount = depthCount;
    }


    public int getBreadthCount() {
        return breadthCount;
    }

    public void setBreadthCount(int breadthCount) {
        this.breadthCount = breadthCount;
    }


    public int getRecCount() {
        return  recCount;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public Searches(Lists lists, Vertex startVertex, Vertex destinationVertex){
        this.lists = lists;
        this.startVertex = startVertex;
        this.destinationVertex = destinationVertex;
    }

    public StringBuilder findPath(){
        StringBuilder path = new StringBuilder().append(destinationVertex.getNumber()).append(" <- ");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        Collections.reverse(parents);
        for (int i = 0; i<parents.size()-1; i++){
            nums.add(parents.get(i).getParent());
        }
        for (int j = 0; j<nums.size(); j++){
            if (nums.get(j) == nums.get(j+1)){
                nums.remove(j+1);
            }
        }
        for (int k = 0; k<nums.size(); k++){
            path.append(nums.get(k)).append(" <- ");
        }
        return path.append("1");
    }

    public boolean breadthSearch(ArrayList<Vertex> vertices, ArrayList<Vertex> open, ArrayList<Vertex> closed){
        open.clear();
        closed.clear();
        parents.clear();
        open.add(vertices.get(getStartVertex().getNumber()));
        int count = 0;
        while (!open.isEmpty()){
            Vertex tempTop = open.get(getStartVertex().getNumber());
            count++;
            // System.out.println("Номер первой вершины " + tempTop.getNumOfTop());
            if (destinationVertex.equals(tempTop)){
                setBreadthCount(count);
                return true;
            }
            else {
                lists.deleteFirst(open);
                lists.addFirst(closed, tempTop);
                // System.out.println("Номер вершины, добавленной в closed " + closed.get(0).getNumOfTop());
                int [] tempRelatedTops = tempTop.getAdjacentVertices();
                //  System.out.println("Количество смежных выбранной вершин " + tempRelatedTops.length);
                for (int j = 0; j<tempRelatedTops.length; j++){
                    // System.out.println("Обрабатываемая смежная вершина " + tempRelatedTops[j]);
                    if (!lists.isContains(open,tempRelatedTops[j]) & (!lists.isContains(closed, tempRelatedTops[j]))){
                        for (int k = 0; k<vertices.size(); k++){
                            Vertex someTop = vertices.get(k);
                            //System.out.println("Номер вершины, которая будет сравниваться с номером смежной: "+ someTop.getNumOfTop());
                            if (someTop.getNumber() == tempRelatedTops[j]){
                                //System.out.println("Номер вершины, совпавшей со смежной "+someTop.getNumOfTop());
                                Parent parent = new Parent(someTop.getNumber(),tempTop.getNumber());
                                parents.add(parent);
                                lists.addLast(open, someTop);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean depthSearch(ArrayList<Vertex> vertices, ArrayList<Vertex> open, ArrayList<Vertex> closed){
        open.clear();
        closed.clear();
        parents.clear();
        open.add(vertices.get(getStartVertex().getNumber()));
        int count = 0;
        while (!open.isEmpty()){
            Vertex vertex = open.get(getStartVertex().getNumber());
            count++;
            //System.out.println("Номер первой вершины " + vertex.getNumOfTop());
            if (destinationVertex.equals(vertex)){
                setDepthCount(count);
                return true;
            }
            else {
                lists.deleteFirst(open);
                lists.addFirst(closed, vertex);
                //System.out.println("Номер вершины, добавленной в closed " + closed.get(0).getNumOfTop());
                int [] adjacentVertices = vertex.getAdjacentVertices();
                // System.out.println("Количество смежных выбранной вершин " + adjacentVertices.length);
                for (int j = 0; j < adjacentVertices.length; j++){
                    // System.out.println("Обрабатываемая смежная вершина " + adjacentVertices[j]);
                    if (!lists.isContains(open,adjacentVertices[j]) & (!lists.isContains(closed, adjacentVertices[j]))){
                        for (int k = 0; k < vertices.size(); k++){
                            Vertex someVertex = vertices.get(k);
                            // System.out.println("Номер вершины, которая будет сравниваться с номером смежной: "+ someVertex.getNumOfTop());
                            if (someVertex.getNumber() == adjacentVertices[j]){
                                // System.out.println("Номер вершины, совпавшей со смежной "+someVertex.getNumOfTop());
                                Parent parent = new Parent(someVertex.getNumber(),vertex.getNumber());
                                parents.add(parent);
                                lists.addFirst(open,someVertex);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /*int [] adjacentVertices = vertex.getAdjacentVertices();
    // System.out.println("Количество смежных выбранной вершин " + adjacentVertices.length);
    for (int j = 0; j<adjacentVertices.length; j++){
        // System.out.println("Обрабатываемая смежная вершина " + adjacentVertices[j]);
        if (!lists.isContains(open,adjacentVertices[j]) & (!lists.isContains(closed, adjacentVertices[j]))){
            for (int k = 0; k<vertices.size(); k++){
                Vertex someVertex = vertices.get(k);
                // System.out.println("Номер вершины, которая будет сравниваться с номером смежной: "+ someVertex.getNumOfTop());
                if (someVertex.getNumber() == adjacentVertices[j]){
                    // System.out.println("Номер вершины, совпавшей со смежной "+someVertex.getNumOfTop());
                    Parent parent = new Parent(someVertex.getNumber(),vertex.getNumber());
                    parents.add(parent);
                    lists.addFirst(open,someVertex);
                    break;
                }
            }
        }
    }*/

    public boolean depthRecSearch(Vertex tempTop, ArrayList<Vertex> ourTops, ArrayList<Vertex> closed){
        //System.out.println("closed size = " + closed.size());
        recCount++;
        if (destinationVertex.equals(tempTop)){
            //output.showCount(recCount);
            return true;
        }
        else {
            lists.addFirst(closed,tempTop);
            int [] tempRelatedTops = tempTop.getAdjacentVertices();
            // System.out.println("Количество смежных выбранной вершин " + tempRelatedTops.length);
            for (int j = 0; j<tempRelatedTops.length; j++){
                //  System.out.println("Обрабатываемая смежная вершина " + tempRelatedTops[j]);
                //  System.out.println("Есть ли смежная вершина в списке closed? " + lists.isContains(closed,tempRelatedTops[j]));
                if (!lists.isContains(closed,tempRelatedTops[j])){
                    for (int k = 1; k<ourTops.size(); k++){
                        Vertex someTop = ourTops.get(k);
                        //      System.out.println("Номер вершины, которая будет сравниваться с номером смежной: "+ someTop.getNumOfTop());
                        if (someTop.getNumber() == tempRelatedTops[j]){
                            if (depthRecSearch(someTop,ourTops,closed))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
