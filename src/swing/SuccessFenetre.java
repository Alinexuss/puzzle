/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nikami
 */
public class SuccessFenetre extends JFrame {
    
    private int moveCount;
    
    public SuccessFenetre(int moveCount) {
        this.moveCount = moveCount;
        
        setTitle("Succès!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel successLabel = new JLabel("Félicitations! Vous avez reconstitué l'image en " + moveCount + " coups.");
        add(successLabel);

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
            }
        });
        add(closeButton);
        
        JButton restartButton = new JButton("Recommencer");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        add(restartButton);

        pack();
        setLocationRelativeTo(null);
    }
    
    private void restartGame() {
        
        dispose(); 
    }
}
