/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nikami
 */
public class MainFenetre extends JFrame {
    private JLabel imageLabel;
    
    public MainFenetre() {
        setLayout(new FlowLayout());
        setTitle("Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);

        imageLabel = new JLabel();
       
        add(imageLabel);

        setVisible(true);
        setSize(700,100);
        setLocationRelativeTo(null);

        mainPanel.getButtonCreateTable().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = mainPanel.getRows();
                int columns = mainPanel.getColumns();
                BufferedImage image = getImageFromLabel(imageLabel);
                TableauFenetre tableFrame = new TableauFenetre(rows, columns, image);
                tableFrame.setVisible(true);
            }
        });
    }
    
    private BufferedImage getImageFromLabel(JLabel label) {
        Icon icon = label.getIcon();
        if (icon instanceof ImageIcon) {
            BufferedImage image = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            icon.paintIcon(label, g, 0, 0);
            g.dispose();
            return image;
        }
        return null;
    }

    public void setImageIcon(ImageIcon icon) {
        imageLabel.setIcon(icon);
    }
}