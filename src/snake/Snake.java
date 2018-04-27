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
public class Snake {

    private ArrayList<Node> listNodes;
    private DirectionType direction;
    private int eatCounter;

    public Snake() {
        initListNodes();
        direction = DirectionType.RIGHT;
        eatCounter = 0;
    }

    public ArrayList<Node> getListNodes() {
        return listNodes;
    }

    public void changeDirection(DirectionType direction) {
        this.direction = direction;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node n : listNodes) {
            util.drawSquare(g, n, Color.black, squareWidth, squareHeight);
        }
    }

    private void initListNodes() {
        listNodes = new ArrayList<Node>();
        listNodes.add(new Node(Board.NUM_ROW / 2, Board.NUM_COL / 2));
        listNodes.add(new Node(Board.NUM_ROW / 2 - 1, Board.NUM_COL / 2));
        listNodes.add(new Node(Board.NUM_ROW / 2 - 2, Board.NUM_COL / 2));

    }

    public void move() {
        Node newNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol());
        switch (direction) {
            case UP:
                newNode.setRow(newNode.getRow() - 1);
                break;
            case DOWN:
                newNode.setRow(newNode.getRow() + 1);
                break;
            case LEFT:
                newNode.setCol(newNode.getCol() - 1);
                break;
            case RIGHT:
                newNode.setCol(newNode.getCol() + 1);
                break;

        }
        listNodes.add(0, newNode);
        if (eatCounter > 0) {
            eatCounter--;
        } else {
            listNodes.remove(listNodes.size() - 1);

        }
    }

    public void eat(Food food) {
        eatCounter += 1;
    }

    public boolean checkWithItself(int row, int col) {
        for (Node n : listNodes) {
            if (col == n.getCol() && row == n.getRow()) {
                return true;
            }
        }
        return false;
    }
}
