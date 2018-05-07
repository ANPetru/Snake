/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.event.KeyAdapter;

/**
 *
 * @author alux9127477l
 */
public class Player {

    private ScoreBoard scoreBoard;
    private Snake snake;
    private KeyAdapter keyAdapter;

    public Player(Snake snake, ScoreBoard scoreBoard,KeyAdapter keyAdapter) {
        this.scoreBoard = scoreBoard;
        this.snake = snake;
        this.keyAdapter=keyAdapter;
    }
    
    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }
    
    public Snake getSnake(){
        return snake;
    }
    
    public KeyAdapter getKeyAdapter(){
        return keyAdapter;
    }
}
