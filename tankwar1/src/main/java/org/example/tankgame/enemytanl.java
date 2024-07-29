package org.example.tankgame;

import java.util.Vector;

public class enemytanl extends Tank implements Runnable{//enemytanl类
    boolean islive = true;
    Vector<Shot> shots = new Vector<>();
    Shot s = null;
    public enemytanl(int x,int y){
        super(x,y);
    }

    @Override
    public void run() {
        while(true){
            if(islive&&shots.size()<(int)(Math.random()*3+1)){
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

            switch (getDirect()){
                case 0 :
                    for(int i =0;i<30;i++){
                        if(getY()>0) {
                            moveu();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for(int i =0;i<30;i++) {
                        if(getX()+60<1000) {
                            mover();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 2:
                    for(int i =0;i<30;i++) {
                        if(getY()+60<750) {
                            moved();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for(int i =0;i<30;i++) {
                        if(getX()>0) {
                            movel();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

            }
            setDirect((int)(Math.random()*4));
            if(!islive){
                break;
            }
        }
    }
}
