package com.hedgehog.islab1;

import java.util.ArrayList;

/**
 * Created by hedgehog on 18.10.16.
 */

public class Searches {

    private Lists lists;
    private Vertex startVertex;
    private Vertex destinationVertex;
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

    public Searches(Lists lists, Vertex startVertex, Vertex destinationVertex){
        this.lists = lists;
        this.startVertex = startVertex;
        this.destinationVertex = destinationVertex;
    }

    public boolean breadthSearch(ArrayList<Vertex> vertices, ArrayList<Vertex> open, ArrayList<Vertex> closed){
        open.clear();
        closed.clear();
        open.add(vertices.get(getStartVertex().getNumber()));
        int count = 0;
        while (!open.isEmpty()){
            Vertex tempTop = open.get(getStartVertex().getNumber());
            count++;
            if (destinationVertex.equals(tempTop)){
                setBreadthCount(count);
                return true;
            }
            else {
                lists.deleteFirst(open);
                lists.addFirst(closed, tempTop);
                int [] tempRelatedTops = tempTop.getAdjacentVertices();
                for (int j = 0; j<tempRelatedTops.length; j++){
                    if (!lists.isContains(open,tempRelatedTops[j]) & (!lists.isContains(closed, tempRelatedTops[j]))){
                        for (int k = 0; k<vertices.size(); k++){
                            Vertex someTop = vertices.get(k);
                            if (someTop.getNumber() == tempRelatedTops[j]){
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
        open.add(vertices.get(getStartVertex().getNumber()));
        int count = 0;
        while (!open.isEmpty()){
            Vertex vertex = open.get(getStartVertex().getNumber());
            count++;
            if (destinationVertex.equals(vertex)){
                setDepthCount(count);
                return true;
            }
            else {
                lists.deleteFirst(open);
                lists.addFirst(closed, vertex);
                int [] adjacentVertices = vertex.getAdjacentVertices();
                for (int j = adjacentVertices.length - 1; j >= 0 ; j--){
                    if (!lists.isContains(open,adjacentVertices[j]) & (!lists.isContains(closed, adjacentVertices[j]))){
                        for (int k = vertices.size() - 1; k >= 0 ; k--){
                            Vertex someVertex = vertices.get(k);
                            if (someVertex.getNumber() == adjacentVertices[j]){
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

    public boolean depthRecSearch(Vertex vertex, ArrayList<Vertex> vertices, ArrayList<Vertex> closed){
        recCount++;
        if (destinationVertex.equals(vertex)){
            return true;
        }
        else {
            lists.addFirst(closed,vertex);
            int [] adjacentVertices = vertex.getAdjacentVertices();
            for (int j = 0; j<adjacentVertices.length; j++){
                if (!lists.isContains(closed,adjacentVertices[j])){
                    for (int k = 1; k<vertices.size(); k++){
                        Vertex someVertex = vertices.get(k);
                        if (someVertex.getNumber() == adjacentVertices[j]){
                            if (depthRecSearch(someVertex,vertices,closed))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
