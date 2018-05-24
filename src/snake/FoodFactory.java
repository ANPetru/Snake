/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.ArrayList;

/**
 *
 * @author alux9127477l
 */
public class FoodFactory {

    public static Food getFood(String s, ArrayList<Node> obsList, Snake... snakes) {
        if (s.equalsIgnoreCase("NormalFood")) {
            return new NormalFood(obsList, snakes);
        } else if (s.equalsIgnoreCase("SpecialFood")) {
            return new SpecialFood(obsList, snakes);
        }
        return null;
    }
}
