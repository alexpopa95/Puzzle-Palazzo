package FRAMES;

import RES.Gioco;
import com.alee.laf.panel.WebPanel;

/**
 * Created by alexpopa95 on 08/04/15.
 */
public class InizioPanel extends WebPanel {

    private WebPanel panel;

    public InizioPanel() {
        initPanel();
    }

    private void initPanel() {
        setBackground(Gioco.primaryThemeColor);
    }
}
