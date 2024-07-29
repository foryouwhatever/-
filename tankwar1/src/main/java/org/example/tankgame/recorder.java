package org.example.tankgame;

import java.io.*;
import java.util.Vector;

public class recorder {//节点流+包装流类，将数据存储在特定地址上
    private static int kinum = 0;
    private static FileWriter fw= null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;
    private static String recordfile = "D:\\record.txt";
    private static Vector<enemytanl> enemytanls = null;
    private static Vector<node> nodes = new Vector<>();
    public static int getKinum() {
        return kinum;
    }
    public static void setKinum(int kinum) {
        recorder.kinum = kinum;
    }
    public static void addkinum(){
        recorder.kinum++;
    }
    public static void setEnemytanls(Vector<enemytanl> enemytanls) {
        recorder.enemytanls = enemytanls;
    }



    public static void keeprecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordfile));
            bw.write(recorder.kinum + "\r\n");
            for(int i =0;i<enemytanls.size();i++){
                enemytanl enemytanl = enemytanls.get(i);
                if(enemytanl.islive){
                    String record = enemytanl.getX()+" "+enemytanl.getY()+" "+enemytanl.getDirect();
                    bw.write(record+"\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Vector<node> getNodestank(){
        try {
            br = new BufferedReader(new FileReader(recordfile));
            kinum = Integer.parseInt(br.readLine());
            String line = "";
            while((line = br.readLine()) !=null){
                String[] xyd = line.split(" ");
                node w = new node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(w);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

}
