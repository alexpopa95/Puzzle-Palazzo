package RES;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by alexpopa95 on 08/04/15.
 */
public class Utilities {
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static ArrayList randomList(ArrayList list) {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
        return list;
    }

    public static ImageIcon getIcon(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }
}
