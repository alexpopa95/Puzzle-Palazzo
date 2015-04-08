package GAMEOBJECTS;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by alexpopa95 on 08/04/15.
 */
public class SpriteTable {
    public static final int altezza = Carta.altezza*4;
    public static final int larghezza = Carta.larghezza*4;

    private int x;
    private int y;
    private BufferedImage image;
    private Color coloreSfondo;

    ArrayList<Point> posizioni;


    public SpriteTable(BufferedImage image, int x, int y) {
        posizioni = new ArrayList<Point>();
        this.x = x;
        this.y = y;
        this.image = image;
        creaPosizioni();
    }

    public SpriteTable(int x, int y, Color coloreSfondo) {
        posizioni = new ArrayList<Point>();
        this.x = x;
        this.y = y;
        this.coloreSfondo = coloreSfondo;
    }

    public void disegna(Graphics g) {

        if(image == null) {
            for(Point pos : posizioni) {
                x = (int) pos.getX();
                y = (int) pos.getY();
                g.setColor(coloreSfondo);
                g.fillRect(x, y, Carta.larghezza, Carta.altezza);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, Carta.larghezza, Carta.altezza);
            }
        }
        else {
            g.drawImage(image, this.x, this.y, larghezza, altezza, null);
        }
    }

    public void creaPosizioni() {
        int x, y;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4;j++) {
                x = this.x;
                y = this.y;
                for(int k=0;k<i;k++) {
                    x+=Carta.larghezza;
                }
                for(int k=0;k<j;k++) {
                    y+=Carta.altezza;
                }
                Point pos = new Point(x, y);
                posizioni.add(pos);
            }
        }
    }

    public ArrayList<Carta> assegnaPosizioniInTabella(ArrayList<Carta> carte) {
        for(int i=0; i<carte.size();i++) {
            carte.get(i).setPosizione(posizioni.get(i));
        }
        return carte;
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

}
