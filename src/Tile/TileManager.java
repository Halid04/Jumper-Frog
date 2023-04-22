package Tile;

import JumperFrogPackage1.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCOl][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/Mappe/mappa1.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/grassWater.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/darkGrass.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/darkGrassWater.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/water.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/trunk.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/woodenBarrier.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/woodenBarrierDark.png"));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Washington abbiamo un proble!");
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col<gamePanel.maxWorldCOl && row<gamePanel.maxWorldRow){
                String line = bufferedReader.readLine();

                while (col < gamePanel.maxWorldCOl){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCOl){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("Washington abbiamo un proble!");
        }
    }

    public void draw(Graphics2D graphics2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol<gamePanel.maxWorldCOl && worldRow<gamePanel.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if((worldX + gamePanel.tileSize) > (gamePanel.player.worldX - gamePanel.player.screenX) &&
               (worldX - gamePanel.tileSize) < (gamePanel.player.worldX + gamePanel.player.screenX) &&
               (worldY + gamePanel.tileSize) > (gamePanel.player.worldY - gamePanel.player.screenY) &&
               (worldY - gamePanel.tileSize) < (gamePanel.player.worldY + gamePanel.player.screenY)){
                graphics2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if(worldCol == gamePanel.maxWorldCOl){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
