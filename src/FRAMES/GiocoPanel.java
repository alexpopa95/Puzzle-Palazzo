package FRAMES;

import DRAW.Immagine;
import GAMEOBJECTS.Carta;
import GAMEOBJECTS.SpriteTable;
import RES.Gioco;
import RES.Notification;
import RES.Tempo;
import RES.Utilities;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class GiocoPanel extends WebPanel implements Runnable {

    private boolean running;

    private Thread thread;

    private ArrayList<Carta> carte;
    private SpriteTable tabella;
    private Carta selectedItem;
    private int gapX = 0, gapY = 0;

    private Tempo tempo;

    int alert1 = 25;
    int alert2 = 10;
    private boolean carteOrdinate = false;
    private boolean alertTempoVisto = false;

    public GiocoPanel() {
        caricaComponenti(Immagine.getImmaginiCarte(Utilities.randInt(1, Immagine.numeroSprite)), null);
        carteOrdinate = false;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedItem != null) {
                    gapX = 0;
                    gapY = 0;
                    if (selectedItem.deveBloccarsi()) {
                        setUltimaCarta(selectedItem);
                        Notification.showTimer("Rimangono altre " + getNumeroCarteNonBloccate() + " carte!", Notification.MESSAGE, 3);
                    }
                    selectedItem = null;
                    //System.out.println("Rilasciato");
                }
                super.mouseReleased(e);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Gioco.frame.stato == Gioco.Stato.VINTO) return;
                if (Gioco.frame.stato == Gioco.Stato.VINTO) return;
                if (Gioco.frame.stato == Gioco.Stato.PAUSA) return;
                Point pointer = e.getPoint();
                if (selectedItem == null) {
                    for (Carta carta : carte) {
                        if (!carta.isBloccata()) {
                            if (pointer.getX() > carta.getX() && pointer.getX() < carta.getX() + carta.getLarghezza()) {
                                if (pointer.getY() > carta.getY() && pointer.getY() < carta.getY() + carta.getAltezza()) {
                                    selectedItem = carta;
                                    gapX = (int) (pointer.getX() - carta.getX());
                                    gapY = (int) (pointer.getY() - carta.getY());
                                    setPrimaCarta(carta);
                                    //System.out.println("Selezionato");
                                    return;
                                }
                            }
                        }
                    }
                }
                super.mouseClicked(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point pointer = e.getPoint();
                if (selectedItem != null) {
                    selectedItem.setX((int) pointer.getX() - gapX);
                    selectedItem.setY((int) pointer.getY() - gapY);
                }
                super.mouseDragged(e);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(Immagine.sfondo, 0, 0, this.getWidth(), this.getHeight(), this);

        //START DRAW -----------
        tabella.disegna(g);
        for(int i=carte.size()-1;i>=0;i--) {
            carte.get(i).disegna(g);
        }
        if(Gioco.frame.stato == Gioco.Stato.PAUSA) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        else if(Gioco.frame.stato == Gioco.Stato.VINTO) {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        else if(Gioco.frame.stato == Gioco.Stato.PERSO) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    private void verificaPosizioniCarte() {
        for(Carta carta : carte) {
            int x, y, larg, alt;
            x = carta.getX();
            y = carta.getY();
            larg = carta.getLarghezza();
            alt = carta.getAltezza();
            if(x<0 || y<0 || (x+larg)>this.getWidth() || (y+alt)>this.getHeight()) {
                if(x<0) {
                    carta.setX(0);
                }
                if(y<0) {
                    carta.setY(0);
                }
                if(x+larg > this.getWidth()) {
                    carta.setX(this.getWidth()-larg);
                }
                if(y+alt > this.getHeight()) {
                    carta.setY(this.getHeight()-alt);
                }
            }
        }
    }

    private void setPrimaCarta(Carta carta) {
        carte.remove(carta);
        carte.add(0, carta);
    }

    private void setUltimaCarta(Carta carta) {
        carte.remove(carta);
        carte.add(carta);
    }

    private void caricaComponenti(ArrayList<BufferedImage> imgCarte, BufferedImage imgTabella) {
        carte = new ArrayList<Carta>();
        for (BufferedImage img : imgCarte) {
            carte.add(new Carta(img, 10, 10));
        }
        if(imgTabella != null) {
            tabella = new SpriteTable(imgTabella, 0, 0);
        }
        else {
            tabella = new SpriteTable(0, 0, Color.ORANGE);
        }
    }

    public void aggiorna() {
        if(Gioco.frame.stato == Gioco.Stato.GIOCO) {
            if(tempo.getSeconds() != alert1 && tempo.getSeconds() != alert2) {
                alertTempoVisto = false;
            }
            if(tempo.getSeconds() == alert1 && !alertTempoVisto) {
                Notification.showTimer("Hai ancora "+alert1+" secondi!", Notification.WARNING, 3);
                alertTempoVisto = true;
            }
            if(tempo.getSeconds() == alert2 && !alertTempoVisto) {
                Notification.showTimer("Hai ancora "+alert2+" secondi!", Notification.ERROR, 3);
                alertTempoVisto = true;
            }
            int vinto = controllaVittoria();
            if(vinto == 1) {
                //HAI VINTO
                Gioco.frame.setVittoria(true);
            }
            else if(vinto == 0) {
                //HAI PERSO
                Gioco.frame.setVittoria(false);
            }
        }
    }

    public void pauseGioco() {
        tempo.pause();
    }

    public void resumeGioco() {
        tempo.resume();
    }

    public int controllaVittoria() {
        if(tempo.isFinito())
            return 0;
        for(Carta carta : carte) {
            if(!carta.isBloccata())
                return 2;
        }
        return 1;
    }

    public synchronized void start(){
        if(running)
            return;

        running=true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        tempo.stop();
        if(!running)
            return;

        System.err.println("\tpannello_gioco.stop()");
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double numeroAggiornamenti = 60.0;
        double ns = 1000000000 / numeroAggiornamenti;
        double delta = 0;
        int aggiornamenti = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        tempo = new Tempo(Gioco.total_time);
        Notification.showTimer("Buona Fortuna! Hai a disposizione "+Gioco.total_time+" secondi...", Notification.CLOCK, 4);
        while(running) {
            //Game LOOP
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime =now;
            if(delta >=1) {
                aggiorna();
                this.repaint();
                if(!carteOrdinate) ordinaComponenti();
                verificaPosizioniCarte();

                aggiornamenti++;
                delta--;
            }

            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer+=1000;
                //System.out.println("Aggiornamenti : "+aggiornamenti+"  Fps: "+frames);
                aggiornamenti = 0;
                frames = 0;
            }
        }
        stop();
    }

    public int getNumeroCarteNonBloccate() {
        int num = 0;
        for(Carta carta : carte) {
            if(!carta.isBloccata()) {
                num++;
            }
        }
        return num;
    }

    private void ordinaComponenti() {
        System.err.println("Ordino componenti");
        tabella.setX((this.getWidth() / 2) - (SpriteTable.larghezza / 2));
        tabella.setY(20);
        tabella.creaPosizioni();
        carte = tabella.assegnaPosizioniInTabella(carte);
        //Assegna posizioni Random
        carte = Utilities.randomList(carte);
        ArrayList<Point> pos = getPosizioniCarte();
        for(int i=0;i<carte.size();i++) {
            carte.get(i).setX((int) pos.get(i).getX());
            carte.get(i).setY((int) pos.get(i).getY());
        }
        carteOrdinate = true;
    }

    public ArrayList<Point> getPosizioniCarte() {
        ArrayList<Point> list = new ArrayList<Point>();
        for(int i=0;i<carte.size();i++) {
            int x, y;
            if(i<4) //sul lato sinistro
            {
                x = 20;
                if(i==0) {
                    y = 10;
                }
                else {
                    y = (int) (this.getHeight()/4.1+list.get(i-1).getY());
                }
            }
            else if(i<12) //Sul lato basso
            {
                y = this.getHeight() - (Carta.altezza + 20);
                x = (int) (this.getWidth() / 10 + list.get(i-1).getX()-3);
            } else //sul lato destro
            {
                x = this.getWidth()-Carta.larghezza-20;
                if(i==12) {
                    y = 10;
                }
                else {
                    y = (int) (this.getHeight()/4.1+list.get(i-1).getY());
                }
            }
            list.add(new Point(x, y));
        }
        return list;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
