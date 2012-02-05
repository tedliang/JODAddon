package net.jodreport.ooAddon.insertScript;

import com.sun.star.awt.ActionEvent;
import com.sun.star.awt.XActionListener;
import com.sun.star.lang.EventObject;

/**
 *
 * @author tedliang
 */
public class ScriptActionListener implements XActionListener {

    private final ScriptDialog dialog;

    public ScriptActionListener(ScriptDialog dialog) {
        this.dialog = dialog;
    }
    
    public void actionPerformed(ActionEvent arg0) {
        dialog.performOkAction();
    }

    public void disposing(EventObject arg0) {
    }

}
