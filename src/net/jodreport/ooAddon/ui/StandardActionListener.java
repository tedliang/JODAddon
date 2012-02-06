package net.jodreport.ooAddon.ui;

import com.sun.star.awt.ActionEvent;
import com.sun.star.awt.XActionListener;
import com.sun.star.lang.EventObject;

/**
 *
 * @author tedliang
 */
public class StandardActionListener implements XActionListener {

    private final StandardDialog dialog;

    public StandardActionListener(StandardDialog dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent ae) {
        dialog.performOkAction();
    }

    public void disposing(EventObject eo) {
    }

}
