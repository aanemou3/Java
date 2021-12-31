/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ergasia_javafx;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    static ArrayList<Team> teams;
    static ArrayList<Game> games;
    static Stage primaryStage;
    static Scene mainScene;
    static TeamScene ts;
    static GameScene gs;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        Button btn = new Button();
        btn.setText("Teams");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                ts.tableSync();
                primaryStage.setScene(ts.scene);
		primaryStage.setTitle("Teams");
		primaryStage.show();
            }
        });
        
        Button btn2 = new Button();
        btn2.setText("Games");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gs.tableSync();
                primaryStage.setScene(gs.scene);
		primaryStage.setTitle("Games");
		primaryStage.show();      
            }
        });
        
        FlowPane root = new FlowPane();
        root.setVgap(10);
        root.setHgap(10);
        
        //StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        
        mainScene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("League Manager");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        teams = new ArrayList<>();
        games = new ArrayList<>();
        
        Team t1 = new Team("OSFP");
        teams.add(t1);
        Team t2 = new Team("PAO");
        teams.add(t2);
        Team t3 = new Team("AEK");
        teams.add(t3);
        
        Game g = new Game(t1.name, t2.name, 0, 2);
        games.add(g);
        Game g2 = new Game(t1.name, t3.name, 1, 0);
        games.add(g2);
        Game g3 = new Game(t2.name, t3.name, 1, 1);
        games.add(g3);
        
        ts = new TeamScene();
        gs = new GameScene();
        
        launch(args);
    }
    
}
