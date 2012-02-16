package net.jodreport.ooAddon.ui.script;

import com.sun.star.awt.ItemEvent;
import com.sun.star.awt.XItemListener;
import com.sun.star.lang.EventObject;

/**
 *
 * @author tedliang
 */
public class ShortcutSelectListener implements XItemListener {

    private final ScriptDialog dialog;

    public ShortcutSelectListener(ScriptDialog dialog) {
        this.dialog = dialog;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        dialog.insertScript(itemEvent.Selected);
    }

    public void disposing(EventObject arg0) {
    }

}
