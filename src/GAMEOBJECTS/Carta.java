package GAMEOBJECTS;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class Carta {

    private BufferedImage image;
    private int altezza;
    private int larghezza;
    private int x, y;

    private boolean muovibile;

    public Carta(BufferedImage image, int x, int y, int altezza, int larghezza) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.altezza = altezza;
        this.larghezza = larghezza;
        muovibile = true;
    }

    public void disegna(Graphics g) {
        g.drawImage(image, x, y, altezza, larghezza, null);
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

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }

    public boolean isMuovibile() {
        return muovibile;
    }

    public void setMuovibile(boolean muovibile) {
        this.muovibile = muovibile;
    }
}
