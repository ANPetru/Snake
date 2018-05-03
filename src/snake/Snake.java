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
    private boolean isAlive;

    public Snake(DirectionType dir) {
        if (dir == DirectionType.RIGHT) {
            direction = DirectionType.RIGHT;
            initListNodes(4);
        } else {
            direction=DirectionType.LEFT;
            initListNodes(2);
        }
        eatCounter = 0;
        isAlive=true;
    }
    
    public boolean getIsAlive(){
        return isAlive;
    }
    
    public void die(){
        isAlive=false;
        for(Node n:listNodes){
            n.setColor(Color.BLACK);
        }
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
            util.drawSquare(g, n, n.getColor(), squareWidth, squareHeight);
        }
    }

    private void initListNodes(int n) {
        listNodes = new ArrayList<Node>();
        listNodes.add(new Node(Board.NUM_ROW / 2, Board.NUM_COL / n, util.getRandomColor()));
        listNodes.add(new Node(Board.NUM_ROW / 2 - 1, Board.NUM_COL / n, util.getRandomColor()));
        listNodes.add(new Node(Board.NUM_ROW / 2 - 2, Board.NUM_COL / n, util.getRandomColor()));

    }

    public void move() {

        Node newNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol(), util.getRandomColor());
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
        eatCounter += food.getGrowth();

    }

    public boolean checkWithItself(int row, int col) {
        for (Node n : listNodes) {
            if (col == n.getCol() && row == n.getRow()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkWithOtherSnake(Snake otherSnake,int row,int col){
        for (Node n : otherSnake.getListNodes()) {
            if (col == n.getCol() && row == n.getRow()) {
                return true;
            }
        }
        return false;
    }

}
