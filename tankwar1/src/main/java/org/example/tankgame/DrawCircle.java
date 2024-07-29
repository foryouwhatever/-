package org.example.tankgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class DrawCircle extends JFrame {//窗口类，内含主类
    private MyPanel mp = null;
    public static void main(String[] args) {
        DrawCircle drawCircle1 = new DrawCircle();
    }
    public DrawCircle() {
        System.out.println("请输入选择 1:重新开始游戏 2:进行上局未完成的游戏");
        Scanner in = new Scanner(System.in);
        String s = in.next();
            mp = new MyPanel(s);
            Thread thread = new Thread(mp);
            thread.start();
            this.add(mp);
            this.setSize(1300, 750);
            this.addKeyListener(mp);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    recorder.keeprecord();
                    System.exit(0);
                }
            });
        }
}

