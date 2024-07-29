package org.example.tankgame;

public class Tank {//tank 类
    public boolean islive = true;
    private int x;
    private int y;
    private int direct;
    private int sudu = (int)(Math.random()*5+2);
    public void moveu(){
        y=y-sudu;
    }
    public void moved(){
        y=y+sudu;
    }
    public void movel(){
        x=x-sudu;
    }
    public void mover(){
        x=x+sudu;
    }
    public Tank(int x,int y){
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getDirect() {
        return direct;
    }
}
