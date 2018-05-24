package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alux9127477l
 */
public abstract class Food {

    private Node position;
    private Snake[] snakes;
    private int growth;

    public Food(ArrayList<Node> obsList, Snake[] snakes) {
        this.snakes = snakes;
        growth = 1;
        generatePosition(obsList);
    }

    public void setGrowth(int g) {
        growth = g;
    }

    public int getGrowth() {
        return growth;
    }

    private void generatePosition(ArrayList<Node> obsList) {
        boolean hit = true;
        Node node = null;
        while (hit) {
            hit = true;
            int randomRow = (int) (Math.random() * ConfigSingleton.getInstance().getNumRows());
            int randomCol = (int) (Math.random() * ConfigSingleton.getInstance().getNumCols());
            node = new Node(randomRow, randomCol, Color.YELLOW);
            for (Snake s : snakes) {
                ArrayList<Node> listNodes = s.getListNodes();
                hit = util.checkNodeWithNodeList(node, listNodes);
                if (!hit) {
                    hit = util.checkNodeWithNodeList(node, obsList);

                }
            }

        }
        position = node;
    }

    public Node getPosition() {
        return position;
    }

    public abstract void draw(Graphics g, int squareWidth, int squareheight);
}
