/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author alux9127477l
 */
public class ConfigSingleton implements Serializable {

    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLS = 20;
    private static final String FILE_NAME = "config.txt";

    private static ConfigSingleton instance = null;

    private int numPlayers;
    private int numRows;
    private int numCols;

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private ConfigSingleton() {
        numPlayers = 0;
        numRows = DEFAULT_ROWS;
        numCols = DEFAULT_COLS;
        try {
            readFromFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }
        return instance;
    }

    public void writeOnFile() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(instance);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void readFromFile() throws ClassNotFoundException, IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            instance = (ConfigSingleton) in.readObject();
        } catch (FileNotFoundException e) {

        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

}
