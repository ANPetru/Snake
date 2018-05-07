/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
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
            if (players[0].getSnake().getIsTurning()) {
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (canMove(DirectionType.UP, players[0].getSnake())) {
                        players[0].getSnake().changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (canMove(DirectionType.DOWN, players[0].getSnake())) {
                        players[0].getSnake().changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (canMove(DirectionType.LEFT, players[0].getSnake())) {
                        players[0].getSnake().changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (canMove(DirectionType.RIGHT, players[0].getSnake())) {
                        players[0].getSnake().changeDirection(DirectionType.RIGHT);
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
            if (players[1].getSnake().getIsTurning()) {
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (canMove(DirectionType.UP, players[1].getSnake())) {
                        players[1].getSnake().changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (canMove(DirectionType.DOWN, players[1].getSnake())) {
                        players[1].getSnake().changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (canMove(DirectionType.LEFT, players[1].getSnake())) {
                        players[1].getSnake().changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (canMove(DirectionType.RIGHT, players[1].getSnake())) {
                        players[1].getSnake().changeDirection(DirectionType.RIGHT);
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    class MyKeyAdapter3 extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (players[2].getSnake().getIsTurning()) {
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_8:
                    if (canMove(DirectionType.UP, players[2].getSnake())) {
                        players[2].getSnake().changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_5:
                    if (canMove(DirectionType.DOWN, players[2].getSnake())) {
                        players[2].getSnake().changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_4:
                    if (canMove(DirectionType.LEFT, players[2].getSnake())) {
                        players[2].getSnake().changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_6:
                    if (canMove(DirectionType.RIGHT, players[2].getSnake())) {
                        players[2].getSnake().changeDirection(DirectionType.RIGHT);
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    class MyKeyAdapter4 extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (players[3].getSnake().getIsTurning()) {
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_I:
                    if (canMove(DirectionType.UP, players[3].getSnake())) {
                        players[3].getSnake().changeDirection(DirectionType.UP);
                    }
                    break;
                case KeyEvent.VK_K:
                    if (canMove(DirectionType.DOWN, players[3].getSnake())) {
                        players[3].getSnake().changeDirection(DirectionType.DOWN);
                    }
                    break;
                case KeyEvent.VK_J:
                    if (canMove(DirectionType.LEFT, players[3].getSnake())) {
                        players[3].getSnake().changeDirection(DirectionType.LEFT);
                    }
                    break;
                case KeyEvent.VK_L:
                    if (canMove(DirectionType.RIGHT, players[3].getSnake())) {
                        players[3].getSnake().changeDirection(DirectionType.RIGHT);
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
    private Timer timer;
    private Player[] players;
    private ScoreBoard[] scoreBoards;
    private Timer specialFoodTimer;
    private ArrayList<Node> obstacleListNodes;
    private RecordsDialog recordsDialog;
    private JFrame parentFrame;
    private PlayerSelection playerSelection;
    private MainGame game;
    private KeyAdapter[] keyAdapters;
    private Snake[] snakes;

    public Board() {
        super();
        players = null;
        scoreBoards = null;
        initKeyAdapters();
        initVariables();
    }

    public void setScoreBoard(ScoreBoard... scoreBoard) {
        this.scoreBoards = scoreBoard;
        setPLayers(scoreBoard.length);
    }

    public void setPlayerSelection(MainGame game) {
        playerSelection = new PlayerSelection(parentFrame, true, game);
        playerSelection.setVisible(true);
    }

    private void initKeyAdapters() {
        keyAdapters = new KeyAdapter[4];
        keyAdapters[0] = new MyKeyAdapter();
        keyAdapters[1] = new MyKeyAdapter2();
        keyAdapters[2] = new MyKeyAdapter3();
        keyAdapters[3] = new MyKeyAdapter4();

    }

    private void initVariables() {

        obstacleListNodes = new ArrayList<Node>();

        deltaTime = 100;
        initFood();
        timer = new Timer(deltaTime, this);
        setFocusable(true);
        recordsDialog = null;
        parentFrame = null;
    }

    public void initGame() {
        initVariables();
        setFocusable(true);
        timer.start();

    }

    private void initFood() {

        if (scoreBoards != null && snakes != null) {
            if (checkSpecialFood()) {
                food = null;
                specialFood = new SpecialFood(obstacleListNodes, snakes);
                specialFoodTimer = new Timer(specialFood.getVisibleTime() * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        specialFood = null;
                        food = new Food(obstacleListNodes, snakes);
                        specialFoodTimer.stop();
                    }
                });
                specialFoodTimer.start();
            } else {
                if (specialFoodTimer != null && specialFoodTimer.isRunning()) {
                    specialFoodTimer.stop();
                }
                food = new Food(obstacleListNodes, snakes);
                specialFood = null;
            }
        }

    }

    private boolean checkSpecialFood() {
        for (ScoreBoard s : scoreBoards) {
            if (s.getScore() > 0 && s.getScore() % 5 == 0) {
                return true;
            }
        }
        return false;
    }

    private void createObstacles(int nObstacles) {

        int counter = 0;
        boolean hit;
        while (counter < nObstacles) {
            hit = false;
            int row = (int) (Math.random() * NUM_ROW);
            int col = (int) (Math.random() * NUM_COL);
            Node obsNode = new Node(row, col, Color.GRAY);

            for (Snake s : snakes) {
                if (!hit) {
                    hit = util.checkNodeWithNodeList(obsNode, s.getListNodes());
                }
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
        if (players != null) {
            for (Player p : players) {
                checkPlayer(p);
            }
        }
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    private void checkPlayer(Player player) {
        player.getSnake().setIsTurning(false);
        if (checkDeath()) {
            processGameOver();
        }
        if (!checkCollision(player.getSnake(), snakes) && player.getSnake().getIsAlive()) {
            player.getSnake().move();
        } else {
            player.getSnake().die();
        }

        if (checkFood(player.getSnake())) {
            if (food != null) {
                player.getSnake().eat(food);
            } else {
                player.getSnake().eat(specialFood);
            }
            createObstacles(1);
            initFood();
            player.getScoreBoard().setScore(player.getScoreBoard().getScore() + 1);

        }

    }

    private boolean checkDeath() {
        for (Snake s : snakes) {
            if (s.getIsAlive()) {
                return false;
            }
        }
        return true;
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
        if (snakes != null) {
            for (Snake s : snakes) {
                s.draw(g, getSquareWidth(), getSquareHeight());
            }
        }

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

    private boolean checkCollision(Snake snake, Snake[] otherSnakes) {
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

        return snake.checkWithOtherSnake(otherSnakes, nextPosRow, nextPosCol);
    }

    private void processGameOver() {
        for (KeyAdapter k : keyAdapters) {
            removeKeyListener(k);
        }
        setScoreRecord();
        timer.stop();

    }

    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    private void setScoreRecord() {

        recordsDialog = new RecordsDialog(parentFrame, true, getGreatestScore().getScore(), "Player 1");
        recordsDialog.setVisible(true);

    }

    private ScoreBoard getGreatestScore() {
        ScoreBoard maxScore = scoreBoards[0];
        for (int i = 1; i < scoreBoards.length; i++) {
            if (scoreBoards[i].getScore() > maxScore.getScore()) {
                maxScore = scoreBoards[i];
            }
        }
        return maxScore;
    }

    public void setPLayers(int numPlayers) {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(new Snake(Snake.SNAKE_COLORS[i], Snake.SNAKE_INIT_NODE[i], Snake.SNAKE_INIT_DIRECTION[i], i), scoreBoards[i], keyAdapters[i]);
        }
        snakes = new Snake[players.length];
        for (int i = 0; i < snakes.length; i++) {
            snakes[i] = players[i].getSnake();
        }
        createObstacles(4);
        food = new Food(obstacleListNodes, snakes);
        for (int i = 0; i < players.length; i++) {
            addKeyListener(keyAdapters[i]);
        }
    }
}
