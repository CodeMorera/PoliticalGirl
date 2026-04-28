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

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //Will help keep the program running till we stop it.

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        // Set the size of this class(jPanel)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // All drawing from this component will be down in an offscreen 
        this.setDoubleBuffered(true);//JPanel has as default.
        //^ enabling this can improve the games' rendering performance.
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        //passing GamePanel class to this thred constructor
        gameThread = new Thread(this); 
        gameThread.start();
    }

    // @Override
    // public void run() { //gameThread calls this automatically.

    //     double drawInterval = 1000000000 / FPS; // draws on screen 60 X per sec
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    //     while (gameThread != null){
    //         update(); // UPDATE: update information on character position
            
    //         repaint();//Draw: draw the screen with update information
            
    //         try {
    //             double remaningTime = nextDrawTime - System.nanoTime();
    //             remaningTime = remaningTime/ 1000000;

    //             if(remaningTime < 0){
    //                 remaningTime = 0;
    //             }

    //             Thread.sleep((long)remaningTime);

    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // Delta / Accumulator Method
    public void run(){
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime(); //check current time

            delta += (currentTime - lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){//To update and draw at every draw interval
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            
        }
    }

    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }

        else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }

        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }

        else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    //Standard method to draw things in JPanel. Think pencil or painbrush.
    public void paintComponent(Graphics g){
        //super is parent class of this class.
        super.paintComponent(g);
        //provides more sophisticated control over geometry, coordinate transformation, etc
        Graphics2D q2 = (Graphics2D)g;

        q2.setColor(Color.CYAN);
        q2.fillRect(playerX, playerY, tileSize, tileSize);
        q2.dispose();// Manually free these graphic context resources
    }
}
