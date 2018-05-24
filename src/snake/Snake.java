/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alux9127477l
 */
public class Snake implements Serializable{

    public static final Color[] SNAKE_COLORS = {Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE};
    public static final Node[] SNAKE_INIT_NODE = {new Node(ConfigSingleton.getInstance().getNumRows() / 4, ConfigSingleton.getInstance().getNumCols() / 2),
        new Node(ConfigSingleton.getInstance().getNumRows() - ConfigSingleton.getInstance().getNumRows() / 4, ConfigSingleton.getInstance().getNumCols() / 2),
        new Node(ConfigSingleton.getInstance().getNumRows() / 2, ConfigSingleton.getInstance().getNumCols() / 4),
        new Node(ConfigSingleton.getInstance().getNumRows() / 2, ConfigSingleton.getInstance().getNumCols() - ConfigSingleton.getInstance().getNumCols() / 4)};
    public static final DirectionType[] SNAKE_INIT_DIRECTION = {DirectionType.DOWN, DirectionType.UP, DirectionType.RIGHT, DirectionType.LEFT};

    private ArrayList<Node> listNodes;
    private DirectionType direction;
    private int eatCounter;
    private boolean isAlive;
    private Color color;
    private boolean isTurning;
    private int id;

    public Snake(Color color, Node node, DirectionType direction, int id) {
        this.direction=direction;
        this.color = color;
        isTurning = false;
        initListNodes(node);
        eatCounter = 0;
        isAlive = true;
        this.id=id;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        for (Node n : listNodes) {
            n.setColor(Color.BLACK);
        }
    }

    public boolean getIsTurning() {
        return isTurning;
    }

    public void setIsTurning(boolean isTurning) {
        this.isTurning = isTurning;
    }

    public ArrayList<Node> getListNodes() {
        return listNodes;
    }

    public void changeDirection(DirectionType direction) {
        this.direction = direction;
        isTurning = true;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node n : listNodes) {
            util.drawSquare(g, n, n.getColor(), squareWidth, squareHeight);
        }
    }

    private void initListNodes(Node n) {
        listNodes = new ArrayList<Node>();
        listNodes.add(n);
        switch (direction) {
            case LEFT:
                listNodes.add(new Node(n.getRow() - 1, n.getCol()));
                break;
            case RIGHT:
                listNodes.add(new Node(n.getRow() + 1, n.getCol()));
                break;
            case UP:
                listNodes.add(new Node(n.getRow(), n.getCol() - 1));
                break;
            case DOWN:
                listNodes.add(new Node(n.getRow(), n.getCol() + 1));
                break;

        }

    }

    public void move() {

        Node newNode = new Node(listNodes.get(0).getRow(), listNodes.get(0).getCol(), color);
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
        changeColor();
    }

    public boolean checkWithItself(int row, int col) {
        for (Node n : listNodes) {
            if (col == n.getCol() && row == n.getRow()) {
                return true;
            }
        }
        return false;
    }
    
    public int getID(){
        return id;
    }

    public boolean checkWithOtherSnake(Snake[] otherSnakes, int row, int col) {
        for (Snake otherSnake : otherSnakes) {
            for (Node n : otherSnake.getListNodes()) {
                if ( id != otherSnake.getID() &&col == n.getCol() && row == n.getRow()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changeColor() {
        int colorNumber = 0;
        int counter = 255 / listNodes.size();
        for (Node n : listNodes) {
            colorNumber += counter;
            n.setColor(new Color(colorNumber, colorNumber, colorNumber));
        }
    }
}
