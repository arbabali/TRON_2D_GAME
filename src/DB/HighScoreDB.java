/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import DB.HighScore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Arbab Ali
 */

public class HighScoreDB {
    
    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    PreparedStatement updateStatement;
    Connection connection;
    public static int size=0;
    /**
     * creates three prepared statmente based on score about update insert and delete 
     * @param maxScores
     * @throws SQLException 
     */
    public HighScoreDB(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        String dbURL = "jdbc:derby://localhost:1527/HIGHSCORES";
        connection = DriverManager.getConnection(dbURL);
        String insertQuery = "INSERT INTO HIGHSCORES (TIMESTAMP, NAME, SCORE) VALUES (?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
        String updateQuery="UPDATE HIGHSCORES  set SCORE=? where NAME=?";
        updateStatement = connection.prepareStatement(updateQuery);
    }
    /**
     * gets HighScore by executing sql statement and return value in ArrayList also stores the size of it in a vairable size 
     * @return
     * @throws SQLException 
     */
    public ArrayList<HighScore> getHighScoreDB() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            highScores.add(new HighScore(name, score));
        }
        sortHighScoreDB(highScores);
        size=highScores.size();
        return highScores;
    }
    /**
     * gets the name of each player in table
     * @return
     * @throws SQLException 
     */
    public ArrayList<String> getNamesDB() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<String> names = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            
            names.add(name);
        }
        //sortHighScoreDB(names);
       // size=highScores.size();
        return names;
    }
    /**
     * put a new entry in database and also checks if its maximum then put it on top.
     * @param name
     * @param score
     * @throws SQLException 
     */
    public void putHighScore(String name, int score) throws SQLException {
        ArrayList<HighScore> highScores = getHighScoreDB();
        ArrayList<String> names=getNamesDB();
        if(names.contains(name)){
            System.out.println("PLAYER EXISTS ALREADY!");
            
        }
            
        if (highScores.size() < maxScores) {
            insertScore(name, score);
        } else {
            int leastScore = highScores.get(highScores.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(name, score);
            }
        }
    }

    /**
     * Sort the high scores in descending order.
     * @param highScores 
     */
    private void sortHighScoreDB(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }
    
    /**
     * inserts Score by executing insertStatment
     * @param name
     * @param score
     * @throws SQLException 
     */
    private void insertScore(String name, int score) throws SQLException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        insertStatement.setTimestamp(1, ts);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, score);
        insertStatement.executeUpdate();
    }

    /**
     * Deletes all the highscores with score.
     *
     * @param score
     */
    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
    
}
