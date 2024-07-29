package org.example.tankgame;

public class Shot implements Runnable {//shot类
    int x;
    int y;
    int direct = 0;
    int speed = 10;
    boolean islive = true;

    public Shot(int x,int y,int direct){
        this.x=x;
        this.y=y;
        this.direct=direct;
    }
    public void run(){
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct){
                case 0:
                    y -=speed;
                    break;
                case 1:
                    x +=speed;
                    break;
                case 2:
                    y +=speed;
                    break;
                case 3:
                    x -=speed;
                    break;
            }
            if(!(x>=0&&x<=1000&&y>=0&&y<=750)){
                islive = false;
                break;
            }
        }
    }
}
