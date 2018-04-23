/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JPanel;

/**
 *
 * @author alux9127477l
 */
public class Board extends JPanel{
    
    private int deltaTime;
    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    
    public Board(){
        super();
    }
}
