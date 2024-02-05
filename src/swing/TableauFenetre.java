package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class TableauFenetre extends JFrame {
    
    private TableauPanel tablePanel;

    public TableauFenetre(int rows, int columns, BufferedImage image) {
        setTitle("Puzzle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int[][] multiplicationTable = new int[rows][columns];

        int currentNumber = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                multiplicationTable[i][j] = currentNumber++;
            }
        }

        tablePanel = new TableauPanel(multiplicationTable, image);
        add(tablePanel);

        JPanel buttonPanel = new JPanel();
        JButton rotate90Button = new JButton("Rotation 90°");
        JButton shuffleButton = new JButton("Mélanger");
        JButton rotateMinus90Button = new JButton("Rotation -90°");

        rotate90Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.rotate90Degrees();
            }
        });

        rotateMinus90Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.rotateMinus90Degrees();
            }
        });
        
        shuffleButton.addActionListener(e -> shuffleImages());

        buttonPanel.add(rotate90Button);
        buttonPanel.add(shuffleButton);
        buttonPanel.add(rotateMinus90Button);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
    
    private void shuffleImages() {
        ArrayList<BufferedImage> subImages = tablePanel.getSubImages();
        Collections.shuffle(subImages);
        tablePanel.setSubImages(subImages);
        tablePanel.repaint();
    }
}
