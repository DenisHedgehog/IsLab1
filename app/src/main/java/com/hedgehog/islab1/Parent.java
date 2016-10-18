package com.hedgehog.islab1;

/**
 * Created by hedgehog on 18.10.16.
 */

public class Parent {
    private int i;
    private int parent;
    public Parent(int i, int parent){
        this.i = i;
        this.parent = parent;
    }
    public int getI(){
        return i;
    }
    public int getParent(){
        return parent;
    }
}