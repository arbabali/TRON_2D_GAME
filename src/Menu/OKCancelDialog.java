/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 * OK CANCEL DIALOG
 * @author Arbab Ali
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class OKCancelDialog extends JDialog
{
    public static final int     OK = 1;
    public static final int     CANCEL = 0;

    protected int       btnCode;
    protected JPanel    btnPanel;
    protected JButton   btnOK;
    protected JButton   btnCancel;

    protected OKCancelDialog(JFrame frame, String name)
    {
        super(frame, name, true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        btnCode = CANCEL;
        btnOK = new JButton(actionOK);
        btnOK.setMnemonic('O');
        btnOK.setPreferredSize(new Dimension(90, 25));
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
        getRootPane().setDefaultButton(btnOK);
        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnOK);
        btnPanel.add(btnCancel);
    }

    public int getButtonCode()      { return btnCode; }

    protected abstract boolean processOK();
    protected abstract void processCancel();

    private AbstractAction  actionOK = new AbstractAction("OK")
    {
        public void actionPerformed(ActionEvent e)
        {
            if ( processOK() )
            {
                btnCode = OK;
                OKCancelDialog.this.setVisible(false);
            }
        }
    };

    private AbstractAction actionCancel = new AbstractAction("Cancel")
    {
        public void actionPerformed(ActionEvent e)
        {
            processCancel();
            btnCode = CANCEL;
            OKCancelDialog.this.setVisible(false);
        }
    };
}
