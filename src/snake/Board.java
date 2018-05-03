/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alux9127477l
 */
public class Board extends JPanel implements ActionListener {

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (canMove(DirectionType.UP, snakeP1)) {
                        snakeP1.changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (canMove(DirectionType.DOWN, snakeP1)) {
                        snakeP1.changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (canMove(DirectionType.LEFT, snakeP1)) {
                        snakeP1.changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (canMove(DirectionType.RIGHT, snakeP1)) {
                        snakeP1.changeDirection(DirectionType.RIGHT);
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    class MyKeyAdapter2 extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (canMove(DirectionType.UP, snakeP2)) {
                        snakeP2.changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (canMove(DirectionType.DOWN, snakeP2)) {
                        snakeP2.changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (canMove(DirectionType.LEFT, snakeP2)) {
                        snakeP2.changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (canMove(DirectionType.RIGHT, snakeP2)) {
                        snakeP2.changeDirection(DirectionType.RIGHT);
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public static final int NUM_ROW = 30;
    public static final int NUM_COL = 30;

    private int deltaTime;
    private Food food;
    private SpecialFood specialFood;
    private Snake snakeP1;
    private Snake snakeP2;

    private Timer timer;
    private KeyAdapter keyAdapterP1;
    private KeyAdapter keyAdapterP2;
    private ScoreBoard scoreBoard;
    private Timer specialFoodTimer;
    private ArrayList<Node> obstacleListNodes;
    private RecordsDialog recordsDialog;
    private JFrame parentFrame;

    public Board() {
        super();
        initVariables();
    }

    private void initVariables() {
        snakeP1 = new Snake(DirectionType.RIGHT);
        snakeP2 = new Snake(DirectionType.LEFT);

        obstacleListNodes = new ArrayList<Node>();

        createObstacles(4);
        deltaTime = 300;
        initFood();
        timer = new Timer(deltaTime, this);
        keyAdapterP1 = new MyKeyAdapter();
        keyAdapterP2 = new MyKeyAdapter2();

        setFocusable(true);
        scoreBoard = null;
        food = new Food(obstacleListNodes, snakeP1, snakeP2);
        recordsDialog = null;
        parentFrame = null;
    }

    public void initGame() {
        initVariables();
        setFocusable(true);
        timer.start();
        addKeyListener(keyAdapterP1);
        addKeyListener(keyAdapterP2);

    }

    private void initFood() {

        if (scoreBoard != null) {
            if (scoreBoard.getScore() > 0 && scoreBoard.getScore() % 5 == 0) {
                food = null;
                specialFood = new SpecialFood(obstacleListNodes, snakeP1, snakeP2);
                specialFoodTimer = new Timer(specialFood.getVisibleTime() * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        specialFood = null;
                        food = new Food(obstacleListNodes, snakeP1, snakeP2);
                        specialFoodTimer.stop();
                    }
                });
                specialFoodTimer.start();
            } else {
                if (specialFoodTimer != null && specialFoodTimer.isRunning()) {
                    specialFoodTimer.stop();
                }
                food = new Food(obstacleListNodes, snakeP1, snakeP2);
                specialFood = null;
            }
        }

    }

    private void createObstacles(int nObstacles) {

        int counter = 0;
        boolean hit;
        while (counter < nObstacles) {
            hit = false;
            int row = (int) (Math.random() * NUM_ROW);
            int col = (int) (Math.random() * NUM_COL);
            Node obsNode = new Node(row, col, Color.GRAY);

            hit = util.checkNodeWithNodeList(obsNode, snakeP1.getListNodes());
            if (!hit) {
                hit = util.checkNodeWithNodeList(obsNode, snakeP2.getListNodes());
            }
            if (!hit) {
                hit = util.checkNodeWithNodeList(obsNode, obstacleListNodes);
            }

            if (!hit) {
                obstacleListNodes.add(obsNode);
                counter++;
            }
        }
    }

    private void drawObstacles(Graphics g) {
        for (Node n : obstacleListNodes) {
            util.drawSquare(g, n, Color.gray, getSquareWidth(), getSquareHeight());
        }
    }

    //Main Loop
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!checkCollision(snakeP1, snakeP2) && snakeP1.getIsAlive()) {
            snakeP1.move();
        } else {
            snakeP1.die();
        }
        if (!checkCollision(snakeP2, snakeP1) && snakeP2.getIsAlive()) {
            snakeP2.move();

        } else {
            snakeP2.die();
        }

        if (!snakeP1.getIsAlive() && !snakeP2.getIsAlive()) {
            processGameOver();
        }

        if (checkFood(snakeP1)) {
            if (food != null) {
                snakeP1.eat(food);
            } else {
                snakeP1.eat(specialFood);
            }
            createObstacles(1);
            initFood();
            scoreBoard.setScore(scoreBoard.getScore() + 1);
        }
        if (checkFood(snakeP2)) {
            if (food != null) {
                snakeP2.eat(food);
            } else {
                snakeP2.eat(specialFood);
            }
            createObstacles(1);
            initFood();
            scoreBoard.setScore(scoreBoard.getScore() + 1);
        }
        repaint();
    }

    private boolean canMove(DirectionType newDirection, Snake snake) {
        DirectionType snakeDirection = snake.getDirection();
        switch (newDirection) {
            case UP:
            case DOWN:
                if (snakeDirection == DirectionType.UP || snakeDirection == DirectionType.DOWN) {
                    return false;
                } else {
                    return true;
                }
            case RIGHT:
            case LEFT:
                if (snakeDirection == DirectionType.LEFT || snakeDirection == DirectionType.RIGHT) {
                    return false;
                } else {
                    return true;
                }

        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        snakeP1.draw(g, getSquareWidth(), getSquareHeight());
        snakeP2.draw(g, getSquareWidth(), getSquareHeight());

        drawObstacles(g);

        if (food != null) {
            food.draw(g, getSquareWidth(), getSquareHeight());
        }

        if (specialFood != null) {
            specialFood.draw(g, getSquareWidth(), getSquareHeight());
        }
    }

    private int getSquareWidth() {
        return getWidth() / NUM_COL;
    }

    private int getSquareHeight() {
        return getHeight() / NUM_ROW;
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.gray);
        g.drawRect(0, 0, getSquareWidth() * NUM_COL, getSquareHeight() * NUM_ROW);

    }

    private boolean checkFood(Snake snake) {
        Node snakePos = snake.getListNodes().get(0);
        Node foodPos = null;
        if (food != null) {
            foodPos = food.getPosition();

        } else {
            foodPos = specialFood.getPosition();
        }
        if (snakePos.getCol() == foodPos.getCol() && snakePos.getRow() == foodPos.getRow()) {
            return true;
        }
        return false;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    private boolean checkCollision(Snake snake, Snake otherSnake) {
        int nextPosRow = snake.getListNodes().get(0).getRow();
        int nextPosCol = snake.getListNodes().get(0).getCol();
        switch (snake.getDirection()) {
            case UP:
                nextPosRow--;
                if (nextPosRow < 0) {
                    return true;
                }
                break;
            case DOWN:
                nextPosRow++;
                if (nextPosRow > NUM_ROW - 1) {
                    return true;
                }
                break;
            case LEFT:
                nextPosCol--;
                if (nextPosCol < 0) {
                    return true;
                }
                break;
            case RIGHT:
                nextPosCol++;
                if (nextPosCol > NUM_COL - 1) {
                    return true;
                }
                break;

        }

        Node node = new Node(nextPosRow, nextPosCol, Color.GRAY);
        if (util.checkNodeWithNodeList(node, obstacleListNodes)) {
            return true;
        }

        if (snake.checkWithItself(nextPosRow, nextPosCol)) {
            return true;
        }
        return snake.checkWithOtherSnake(otherSnake, nextPosRow, nextPosCol);
    }

    private void processGameOver() {
        removeKeyListener(keyAdapterP1);
        removeKeyListener(keyAdapterP2);

        timer.stop();
        recordsDialog = new RecordsDialog(parentFrame, true, scoreBoard.getScore());
        recordsDialog.setVisible(true);
    }

    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

}
