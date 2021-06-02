/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2proje3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Yazlab2proje3 {

    public static int RMatris[][] = new int[625][625];
    public static double QMatris[][] = new double[625][625];
    public static int NormalMatris[][] = new int[25][25];
    public static int MapMatris[][] = new int[25][25];
    public static int Skor = 0;
    public static int Maliyet = 0;
    public static int AdimSayisi = 0;

    public static void main(String[] args) {
        // TODO code application logic here
        StandartMatrisDoldurma(RMatris, MapMatris, QMatris);

        RMatrisDoldur(RMatris);

        MapYarat(MapMatris);

        MapFrame mapFrame = new MapFrame();
        mapFrame.setVisible(true);

    }

    public static void StandartMatrisDoldurma(int Rmatris[][], int mapMatris[][], double qMatris[][]) {

        for (int i = 0; i < 625; i++) {
            for (int j = 0; j < 625; j++) {
                Rmatris[i][j] = -1;
                qMatris[i][j] = 0;
            }
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                mapMatris[i][j] = 0;
            }
        }

    }

    public static void MatrisYazdir(int Matris[][]) {

        for (int i = 0; i < Matris.length; i++) {
            for (int j = 0; j < Matris[i].length; j++) {
                System.out.print(Matris[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void MatrisYazdir(double Matris[][]) {

        for (int i = 0; i < Matris.length; i++) {
            for (int j = 0; j < Matris[i].length; j++) {
                System.out.print(Matris[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void RMatrisDoldur(int Rmatris[][]) {

        for (int i = 0; i < 25; i++) {

            for (int j = 0; j < 25; j++) {
                //üst
                if (i - 1 >= 0) {
                    Rmatris[(25 * i) + j][(25 * (i - 1)) + j] = 0;
                }
                //sag üst
                if (i - 1 >= 0 && j + 1 < 25) {
                    Rmatris[(25 * i) + j][(25 * (i - 1)) + j + 1] = 0;

                }
                //sol üst
                if (i - 1 >= 0 && j - 1 >= 0) {
                    Rmatris[(25 * i) + j][(25 * (i - 1)) + j - 1] = 0;
                }
                //sol
                if (j - 1 >= 0) {
                    Rmatris[(25 * i) + j][(25 * i) + j - 1] = 0;
                }
                //sag
                if (j + 1 < 25) {
                    Rmatris[(25 * i) + j][(25 * i) + j + 1] = 0;
                }
                //asagi
                if (i + 1 < 25) {
                    Rmatris[(25 * i) + j][(25 * (i + 1)) + j] = 0;
                }
                //sag asagi
                if (i + 1 < 25 && j + 1 < 25) {
                    Rmatris[(25 * i) + j][(25 * (i + 1)) + j + 1] = 0;
                }
                //sag asagi
                if (i + 1 < 25 && j - 1 >= 0) {
                    Rmatris[(25 * i) + j][(25 * (i + 1)) + j - 1] = 0;
                }

            }
        }

    }

    public static void MapYarat(int mapMatris[][]) {
        try {
            FileWriter fileWriter = new FileWriter("engel.txt");
            Random r = new Random();
            int ObstaclePerMap = 30;
            int ObstacleRate = (625 * ObstaclePerMap) / 100;
            while (ObstacleRate != 0) {
                int randomI = r.nextInt(25 - 0);
                int randomJ = r.nextInt(25 - 0);
                if (mapMatris[randomI][randomJ] != -1) {
                    fileWriter.append("( " + randomI + " , " + randomJ + ", K )\n");
                    mapMatris[randomI][randomJ] = -1;
                    ObstacleRate--;
                }
            }
            fileWriter.close();
        } catch (IOException e) {

        }

    }

    public static void GrafikArayuzuYarat() {
        JFrame Frame = new JFrame("Maliyet ve Adım Grafiği");
        Frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        Frame.setSize(1000, 800);
        Frame.setLocation(200, 200);
        Frame.add(new G());
        Frame.setVisible(true);
    }

}
