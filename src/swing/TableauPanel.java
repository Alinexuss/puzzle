/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;


import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nikami
 */
public class TableauPanel extends JPanel {
    
    private int[][] data;
    private int moveCount;
    private BufferedImage image;
    private ArrayList<BufferedImage> subImages;

    public TableauPanel(int[][] data, BufferedImage image) {
        this.data = data;
        this.image = image;
        if (image != null) {
            this.subImages = divideImage();
        } else {
            this.subImages = new ArrayList<>();
        }
        
        this.moveCount = 0;
        
        addMouseListener(new MouseAdapter() {
            private int selectedRow = -1;
            private int selectedCol = -1;

            @Override
            public void mousePressed(MouseEvent e) {
                int cellWidth = getWidth() / data[0].length;
                int cellHeight = getHeight() / data.length;

                int row = e.getY() / cellHeight;
                int col = e.getX() / cellWidth;
                
                System.out.println("Clicked on Row: " + row + ", Col: " + col);

                if (selectedRow == -1 && selectedCol == -1) {
                    selectedRow = row;
                    selectedCol = col;
                } else {
                    System.out.println("Before Swap - Image 1: " + data[selectedRow][selectedCol] + ", Image 2: " + data[row][col]);
                    swapImages(selectedRow, selectedCol, row, col);
                    System.out.println("After Swap - Image 1: " + data[selectedRow][selectedCol] + ", Image 2: " + data[row][col]);

                    selectedRow = -1;
                    selectedCol = -1;

                    moveCount++;
                    if (isImageReconstructed()) {
                        showSuccessMessage();
                    }

                    repaint();
                }
            }
        });
    }

    private void swapImages(int row1, int col1, int row2, int col2) {
        BufferedImage tempImage = subImages.get(data[row1][col1] - 1);
        subImages.set(data[row1][col1] - 1, subImages.get(data[row2][col2] - 1));
        subImages.set(data[row2][col2] - 1, tempImage);

        int tempData = data[row1][col1];
        data[row1][col1] = data[row2][col2];
        data[row2][col2] = tempData;
        
        repaint();
    }

    private boolean isImageReconstructed() {
    int maxNumber = data.length * data[0].length;
    
    for (int i = 0; i < maxNumber - 1; i++) {
        if (data[i / data[0].length][i % data[0].length] != i + 1) {
            return false;
        }
    }

    return true;
    }
    
    private void showSuccessMessage() {
        SuccessFenetre successFrame = new SuccessFenetre(moveCount);
        successFrame.setVisible(true);
    }
    
    public ArrayList<BufferedImage> getSubImages() {
        return subImages;
    }

    public void setSubImages(ArrayList<BufferedImage> subImages) {
        this.subImages = subImages;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int cellWidth = getWidth() / data[0].length;
            int cellHeight = getHeight() / data.length;

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    int x = j * cellWidth;
                    int y = i * cellHeight;

                    int subImageNumber = data[i][j];
                    BufferedImage subImage = subImages.get(subImageNumber - 1);

                    g.drawImage(subImage, x, y, cellWidth, cellHeight, this);
                }
            }
        }
    }

    public void rotate90Degrees() {
        int[][] rotatedData = new int[data[0].length][data.length];
        ArrayList<BufferedImage> rotatedSubImages = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                rotatedData[j][data.length - 1 - i] = data[i][j];
                rotatedSubImages.add(subImages.get(data[i][j] - 1));
            }
        }

        data = rotatedData;
        subImages = rotatedSubImages;
        repaint();
    }

    public void rotateMinus90Degrees() {
        int[][] rotatedData = new int[data[0].length][data.length];
        ArrayList<BufferedImage> rotatedSubImages = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                rotatedData[data[0].length - 1 - j][i] = data[i][j];
                rotatedSubImages.add(subImages.get(data[i][j] - 1));
            }
        }

        data = rotatedData;
        subImages = rotatedSubImages;
        repaint();
    }

    private ArrayList<BufferedImage> divideImage() {
        int subImageWidth = image.getWidth() / data[0].length;
        int subImageHeight = image.getHeight() / data.length;

        ArrayList<BufferedImage> dividedImages = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                int x = j * subImageWidth;
                int y = i * subImageHeight;
                BufferedImage subImage = image.getSubimage(x, y, subImageWidth, subImageHeight);
                dividedImages.add(subImage);
            }
        }

        return dividedImages;
    }
}

