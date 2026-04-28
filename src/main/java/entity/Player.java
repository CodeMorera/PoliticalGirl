package main.java.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.java.main.GamePanel;
import main.java.main.KeyHandler;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    String direction;

    int spriteCounter = 0;
    int spriteNum = 1;

    BufferedImage standingFront, standingBack, standingLeft, standingRight;
    BufferedImage down1, down2, down3;
    BufferedImage up1, up2, up3;
    BufferedImage left1, left2, left3;
    BufferedImage right1, right2, right3;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update(){

        boolean moving = false;

        if(keyH.upPressed == true){
            y -= speed;
            direction = "up";
            moving = true;
        }

        else if(keyH.downPressed == true){
            y += speed;
            direction = "down";
            moving = true;
        }

        else if(keyH.leftPressed == true){
            x -= speed;
            direction = "left";
            moving = true;
        }

        else if(keyH.rightPressed == true){
            x += speed;
            direction = "right";
            moving = true;
        }
        if(moving){
            if(spriteNum == 0){
                spriteNum = 1;
            }
            spriteCounter++;

            if(spriteCounter > 6){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 1;
                }
                System.out.println("spriteNum = " + spriteNum);
                spriteCounter = 0;
            }
        }
        else{
            spriteNum = 0;
            spriteCounter =0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }
                else if(spriteNum ==2){
                    image = up2;
                }
                else if(spriteNum ==3){
                    image = up3;
                }
                else{
                    image = standingBack;
                }
                break;
            case "down":
                if(spriteNum ==1){
                    image = down1;
                }
                else if(spriteNum ==2){
                    image = down2;
                }
                else if(spriteNum ==3){
                    image = down3;
                }
                else{
                    image = standingFront;
                }
                break;
            case "left":
                if(spriteNum ==1){
                    image = left1;
                }
                else if(spriteNum ==2){
                    image = left2;
                }
                else if(spriteNum ==3){
                    image = left3;
                }
                else{
                    image = standingLeft;
                }
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
                }
                else if(spriteNum ==2){
                    image = right2;//temporay test
                }
                else if(spriteNum ==3){
                    image = right3;
                }
                else{
                    image = standingRight;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void getPlayerImage() {
        try {
            BufferedImage sheet = ImageIO.read(new File("sprites/Chloe4.png"));
            System.out.println("Sheet loaded: " + (sheet != null));
            System.out.println("Sheet dimensions: " + sheet.getWidth() + "x" + sheet.getHeight());

            // Right row
            right1 = sheet.getSubimage(135, 45, 150, 233);
            right2 = sheet.getSubimage(413, 45, 146, 233);
            right3 = sheet.getSubimage(685, 45, 147, 233);
            standingRight = sheet.getSubimage(961, 45, 147, 233);

            // Left row
            left1 = sheet.getSubimage(135, 336, 150, 231);
            left2 = sheet.getSubimage(413, 336, 146, 231);
            left3 = sheet.getSubimage(685, 336, 147, 231);
            standingLeft = sheet.getSubimage(961, 336, 147, 231);

            // Down row
            down1 = sheet.getSubimage(135, 632, 150, 227);
            down2 = sheet.getSubimage(413, 632, 146, 227);
            down3 = sheet.getSubimage(685, 632, 147, 227);
            standingFront = sheet.getSubimage(961, 632, 147, 227);

            // Up row
            up1 = sheet.getSubimage(135, 911, 150, 227);
            up2 = sheet.getSubimage(413, 911, 146, 227);
            up3 = sheet.getSubimage(685, 911, 147, 227);
            standingBack = sheet.getSubimage(961, 911, 147, 227);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
