/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ergasia_javafx;


public class Team implements Comparable<Team>{
    String name;
    int points;

    public Team(String name) {
        this.name = name;
        points = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
    public int compareTo(Team t2){
        //compare for descending ordering of the list
        return t2.points - this.points;
    }
}
