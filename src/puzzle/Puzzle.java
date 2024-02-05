/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package puzzle;

import javax.swing.SwingUtilities;
import swing.MainFenetre;

/**
 *
 * @author Nikami
 */
public class Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            MainFenetre mainFrame = new MainFenetre();
            mainFrame.setVisible(true);
        });
    }
    
}
