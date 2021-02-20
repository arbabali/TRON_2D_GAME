/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Ok DIALOG
 * @author Arbab Ali
 */

 abstract class OKDialog extends JDialog
        {
    public static final int     OK2 = 1;
   

    protected int       btnCode;
    protected JPanel    btnPanel;
    protected JButton   btnOK;
 

    protected OKDialog(JFrame frame, String name)
    {
        super(frame, name, true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
   
        btnOK = new JButton(actionOK);
        btnOK.setMnemonic('O');
        btnOK.setPreferredSize(new Dimension(90, 25));
       
     
    
        getRootPane().setDefaultButton(btnOK);
        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnOK);
       
    }

    public int getButtonCode()      { return btnCode; }

    protected abstract boolean processOK();
  

    private AbstractAction  actionOK = new AbstractAction("OK")
    {
        public void actionPerformed(ActionEvent e)
        {
            if ( processOK() )
            {
                btnCode = OK2;
                OKDialog.this.setVisible(false);
            }
        }
    };


}