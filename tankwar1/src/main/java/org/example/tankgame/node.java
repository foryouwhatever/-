package org.example.tankgame;

public class node {//存储类
    private int x;
    private int y;
    private int direct;
    public node(int x,int y,int direct){
        this.x=x;
        this.y=y;
        this.direct=direct;

    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }


}
