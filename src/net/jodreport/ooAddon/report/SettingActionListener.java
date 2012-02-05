package net.jodreport.ooAddon.report;

import com.sun.star.awt.ActionEvent;
import com.sun.star.awt.XActionListener;
import com.sun.star.lang.EventObject;

/**
 *
 * @author tedliang
 */
public class SettingActionListener implements XActionListener {

    private final SettingDialog dialog;

    public SettingActionListener(SettingDialog dialog) {
        this.dialog = dialog;
    }
    
    public void actionPerformed(ActionEvent arg0) {
        dialog.performOkAction();
    }

    public void disposing(EventObject arg0) {
    }

}
