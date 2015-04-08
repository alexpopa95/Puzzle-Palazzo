package DRAW;

import RES.Gioco;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class Immagine extends Canvas {

    public static final int numeroSprite = 2;
    public static BufferedImage sfondo;
    public static BufferedImage tabella;
    public static BufferedImage inizia;
    public static BufferedImage pausa;
    public static BufferedImage stop;
    public static BufferedImage croce;
    public static BufferedImage rinizia;
    public static HashMap<Integer, ArrayList<BufferedImage>> imgCarte;


    public static void caricaImmagini() {
        sfondo = carica("/IMMAGINI/sfondo.png");
        tabella = carica("/IMMAGINI/card/tabella.jpg");
        inizia = carica("/IMMAGINI/inizia.png");
        pausa = carica("/IMMAGINI/pausa.png");
        stop = carica("/IMMAGINI/termina.png");
        rinizia = carica("/IMMAGINI/rinizia.png");
        croce = carica("/IMMAGINI/croce.png");
    }

    public static void caricaImmaginiCarte() {
        HashMap<Integer, ArrayList<BufferedImage>> hmap = new HashMap<Integer, ArrayList<BufferedImage>>();
        ArrayList<BufferedImage> img;
        for(int k=1;k<=numeroSprite;k++) {
            img = new ArrayList<BufferedImage>();
            BufferedImage carteTot = carica("/IMMAGINI/card/" + k + ".jpg");
            SpriteSheet sp = new SpriteSheet(carteTot);
            //System.out.println("Carico le immagini delle carte...");
            for(int i=1; i<=4; i++) {
                for(int j=1;j<=4;j++) {
                    img.add(sp.sottraiCarta(i, j));
                }
            }
            hmap.put(k, img);
        }
        imgCarte = hmap;
    }

    public static ArrayList<BufferedImage> getImmaginiCarte(int num) {
        return imgCarte.get(num);
    }

    public static BufferedImage carica(String posizione) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Gioco.frame.getClass().getResource(posizione));
        } catch (IOException ex) {
            Logger.getLogger(Immagine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
}
