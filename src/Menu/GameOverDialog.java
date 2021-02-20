/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

/**
 *
 * @author Arbab Ali
 */
public  abstract class GameOverDialog  extends JDialog {
     public static final int     Restart = 1;
    public static final int     CANCEL = 0;

    protected int       btnCode;
    protected JPanel    btnPanel;
    protected JButton   btnRestart;
    protected JButton   btnCancel;

    protected GameOverDialog(JFrame frame, String name)
    {
        super(frame, name, true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        btnCode = CANCEL;
        btnRestart = new JButton(actionOK);
        btnRestart.setMnemonic('R');
        btnRestart.setPreferredSize(new Dimension(90, 25));
        btnCancel = new JButton(actionCancel);

        KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap inputMap = btnCancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnCancel.getActionMap();
        if (inputMap != null && actionMap != null)
        {
            inputMap.put(cancelKeyStroke, "cancel");
            actionMap.put("cancel", actionCancel);
        }
        btnCancel.setPreferredSize(new Dimension(90, 25));
        getRootPane().setDefaultButton(btnRestart);
        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnRestart);
        btnPanel.add(btnCancel);
    }

    public int getButtonCode()      { return btnCode; }

    protected abstract boolean processRestart();
    protected abstract void processCancel();

    private AbstractAction  actionOK = new AbstractAction("Restart")
    {
        public void actionPerformed(ActionEvent e)
        {
            if ( processRestart() )
            {
                btnCode = Restart;
                GameOverDialog.this.setVisible(false);
            }
        }
    };

    private AbstractAction actionCancel = new AbstractAction("Cancel")
    {
        public void actionPerformed(ActionEvent e)
        {
            processCancel();
            btnCode = CANCEL;
            GameOverDialog.this.setVisible(false);
        }
    };
}
