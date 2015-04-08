package DRAW;

import java.awt.image.BufferedImage;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class SpriteSheet {

    private final BufferedImage img;
    private final int larghezzaTot = 640;
    private final int altezzaTot = 848;
    private final int larghezza = 160;
    private final int altezza = 212;


    public SpriteSheet (BufferedImage image) {
        img=image;
    }

    public BufferedImage sottraiCarta(int colonna, int riga){
        //System.out.println(((colonna * larghezza)-larghezza)+"  :  "+((riga * altezza)-altezza)+" / "+larghezza +":"+ altezza);

        BufferedImage image = img.getSubimage((colonna * larghezza)-larghezza, (riga * altezza)-altezza, larghezza, altezza);
        return image;
    }
}
