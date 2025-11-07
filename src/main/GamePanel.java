package main;

import javax.swing.JPanel;//swing is not thread safe
import java.awt.*;

//Class inherites JPanel class
public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; // 16x16 tiles
    final int scale = 3; //make it look 48X48 on our modern screens.

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px

    //Will help keep the program running till we stop it.
    Thread gameThread;

    public GamePanel(){
        // Set the size of this class(jPanel)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // All drawing from this component will be down in an offscreen 
        // Painting buffer.
        this.setDoubleBuffered(true);//JPanel has as default.
        //^ enabling this can improve the games' rendering performance.
    }

    public void startGameThread(){
        //passing GamePanel class to this thred constructor
        gameThread = new Thread(this); 
        gameThread.start();
    }

    @Override
    public void run() { //gameThread calls this automatically.
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
