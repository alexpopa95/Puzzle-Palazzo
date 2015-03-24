import FRAMES.GiocoPanel;
import FRAMES.ImpostazioniPanel;
import FRAMES.IstruzioniPanel;
import com.alee.extended.label.WebLinkLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.splitpane.WebSplitPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class Gioco extends JFrame {

    public static final String NOME = "Puzzle Palazzo Schiffanoia";
    public static final int LARGHEZZA = 1200;
    public static final int ALTEZZA = 600;

    private final double posizione_divisore = .2d;

    private enum Stato {
        GIOCO,
        PAUSA,
        STOP,
        PERSO,
        VINTO
    };

    private Stato stato;

    private static Gioco gioco;

    //Componenti finestra
    WebSplitPane interfaccia;
    WebProgressBar tempo;
    WebButton inizia;
    WebButton istruzioni;
    WebToggleButton pausa;
    WebLabel impostazioni;
    WebPanel sinistra_panel;

    WebPanel gioco_panel;
    WebPanel istruzioni_panel;
    WebPanel impostazioni_panel;

    public Gioco() {
        initPanes();
        initComp();
        initList();
        stato = Stato.STOP;
    }

    private void initPanes() {
        gioco_panel = new GiocoPanel();
        istruzioni_panel = new IstruzioniPanel();
        impostazioni_panel = new ImpostazioniPanel();
    }

    private void initComp() {
        interfaccia = new WebSplitPane();
        tempo = new WebProgressBar();
        inizia = new WebButton("Inizia/Finisci");
        istruzioni = new WebButton("Istruzioni");
        pausa = new WebToggleButton("Pausa/Riprendi");
        impostazioni = new WebLinkLabel("Impostazioni");
        impostazioni.setHorizontalAlignment(WebLabel.CENTER);
        sinistra_panel = new WebPanel();

        SpringLayout layout = new SpringLayout();
        sinistra_panel.setLayout(layout);

        sinistra_panel.add(tempo);
        sinistra_panel.add(inizia);
        sinistra_panel.add(istruzioni);
        sinistra_panel.add(pausa);
        sinistra_panel.add(impostazioni);

        int margine = 5;
        layout.putConstraint(SpringLayout.WEST, tempo, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, tempo, margine, SpringLayout.NORTH, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, tempo, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, inizia, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, inizia, margine, SpringLayout.SOUTH, tempo);
        layout.putConstraint(SpringLayout.EAST, inizia, -margine, SpringLayout.EAST, sinistra_panel);


        layout.putConstraint(SpringLayout.WEST, istruzioni, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, istruzioni, margine, SpringLayout.SOUTH, inizia);
        layout.putConstraint(SpringLayout.EAST, istruzioni, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, pausa, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, pausa, margine+5, SpringLayout.SOUTH, istruzioni);
        layout.putConstraint(SpringLayout.EAST, pausa, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, impostazioni, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, impostazioni, -margine, SpringLayout.EAST, sinistra_panel);
        layout.putConstraint(SpringLayout.SOUTH, impostazioni, -margine, SpringLayout.SOUTH, sinistra_panel);

        interfaccia.setLeftComponent(sinistra_panel);
        interfaccia.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        interfaccia.setDividerLocation(1d);

        setContentPane(interfaccia);
        setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        interfaccia.setDividerLocation(posizione_divisore);
        interfaccia.setContinuousLayout(true);
        interfaccia.setEnabled(false);
    }

    private void initList() {
        inizia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        //Inizia nuova partita
                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });

        istruzioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        //Inizia nuova partita
                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });

        pausa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        //Inizia nuova partita
                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });

        impostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setPannelloDestro(impostazioni_panel);
            }
        });
    }

    public void setPannelloDestro(WebPanel panel) {
        interfaccia.setRightComponent(panel);
        revalidate();
    }

    public void revalidate() {
        interfaccia.setDividerLocation(posizione_divisore);
        interfaccia.setContinuousLayout(true);
        interfaccia.setEnabled(false);
    }

    public static void main(String args[]) {
        gioco = new Gioco();
    }
}
