package GAMEOBJECTS;

import RES.Gioco;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class Carta {

    private BufferedImage image;
    public static final int altezza = 142;
    public static final int larghezza = 90;
    private int x, y;

    private Point posizione;

    private boolean bloccata;

    public Carta(BufferedImage image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
        bloccata = false;
    }

    public boolean deveBloccarsi() {
        int posx, posy;
        posx = (int) posizione.getX();
        posy = (int) posizione.getY();
        if(x>=(posx- Gioco.margineErrore) && x<=(posx+Gioco.margineErrore)) {
            if(y>=(posy-Gioco.margineErrore) && y<=(posy+Gioco.margineErrore)) {
                setBloccata(true);
                x = posx;
                y = posy;
                return true;
            }
        }
        return false;
    }

    public void disegna(Graphics g) {
        g.drawImage(image, x, y, larghezza, altezza, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public boolean isBloccata() {
        return bloccata;
    }

    public void setBloccata(boolean bloccata) {
        this.bloccata = bloccata;
    }

    public Point getPosizione() {
        return posizione;
    }

    public void setPosizione(Point posizione) {
        this.posizione = posizione;
    }
}
