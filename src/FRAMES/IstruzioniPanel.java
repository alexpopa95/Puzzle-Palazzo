package FRAMES;

import RES.Gioco;
import com.alee.laf.panel.WebPanel;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class IstruzioniPanel extends WebPanel {

    private WebPanel panel;

    public IstruzioniPanel() {
        initPanel();
    }

    private void initPanel() {
        setBackground(Gioco.primaryThemeColor);
    }
}
