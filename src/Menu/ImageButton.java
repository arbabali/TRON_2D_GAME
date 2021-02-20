/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import res.ResourceLoader;

/**
 * TO CREATE A BUTTON WITH IMAGE
 * @author Arbab Ali
 */
public class ImageButton extends JButton {
    private static final long serialVersionUID = 1;

    /** @serial */
    private Image image;

    /** @serial */
    private final Rectangle innerArea = new Rectangle();

    ImageButton(String path) throws IOException {
       image=ResourceLoader.loadImage(path);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            SwingUtilities.calculateInnerArea(this, innerArea);

            g.drawImage(image,
                innerArea.x, innerArea.y, innerArea.width, innerArea.height,
                this);
        }
    }
}
