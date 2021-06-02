/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2proje3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class G extends JPanel {

    int margin = 50;
    int margin2 = 550;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int genislik = 500;
        int yukseklik = 500;
        // graph 1
        g1.draw(new Line2D.Double(margin, margin, margin, yukseklik - margin + 50));
        g1.draw(new Line2D.Double(margin, yukseklik - margin, genislik - margin, yukseklik - margin));
        double x = (double) (genislik - 2 * margin) / (MapFrame.MaliyetAdimMatrisi.length - 1);
        double scale = (double) (yukseklik - 2 * margin) / getMax(2);
        g1.setPaint(Color.green);
        for (int i = 0; i < MapFrame.MaliyetAdimMatrisi.length; i++) {
            double x1 = margin + i * x;
            double y1 = yukseklik - margin - scale * MapFrame.MaliyetAdimMatrisi[i][2];

            g1.draw(new Line2D.Double(x1+2, yukseklik - margin+2, x1+2, y1+2));

        }

        // graph 2
        int genislik2 = 1500;
        int yukseklik2 = 1500;
        g1.setPaint(Color.black);
        g1.draw(new Line2D.Double(margin2, margin2, margin2, yukseklik2 - margin2));
        g1.draw(new Line2D.Double(margin2, yukseklik2 - margin2, genislik2 - margin2 + 50, yukseklik2 - margin2));
        double x2 = (double) (genislik2 - 2 * margin2) / (MapFrame.MaliyetAdimMatrisi.length - 1);
        double scale2 = (double) (yukseklik2 - 2 * margin2) / getMax(1);
        g1.setPaint(Color.ORANGE);
        for (int i = 0; i < MapFrame.MaliyetAdimMatrisi.length; i++) {
            double x1 = margin2 + i * x2;
            double y1 = yukseklik2 - margin2 - scale2 * MapFrame.MaliyetAdimMatrisi[i][1];
            g1.draw(new Line2D.Double(x1+2, yukseklik2 - margin2+2, x1+2, y1+2));

        }

    }

    private int getMax(int index) {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < MapFrame.MaliyetAdimMatrisi.length; i++) {
            if (MapFrame.MaliyetAdimMatrisi[i][index] > max) {
                max = MapFrame.MaliyetAdimMatrisi[i][index];
            }

        }
        return max;
    }

}
