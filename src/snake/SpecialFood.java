/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alux9127477l
 */
public class SpecialFood extends Food {

    private int visibleTime;

    public SpecialFood( ArrayList<Node> obsList, Snake... snakes) {
        super(obsList,snakes);
        visibleTime = 10;
        super.setGrowth(3);
        
    }

    public int getVisibleTime() {
        return visibleTime;
    }

    public void draw(Graphics g, int squareWidth, int squareheight) {
        util.drawSquare(g, super.getPosition(), Color.red, squareWidth, squareheight);
    }

}
