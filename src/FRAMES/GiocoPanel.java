package FRAMES;

import DRAW.Immagini;
import GAMEOBJECTS.Carta;
import com.alee.laf.panel.WebPanel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class GiocoPanel extends WebPanel implements Runnable, ComponentListener {

    private boolean running;

    private int altezza;
    private int larghezza;

    public static ArrayList<Carta> carte;

    public GiocoPanel() {
        Immagini.carica(this);
        Immagini.caricaImmaginiCarte(1, this);
        caricaCarte();
        Thread t = new Thread(this);
        t.start();

    }

    private void caricaCarte() {
        carte = new ArrayList<Carta>();
        for(int i=0;i<Immagini.numeroCarte;i++) {
            carte.add(new Carta(Immagini.carte.get(i), 0, 0, 100, 152));
        }
    }

    @Override
    public void run() {
        running = false;
        while(running) {
            //LOOP
            System.out.println("\t \t Aggiorno Canvas");
            //------------
        }
    }

    public void paintComponent(Graphics g) {
        larghezza = getWidth();
        altezza = getHeight();
        g.drawImage(Immagini.sfondo, 0, 0, larghezza, altezza,  this);
        for(int i=0;i<carte.size();i++) {
            carte.get(i).disegna(g);
        }
        ordinaComponenti();
    }

    private void ordinaComponenti() {
        List posizioni = new List();
        for(int i=0;i<Immagini.numeroCarte;i++) {
            if(i<4) //sul lato sinistro
            {
                carte.get(i).setX(30);
                if(i==0) {
                    carte.get(i).setY(50);
                }
                else {
                    carte.get(i).setY(altezza/5+carte.get(i-1).getY());
                }
            }
            else if(i<12) //Sul lato basso
            {
                carte.get(i).setY(altezza - (carte.get(i).getAltezza() + 80));
                carte.get(i).setX(larghezza / 10 + carte.get(i - 1).getX()-3);
            } else //sul lato destro
            {
                carte.get(i).setX(larghezza-carte.get(i).getLarghezza()+10);
                if(i==12) {
                    carte.get(i).setY(50);
                }
                else {
                    carte.get(i).setY(altezza/5+carte.get(i-1).getY());
                }
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //ordinaComponenti();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
