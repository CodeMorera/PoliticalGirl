package main;

import javax.swing.JFrame;

public class Main  {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        // Lets the window properly close when user clicks the close(x) button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // cannot resize window.
        window.setTitle("Political Girl");


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //Causes this window to be sized to fit the preferred size.
        window.pack();

        window.setLocationRelativeTo(null); // set it relative to the screen.
        window.setVisible(true);  // so we can see this window.

        gamePanel.startGameThread();
    }
}

