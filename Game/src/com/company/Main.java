package com.company;

import java.util.ArrayList;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {

        Game a = new Game(); // yeni bir Game objesi oluşturulur
        a.printBoard(); //ekrana oyun boardı print edilir


        Stack<Cell> path = new Stack<>();  // en kısa pathin olduğu celler bir stack te tutulmalı
        ArrayList<String > karsilasilanSiyahPullar = new ArrayList<>();

        System.out.println("\n\nEn kısa yol uzunluğu = " + a.shortestPath( a.getGameBoard(), new Cell(7, 0), new Cell(0, 7), path)); //shortestPath methodu en kısa yolun uzunluğunu döndürür ve aldığı Stack pathi
                                                                                                                                                        // call by reference ile değiştirir
        System.out.print("En kısa yol = ");
        while (!path.isEmpty()) {
            Cell b = path.pop();  // teker teker stackten celler pop edilir
            if(path.isEmpty()){
                System.out.print(b.getName() + "");  // ekrana cellerin lokasyon bilgileri yazılır
            }
            else{
                System.out.print(b.getName() + ", ");
            }
            a.karsilasilanSiyahPullar(b,karsilasilanSiyahPullar);   // path üzerindeki her bir cell in 4 kenarına bakılır siyah pul varsa ve daha önce arraylistte yoksa eklenir
        }
        System.out.println();
        System.out.print("Karşılaşılan siyah pullar = " );
        for(int i = 0;i<karsilasilanSiyahPullar.size();i++){  // karsılasılanSiyah pullar print edilir
            if(i==karsilasilanSiyahPullar.size()-1){
                System.out.print(karsilasilanSiyahPullar.get(i)+"");
            }
            else{
                System.out.print(karsilasilanSiyahPullar.get(i)+",");
            }
        }
        System.out.println();

    }
}

