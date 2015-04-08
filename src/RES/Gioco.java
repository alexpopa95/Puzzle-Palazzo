package RES;

import DRAW.Immagine;
import FRAMES.GiocoPanel;
import FRAMES.InformazioniPanel;
import FRAMES.IstruzioniPanel;
import FRAMES.InizioPanel;
import com.alee.extended.image.WebImage;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.notification.NotificationOption;
import com.alee.managers.tooltip.TooltipManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class Gioco extends WebFrame {

    public static Color primaryThemeColor = Color.decode("#7C4527");
    public static Color secondaryThemeColor = Color.RED;
    public static int total_time = 60;
    public static int margineErrore = 15;

    public static final String NOME = "Puzzle Palazzo Schiffanoia";
    public static int LARGHEZZA = 900;
    public static int ALTEZZA = 720;

    private final double posizione_divisore = .15d;


    public enum Stato {
        GIOCO,
        PAUSA,
        STOP,
        PERSO,
        VINTO
    };

    public Stato stato;

    public static Gioco frame;

    //Componenti finestra
    WebSplitPane interfaccia;
    WebLabel label_tempo;
    JProgressBar tempo;
    WebButton iniziaOFerma;
    WebButton istruzioni;
    WebToggleButton pausa;
    WebButton exit;
    WebPanel sinistra_panel;
    WebLabel app_info;
    GiocoPanel gioco_panel;
    WebPanel istruzioni_panel;
    WebPanel informazioni_panel;
    WebPanel inizio_panel;

    public Gioco() {
        initPanes();
        initComp();
        initList();
        stato = Stato.STOP;
        setFrameFullScreenMode(this, true);
    }

    private void initPanes() {
        gioco_panel = null;
        istruzioni_panel = new IstruzioniPanel();
        informazioni_panel = new InformazioniPanel();
        inizio_panel = new InizioPanel();
    }

    private void initComp() {
        interfaccia = new WebSplitPane();
        label_tempo = new WebLabel("Tempo rimasto:");
        tempo = new JProgressBar();
        tempo.setIndeterminate(false);
        iniziaOFerma = new WebButton();
        istruzioni = new WebButton("Istruzioni");
        pausa = new WebToggleButton("PAUSA");
        exit = new WebButton("Esci");
        exit.setForeground(Color.RED);
        exit.setPreferredSize(new Dimension(0, 50));
        exit.setHorizontalAlignment(WebLabel.CENTER);
        sinistra_panel = new WebPanel();
        app_info = new WebLabel("INFORMAZIONI", SwingConstants.CENTER);
        SpringLayout layout = new SpringLayout();
        sinistra_panel.setLayout(layout);

        sinistra_panel.add(tempo);
        sinistra_panel.add(label_tempo);
        sinistra_panel.add(iniziaOFerma);
        sinistra_panel.add(istruzioni);
        sinistra_panel.add(pausa);
        sinistra_panel.add(app_info);
        sinistra_panel.add(exit);

        int margine = 5;
        layout.putConstraint(SpringLayout.WEST, label_tempo, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, label_tempo, margine, SpringLayout.NORTH, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, label_tempo, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, tempo, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, tempo, margine, SpringLayout.SOUTH, label_tempo);
        layout.putConstraint(SpringLayout.EAST, tempo, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, iniziaOFerma, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, iniziaOFerma, margine, SpringLayout.SOUTH, tempo);
        layout.putConstraint(SpringLayout.EAST, iniziaOFerma, -margine, SpringLayout.EAST, sinistra_panel);


        layout.putConstraint(SpringLayout.WEST, istruzioni, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, istruzioni, margine, SpringLayout.SOUTH, iniziaOFerma);
        layout.putConstraint(SpringLayout.EAST, istruzioni, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, pausa, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, pausa, margine+5, SpringLayout.SOUTH, istruzioni);
        layout.putConstraint(SpringLayout.EAST, pausa, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, app_info, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, app_info, -margine, SpringLayout.EAST, sinistra_panel);
        layout.putConstraint(SpringLayout.SOUTH, app_info, -margine, SpringLayout.NORTH, exit);

        layout.putConstraint(SpringLayout.WEST, exit, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, exit, -margine, SpringLayout.EAST, sinistra_panel);
        layout.putConstraint(SpringLayout.SOUTH, exit, -margine, SpringLayout.SOUTH, sinistra_panel);


        interfaccia.setLeftComponent(sinistra_panel);
        interfaccia.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        setContentPane(interfaccia);
        setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pack();
        setVisible(true);

        interfaccia.setDividerLocation(posizione_divisore);
        interfaccia.setContinuousLayout(true);
        interfaccia.setEnabled(false);

    }

    public void setFrameFullScreenMode(JFrame app, boolean fullscreen){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if(fullscreen) {    //FULLSCREEN MODE
            if (gd.getFullScreenWindow() == null){
                app.dispose();
                app.setUndecorated(true);
                gd.setFullScreenWindow(app);
                app.setVisible(true);
            }
        }
        else{   //WINDOWS MODE
            if(gd.getFullScreenWindow() != null) {
                app.dispose();
                app.setUndecorated(false);
                gd.setFullScreenWindow(null);
                app.setVisible(true);
            }
        }
        ALTEZZA = app.getHeight();
        LARGHEZZA = app.getWidth();
        app.revalidate();
    }

    private void initList() {
        TooltipManager.setTooltip(iniziaOFerma, "Inizia una nuova partita", TooltipWay.up, 0);
        iniziaOFerma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case VINTO:
                    case PERSO:
                        TooltipManager.setTooltip(iniziaOFerma, "Inizia una nuova partita", TooltipWay.up, 0);
                        if (gioco_panel != null) gioco_panel.stop();
                        setPannelloDestro(inizio_panel);
                        pausa.setVisible(false);
                        istruzioni.setVisible(true);
                        iniziaOFerma.setIcon(Utilities.getIcon(Immagine.inizia));
                        setPannelloDestro(inizio_panel);
                        stato = Stato.STOP;

                        break;
                    case STOP:
                        TooltipManager.setTooltip(iniziaOFerma, "Termina la partita", TooltipWay.up, 0);
                        if (gioco_panel != null) gioco_panel.stop();
                        gioco_panel = new FRAMES.GiocoPanel();
                        setPannelloDestro(gioco_panel);
                        gioco_panel.start();
                        iniziaOFerma.setIcon(Utilities.getIcon(Immagine.stop));
                        pausa.setVisible(true);
                        istruzioni.setVisible(false);
                        stato = Stato.GIOCO;
                        break;
                    case PAUSA:
                    case GIOCO:
                        TooltipManager.setTooltip(iniziaOFerma, "Inizia una nuova partita", TooltipWay.up, 0);
                        if (gioco_panel != null) gioco_panel.stop();
                        pausa.setVisible(false);
                        istruzioni.setVisible(true);
                        iniziaOFerma.setIcon(Utilities.getIcon(Immagine.inizia));
                        setPannelloDestro(inizio_panel);
                        stato = Stato.STOP;
                        break;
                }
                System.err.println(stato.toString());
            }
        });
        TooltipManager.setTooltip(istruzioni, "Vedi le istruzioni del Puzzle", TooltipWay.down, 0);
        istruzioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setPannelloDestro(istruzioni_panel);
                switch (stato) {
                    case STOP:
                    case VINTO:
                    case PERSO:
                        setPannelloDestro(istruzioni_panel);
                }
                System.err.println(stato.toString());
            }
        });

        TooltipManager.setTooltip(pausa, "Metti in pausa il gioco", TooltipWay.up, 0);
        pausa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        TooltipManager.setTooltip(pausa, "Riprendi il gioco", TooltipWay.up, 0);
                        pausa.setText("RIPRENDI");
                        gioco_panel.pauseGioco();
                        stato = Stato.PAUSA;
                        break;
                    case PAUSA:
                        TooltipManager.setTooltip(pausa, "Metti in pausa il gioco", TooltipWay.up, 0);
                        pausa.setText("PAUSA");
                        gioco_panel.resumeGioco();
                        stato = Stato.GIOCO;
                        break;
                }
                System.err.println(stato.toString());
            }
        });

        TooltipManager.setTooltip(exit, "Esci dal gioco", TooltipWay.up, 0);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Notification.showCloseOption("Vuoi davvero chiudere?", Notification.APPLICATION);
            }
        });

        TooltipManager.setTooltip(app_info, "Vedi le informazioni", TooltipWay.up, 0);
        app_info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final WebPopOver popOver = new WebPopOver (e.getComponent());
                popOver.setCloseOnFocusLoss ( true );
                popOver.setMargin ( 10 );
                popOver.setLayout ( new VerticalFlowLayout() );
                final WebImage icon = new WebImage ( WebLookAndFeel.getIcon(16) );
                final WebLabel titleLabel = new WebLabel ( "Informazioni", WebLabel.CENTER );
                final WebButton closeButton = new WebButton ( Utilities.getIcon(Immagine.croce), new ActionListener()
                {
                    @Override
                    public void actionPerformed ( final ActionEvent e )
                    {
                        popOver.dispose ();
                    }
                } ).setUndecorated ( true );
                popOver.add(new GroupPanel(GroupingType.fillMiddle, 4, closeButton).setMargin(0, 0, 10, 0));
                popOver.add(new WebLabel("Puzzle Palazzo Schiffanoia", SwingConstants.CENTER));
                popOver.add(new WebLabel("-", SwingConstants.CENTER));
                popOver.add ( new WebLabel ( "Creato da Alexandru Popa", SwingConstants.CENTER ) );
                popOver.add ( new WebLabel ( "classe 5°G", SwingConstants.CENTER ) );
                popOver.add ( new WebLabel ( "AS 2014/2015", SwingConstants.CENTER ) );
                popOver.show (e.getComponent());
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

    public void setTempoMaxMin(int max, int min) {
        tempo.setMaximum(max);
        tempo.setMinimum(min);
    }

    public void setTempoValue(int value) {
        tempo.setValue(value);
    }

    public static void main(String args[]) {
        WebLookAndFeel.install();
        frame = new Gioco();
        Immagine.caricaImmagini();
        Immagine.caricaImmaginiCarte();

        frame.setPannelloDestro(frame.inizio_panel);

        frame.pausa.setVisible(false);
        frame.istruzioni.setVisible(true);

        frame.iniziaOFerma.setIcon(Utilities.getIcon(Immagine.inizia));

        frame.setBackground(primaryThemeColor);
        frame.sinistra_panel.setBackground(primaryThemeColor);

        frame.revalidate();
    }

    public void setVittoria(boolean vinto) {
        if(vinto) {
            stato = Stato.VINTO;
        }
        else {
            stato = Stato.PERSO;
        }
        TooltipManager.setTooltip(iniziaOFerma, "Torna alla pagina principale", TooltipWay.up, 0);
        pausa.setVisible(false);
        istruzioni.setVisible(false);
        iniziaOFerma.setIcon(Utilities.getIcon(Immagine.rinizia));
    }
}
