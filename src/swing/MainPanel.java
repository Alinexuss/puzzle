/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Nikami
 */
public class MainPanel extends JPanel {

    private final JTextField textFieldRows;
    private final  JTextField textFieldColumns;
    private final JButton buttonCreateTable;
    private final JButton buttonLoadImage;

    private BufferedImage image;

    public MainPanel() {
        setLayout(new FlowLayout());

        textFieldRows = new JTextField(5);

        JLabel labelColumns = new JLabel(" * ");
        textFieldColumns = new JTextField(5);

        buttonCreateTable = new JButton("Créer Puzzle");
        buttonLoadImage = new JButton("Charger Image");

        add(textFieldRows);
        add(labelColumns);
        add(textFieldColumns);
        add(buttonCreateTable);
        add(buttonLoadImage);

        buttonCreateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = getRows();
                int columns = getColumns();
                TableauFenetre tableFrame = new TableauFenetre(rows, columns, image);
                tableFrame.setVisible(true);
            }
        });

        buttonLoadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(MainPanel.this);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    image = loadImage(selectedFile);
                    repaint();
                }
            }
        });
    }

    int getRows() {
        return Integer.parseInt(textFieldRows.getText());
    }

    int getColumns() {
        return Integer.parseInt(textFieldColumns.getText());
    }

    private void loadAndDivideImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                image = ImageIO.read(selectedFile);
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int rows = getRows();
            int columns = getColumns();

            int subImageWidth = imageWidth / columns;
            int subImageHeight = imageHeight / rows;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int x = j * subImageWidth;
                    int y = i * subImageHeight;

                    int subImageNumber = i * columns + j + 1;

                    g.drawImage(image.getSubimage(x, y, subImageWidth, subImageHeight),
                            x, y, subImageWidth, subImageHeight, this);

                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(subImageNumber),
                            x + subImageWidth / 2, y + subImageHeight / 2);
                }
            }
        }
    }
    
    public JButton getButtonCreateTable() {
        return buttonCreateTable;
    }
    
    private BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
