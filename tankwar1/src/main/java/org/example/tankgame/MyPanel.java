package org.example.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

class MyPanel extends JPanel implements KeyListener,Runnable {//我的画笔类
    Hero hero = null;
    Vector<enemytanl> enemys = new Vector<>();
    Vector<node> nodes = null;
    int x = 0;
    int y = 0;
    int enemysize = (int)(Math.random()*5+1);
    public MyPanel(String k){
        nodes=recorder.getNodestank();
        recorder.setEnemytanls(enemys);
        hero = new Hero((int)(Math.random()*900),600);//初始化自己tank
        switch (k){
            case "1":
                for(int i =0;i<enemysize;i++) {
                    enemytanl enemytank = new enemytanl(100 * (i + 1), 0);
                    enemytank.setDirect(2);
                    new Thread(enemytank).start();
                    Shot shot1 = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, +enemytank.getDirect());
                    enemytank.shots.add(shot1);
                    new Thread(shot1).start();
                    enemys.add(enemytank);
                    recorder.setKinum(0);
                }
                break;
            case "2":
                for(int i =0;i<nodes.size();i++) {
                    node syx = nodes.get(i);
                    enemytanl enemytank = new enemytanl(syx.getX(), syx.getY());
                    enemytank.setDirect(syx.getDirect());
                    new Thread(enemytank).start();
                    Shot shot1 = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, +enemytank.getDirect());
                    enemytank.shots.add(shot1);
                    new Thread(shot1).start();
                    enemys.add(enemytank);
                }
                break;
            default:
                System.out.println("您的输入有误！");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);//启用画笔
        g.fillRect(0,0,1000,750);
        for(int i =0;i<enemys.size();i++){
           enemytanl enemy1 = enemys.get(i);
           if(enemy1.islive) {
               drawTank(enemy1.getX(), enemy1.getY(), g, enemy1.getDirect(), 0);
               for (int j = 0; j < enemy1.shots.size(); j++) {
                   Shot shot1 = enemy1.shots.get(j);
                   if (shot1.islive) {
                       g.fill3DRect(shot1.x, shot1.y, 1, 1, true);
                   } else {
                       enemy1.shots.remove(shot1);
                   }
               }
           }
        }//描绘敌方坦克以及子弹
        if(hero.islive&&hero!=null) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        for(int i =0;i<hero.shots.size();i++){
            Shot shot = hero.shots.get(i);
            if(shot!=null&&shot.islive){
                g.fill3DRect(shot.x,shot.y,1,1,true);
            }else{
                hero.shots.remove(shot);
            }
        }//描绘我方坦克以及子弹

        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("击毁的敌方坦克",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(recorder.getKinum()+"",1080,100);//描绘成绩

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirect(0);
            if(hero.getY()>0) {
                hero.moveu();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if(hero.getY()+60<750) {
                hero.moved();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0) {
                hero.movel();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            if(hero.getX()+60<1000) {
                hero.mover();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_J){
            //if(hero.shot ==null||!(hero.shot.islive)) {
            hero.shottank();
            //}
        }
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitenemy();
            hithero();
            this.repaint();
        }
    }
    public void drawTank(int x,int y,Graphics g,int dir,int type){
        switch (type){
            case 0://敌人坦克
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.ORANGE);
                break;
        }
        switch (dir){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;

        }
    }

    public void hit(Shot s ,Tank enemy){
        switch (enemy.getDirect()){
            case 0:
            case 1:
                case 2:
                    if(s.x>enemy.getX()&&s.x<enemy.getX()+40 && s.y>enemy.getY()&&s.y<enemy.getY()+60){
                        s.islive=false;
                        enemy.islive=false;
                        if(enemy instanceof enemytanl){
                            recorder.addkinum();
                        }
                        enemys.remove(enemy);
                    }
                    break;
            case 3:
                if(s.x>enemy.getX()&&s.x<enemy.getX()+60 && s.y>enemy.getY()&&s.y<enemy.getY()+40){
                    s.islive=false;
                    enemy.islive=false;
                    if(enemy instanceof enemytanl){
                        recorder.addkinum();
                    }
                    enemys.remove(enemy);
                }
                break;
        }
    }
    public void hitenemy(){
        for(int j=0;j<hero.shots.size();j++){
            Shot shot = hero.shots.get(j);
            if(shot !=null&&shot.islive){
                for(int i =0;i<enemys.size();i++){
                    enemytanl enemy = enemys.get(i);
                    hit(shot,enemy);
                }
            }
        }
    }
    public void hithero(){
        for(int i =0;i<enemys.size();i++){
            enemytanl enemys1 = enemys.get(i);
            for(int j =0;j<enemys1.shots.size();j++){
                Shot shot = enemys1.shots.get(j);
                if(hero.islive&&shot.islive){
                    hit(shot,hero);
                }
            }
        }
    }
}
