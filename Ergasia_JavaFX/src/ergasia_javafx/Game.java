/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ergasia_javafx;

public class Game {
    String home, away;
    int goals1, goals2;

    public Game(String home, String away, int goals1, int goals2) {
        this.home = home;
        this.away = away;
        this.goals1 = goals1;
        this.goals2 = goals2;
        
        update_points();
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public int getGoals1() {
        return goals1;
    }

    public int getGoals2() {
        return goals2;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public void setGoals1(int goals1) {
        this.goals1 = goals1;
    }

    public void setGoals2(int goals2) {
        this.goals2 = goals2;
    }

    

    
    private void update_points(){
        if(goals1 > goals2){
            //home wins
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == home){
                    Main.teams.get(i).points += 3;
                    break;
                }
            }
        }
        else if(goals2 > goals1){
            //away wins
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == away){
                    Main.teams.get(i).points += 3;
                    break;
                }
            }
        }else{
            //draw
            int counter = 0;
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == home || Main.teams.get(i).name == away){
                    Main.teams.get(i).points += 1;
                    counter++;
                    if(counter == 2){
                        //both team points updated, no need to search more
                        break;
                    }
                }
            }
        }
    }
    
    public void cancel_points(){
        if(goals1 > goals2){
            //home had won
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == home){
                    Main.teams.get(i).points -= 3;
                    break;
                }
            }
        }
        else if(goals2 > goals1){
            //away had wons
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == away){
                    Main.teams.get(i).points -= 3;
                    break;
                }
            }
        }else{
            //draw
            int counter = 0;
            for(int i=0; i< Main.teams.size(); i++){
                if(Main.teams.get(i).name == home || Main.teams.get(i).name == away){
                    Main.teams.get(i).points -= 1;
                    counter++;
                    if(counter == 2){
                        //both team points updated, no need to search more
                        break;
                    }
                }
            }
        }
    
    }
    
}
