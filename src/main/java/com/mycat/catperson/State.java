package com.mycat.catperson;

public class State {
    private int father;
    private int self;
    private int num;
    private int velocity;
    private int movable;
    private int distance;



    public State(int father, int self, int num, int velocity, int movable, int distance){
        this.father = father;
        this.self = self;
        this.num = num;
        this.velocity = velocity;
        this.movable = movable;
        this.distance = distance;
    }

    public int getMovable() {
        return movable;
    }

    public void setMovable(int movable) {
        this.movable = movable;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getSelf() {
        return self;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSelf(int self) {
        this.self = self;
    }

    public int getFather() {
        return father;
    }

    public void setFather(int father) {
        this.father = father;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
