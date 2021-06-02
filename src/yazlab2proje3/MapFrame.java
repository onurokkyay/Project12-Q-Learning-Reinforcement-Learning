/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2proje3;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MapFrame extends javax.swing.JFrame {

    public static JTextField[][] MapFieldMatris = new JTextField[25][25];
    public static JTextField BaslangicPosI = new JTextField();
    public static JTextField BaslangicPosJ = new JTextField();
    public static JTextField BitisPosI = new JTextField();
    public static JTextField BitisPosJ = new JTextField();
    public static ArrayList<Integer> Komsular = new ArrayList();
    public static ArrayList<Integer> KomsularSonraki = new ArrayList();
    public static Random rnd = new Random();
    public static double MaxValue = -1;
    public static int i = 0, j = 0;
    public static int sonrakiI, sonrakiJ;
    public static ArrayList<Integer> EnKisaYol = new ArrayList();
    public static int IterasyonSayisi = 1000000;
    public static float EpsilonDeger = 0.9f;
    public static int[][] MaliyetAdimMatrisi = new int[IterasyonSayisi][3];

    /**
     * Creates new form MapFrame
     */
    public MapFrame() {
        initComponents();
        ArayuzMapYarat();
        KullaniciArayuzuAyarla();
    }

    public void ArayuzMapYarat() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                JTextField b = new JTextField("i:" + i + "j:" + j);
                b.setForeground(Color.red);
                b.setBounds(75 * j, i * 38, 75, 38);
                b.setEditable(false);
                if (Yazlab2proje3.MapMatris[i][j] == -1) {
                    b.setBackground(Color.black);
                } else {
                    b.setBackground(Color.white);
                }
                MapFieldMatris[i][j] = b;
                this.add(b);
            }
        }

    }

    public void BitisAyarla(int i, int j) {
        if (i - 1 >= 0) {
            Yazlab2proje3.RMatris[(25 * (i - 1)) + j][(25 * i) + j] = 100;
        }
        //sag üst
        if (i - 1 >= 0 && j + 1 < 25) {
            Yazlab2proje3.RMatris[(25 * (i - 1)) + j + 1][(25 * i) + j] = 100;

        }
        //sol üst
        if (i - 1 >= 0 && j - 1 >= 0) {
            Yazlab2proje3.RMatris[(25 * (i - 1)) + j - 1][(25 * i) + j] = 100;
        }
        //sol
        if (j - 1 >= 0) {
            Yazlab2proje3.RMatris[(25 * i) + j - 1][(25 * i) + j] = 100;
        }
        //sag
        if (j + 1 < 25) {
            Yazlab2proje3.RMatris[(25 * i) + j + 1][(25 * i) + j] = 100;
        }
        //asagi
        if (i + 1 < 25) {
            Yazlab2proje3.RMatris[(25 * (i + 1)) + j][(25 * i) + j] = 100;
        }
        //sag asagi
        if (i + 1 < 25 && j + 1 < 25) {
            Yazlab2proje3.RMatris[(25 * (i + 1)) + j + 1][(25 * i) + j] = 100;
        }
        //sag asagi
        if (i + 1 < 25 && j - 1 >= 0) {
            Yazlab2proje3.RMatris[(25 * (i + 1)) + j - 1][(25 * i) + j] = 100;
        }
        for (int k = 0; k < 625; k++) {
            if (25 * i + j == k) {
                Yazlab2proje3.RMatris[25 * i + j][k] = 0;
            } else {
                Yazlab2proje3.RMatris[25 * i + j][k] = -1;
            }

        }
    }

    public int QMatrisiDoldur(int startI, int startJ, int finishI, int finishJ) {
        //System.out.println("Methoda Girildi i: " + startI + " j: " + startJ);
        int rastgeleIndex = 0;
        MaxValue = -1;
        Komsular.clear();
        KomsularSonraki.clear();
        int start=(startI*50)+startJ;
        int finish=(finishI*50)+finishJ;
        float randomInterval = 0 + rnd.nextFloat() * (1 - 0);

        for (i = 0; i < 625; i++) {
            if (Yazlab2proje3.RMatris[25 * startI + startJ][i] != -1) {
                Komsular.add(i);
            }
        }
        
        Collections.shuffle(Komsular);
        if (randomInterval <= EpsilonDeger) {
            rastgeleIndex = rnd.nextInt(Komsular.size() - 0) + 0;  
        } else {
            double maxValue = 0;
            for (int k = 0; k < Komsular.size(); k++) {
                if (Yazlab2proje3.QMatris[25 * startI + startJ][Komsular.get(k)] >= maxValue) {
                    maxValue = Yazlab2proje3.QMatris[25 * startI + startJ][Komsular.get(k)];
                    rastgeleIndex = k;
                }
            }

        }

        if (Yazlab2proje3.MapMatris[Komsular.get(rastgeleIndex) / 25][Komsular.get(rastgeleIndex) % 25] == -1) {
            //System.out.println("komsu cıkarildi");       
            Yazlab2proje3.QMatris[25 * startI + startJ][Komsular.get(rastgeleIndex)] = -10;
            Yazlab2proje3.Skor -= 5;
            return 0;
        }

        Yazlab2proje3.Skor += 3;
        Yazlab2proje3.AdimSayisi++;
        for (i = 0; i < 625; i++) {
            if (Yazlab2proje3.RMatris[Komsular.get(rastgeleIndex)][i] != -1) {
                KomsularSonraki.add(i);
            }
        }

        for (i = 0; i < KomsularSonraki.size(); i++) {
            if (Yazlab2proje3.QMatris[Komsular.get(rastgeleIndex)][KomsularSonraki.get(i)] > MaxValue) {
                MaxValue = Yazlab2proje3.QMatris[Komsular.get(rastgeleIndex)][KomsularSonraki.get(i)];

            }
        }
        if(start==finish){
        Yazlab2proje3.QMatris[25 * startI + startJ][Komsular.get(rastgeleIndex)] = (Yazlab2proje3.RMatris[25 * startI + startJ][Komsular.get(rastgeleIndex)] + 0.8 * MaxValue)+5;
        }
        else{
        Yazlab2proje3.QMatris[25 * startI + startJ][Komsular.get(rastgeleIndex)] = (Yazlab2proje3.RMatris[25 * startI + startJ][Komsular.get(rastgeleIndex)] + 0.8 * MaxValue)+3;
        }
        
  

        startI = Komsular.get(rastgeleIndex) / 25;
        startJ = Komsular.get(rastgeleIndex) % 25;
        //jTextFieldMatris[startI][startJ].setBackground(Color.green);
        sonrakiI = startI;
        sonrakiJ = startJ;
        return 1;
    }

    public void EnKisaYoluBul(int x, int y) {
        int Current = (25 * x) + y;
        int qValue = 0;
        double maxValue = 0;
        for (int q = 0; q < 625; q++) {
            if (maxValue <= Yazlab2proje3.QMatris[Current][q]) {
                maxValue = Yazlab2proje3.QMatris[Current][q];
                qValue = q;
            }

        }
        EnKisaYol.add(qValue);
        sonrakiI = qValue / 25;
        sonrakiJ = qValue % 25;
        MapFieldMatris[sonrakiI][sonrakiJ].setBackground(Color.green);
    }

    public void KullaniciArayuzuAyarla() {
        JTextField text1 = new JTextField("Başlangıç I");
        text1.setBounds(330, 960, 70, 33);
        text1.setEditable(false);
        BaslangicPosI.setBounds(400, 960, 33, 33);
        this.add(BaslangicPosI);
        this.add(text1);

        JTextField text2 = new JTextField("Başlangıç J");
        text2.setBounds(440, 960, 70, 33);
        text2.setEditable(false);
        BaslangicPosJ.setBounds(510, 960, 33, 33);
        this.add(BaslangicPosJ);
        this.add(text2);

        JTextField text3 = new JTextField("Bitiş I");
        text3.setBounds(570, 960, 50, 33);
        text3.setEditable(false);
        BitisPosI.setBounds(620, 960, 33, 33);
        this.add(BitisPosI);
        this.add(text3);

        JTextField text4 = new JTextField("Bitiş J");
        text4.setBounds(660, 960, 50, 33);
        text4.setEditable(false);
        BitisPosJ.setBounds(710, 960, 33, 33);
        this.add(BitisPosJ);
        this.add(text4);

        JButton SaveButton = new JButton("Baslat");
        SaveButton.setBounds(750, 960, 70, 30);
        this.add(SaveButton);

        SaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int startI = Integer.parseInt(BaslangicPosI.getText());
                int startJ = Integer.parseInt(BaslangicPosJ.getText());
                int finishI = Integer.parseInt(BitisPosI.getText());
                int finishJ = Integer.parseInt(BitisPosJ.getText());
                MapFieldMatris[startI][startJ].setBackground(Color.blue);
                MapFieldMatris[finishI][finishJ].setBackground(Color.magenta);
                BitisAyarla(finishI, finishJ);
                    int kontrol2=0;
                    int iteration=0;
                while (iteration<IterasyonSayisi){
                    System.out.println("Su anki iterasyon : " + iteration);
                    sonrakiI = startI;
                    sonrakiJ = startJ;
                    int x = sonrakiI * 25 + sonrakiJ;
                    int y = finishI * 25 + finishJ;
                    do {
                        int value = QMatrisiDoldur(sonrakiI, sonrakiJ, finishI, finishJ);
                        if (value == 0) {

                            Yazlab2proje3.Maliyet = -5;
                            MaliyetAdimMatrisi[iteration][0] = Yazlab2proje3.Skor;
                            MaliyetAdimMatrisi[iteration][1] = Yazlab2proje3.AdimSayisi;
                            MaliyetAdimMatrisi[iteration][2] = Yazlab2proje3.Maliyet;
//                            System.out.println("Score: " + Yazlab2proje3.Score);
//                            System.out.println("Step: " + Yazlab2proje3.Step);
//                            System.out.println("Cost: " + Yazlab2proje3.Cost);
                            Yazlab2proje3.Skor = 0;
                            Yazlab2proje3.AdimSayisi = 0;
                            break;
                        }
                        x = sonrakiI * 25 + sonrakiJ;
                        if (x == y) {
                            Yazlab2proje3.Skor += 5;
                            Yazlab2proje3.Maliyet = 100;
                            MaliyetAdimMatrisi[iteration][0] = Yazlab2proje3.Skor;
                            MaliyetAdimMatrisi[iteration][1] = Yazlab2proje3.AdimSayisi;
                            MaliyetAdimMatrisi[iteration][2] = Yazlab2proje3.Maliyet;
                            System.out.println("- Skor: " + Yazlab2proje3.Skor);
                            System.out.println("- Adim Sayisi: " + Yazlab2proje3.AdimSayisi);
                            System.out.println("- Maliyet: " + Yazlab2proje3.Maliyet);
                            Yazlab2proje3.Skor = 0;
                            Yazlab2proje3.AdimSayisi = 0;
                        }
                    } while (x != y);
                    if (iteration % 10000 == 0) {
                        EpsilonDeger -= 0.01;
                    }
                         iteration++;
                }
                sonrakiI = startI;
                sonrakiJ = startJ;
                int a = sonrakiI * 25 + sonrakiJ;
                int b = finishI * 25 + finishJ;
                do {

                    EnKisaYoluBul(sonrakiI, sonrakiJ);
                    a = sonrakiI * 25 + sonrakiJ;
                } while (a != b);
                MapFieldMatris[startI][startJ].setBackground(Color.blue);
                MapFieldMatris[finishI][finishJ].setBackground(Color.magenta);
                Yazlab2proje3.GrafikArayuzuYarat();

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
