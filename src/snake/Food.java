/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author alux9127477l
 */
public class Food {

    private Node position;
    private Snake[] snake;
    private int growth;

    public Food(ArrayList<Node> obsList, Snake... snake) {
        this.snake = snake;
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
            int randomRow = (int) (Math.random() * Board.NUM_ROW);
            int randomCol = (int) (Math.random() * Board.NUM_COL);
            node = new Node(randomRow, randomCol, Color.YELLOW);
            for (Snake s : snake) {
                ArrayList<Node> listNodes = s.getListNodes();
                hit = util.checkNodeWithNodeList(node, listNodes);
                if (!hit) {
                    hit = util.checkNodeWithNodeList(node, obsList);

                }
            }

        }
        position = node;
    }

    public void draw(Graphics g, int squareWidth, int squareheight) {
        if (position != null) {
            util.drawSquare(g, position, position.getColor(), squareWidth, squareheight);

        }
    }

    public Node getPosition() {
        return position;
    }
}
