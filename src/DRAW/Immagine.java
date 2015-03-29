package DRAW;

import com.alee.laf.panel.WebPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class Immagine {

    private static BufferedImage image;

    public static BufferedImage carica(String posizione, WebPanel panel) {

        try {
            image = ImageIO.read(panel.getClass().getResource(posizione));
        } catch (IOException ex) {
            Logger.getLogger(Immagine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;

    }
}
