/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.jodreport.ooAddon.insertScript;

import net.jodreport.ooAddon.report.*;
import com.sun.star.awt.ActionEvent;
import com.sun.star.awt.XActionListener;
import com.sun.star.lang.EventObject;

/**
 *
 * @author teddy
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
