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
public class NormalFood extends Food {

    public NormalFood(ArrayList<Node> obsList, Snake[] snakes) {
        super(obsList, snakes);
    }

    public void draw(Graphics g, int squareWidth, int squareheight) {
            util.drawSquare(g, super.getPosition(), super.getPosition().getColor(), squareWidth, squareheight);

      
    }

}
