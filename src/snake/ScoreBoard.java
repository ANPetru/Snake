/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JLabel;

/**
 *
 * @author alux9127477l
 */
public class ScoreBoard extends JLabel{
    private int score;
    
    public ScoreBoard(){
        super();
        resetScore();
    }
    
    private void setTextScore(){
        setText("Score:" + score);
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int score){
        this.score = score;
        setTextScore();
    }
    
    public void resetScore(){
        score=0;
        setTextScore();
    }
}
