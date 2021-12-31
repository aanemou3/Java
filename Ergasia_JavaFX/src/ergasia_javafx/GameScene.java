/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ergasia_javafx;

import static ergasia_javafx.Main.mainScene;
import java.util.Collections;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public class GameScene {
    Scene scene;
    TextField team1Field;
    TextField team2Field;
    TextField goals1Field;
    TextField goals2Field;
    TableView<Game> gameTableView;
    ComboBox home, away;
    
    public GameScene(){
    
    
        GridPane rootGridPane = new GridPane(); 
        
        FlowPane buttonFlowPane= new FlowPane();

        Button newGameBtn= new Button("New Game");
        newGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name1 = (String) home.getValue();
                String name2 = (String) away.getValue();
                int goals1 = Integer.parseInt(goals1Field.getText());
                int goals2 = Integer.parseInt(goals2Field.getText());
                Game t = new Game(name1, name2, goals1, goals2);
                
                Main.games.add(t);
                
                tableSync();
                
            }
        });
        
        Button deleteGameBtn = new Button("Delete Game");
        deleteGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int i = gameTableView.getSelectionModel().getSelectedIndex();
                
                Game g = Main.games.get(i);
                
                g.cancel_points();
                
                Main.games.remove(i);
                
                tableSync();
            }
        });
        
        Button backBtn = new Button("Back");
        
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.primaryStage.setScene(Main.mainScene);
            }
        });
        
        buttonFlowPane.setHgap(10);
        buttonFlowPane.getChildren().add(newGameBtn);
        buttonFlowPane.getChildren().add(deleteGameBtn);
        buttonFlowPane.getChildren().add(backBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);

        GridPane inputFieldsPane = new GridPane();
        Label label1 = new Label("Home team: ");
        Label label2 = new Label("Away team: ");
        Label label3 = new Label("Home goals: ");
        Label label4 = new Label("Away goals: ");
        
        team1Field = new TextField();
        team2Field = new TextField();
        home = new ComboBox();
        away = new ComboBox();
        
        for(int i=0; i < Main.teams.size(); i++){
            String team_name = Main.teams.get(i).name;
            home.getItems().add(team_name);
            away.getItems().add(team_name);
        }
        
        goals1Field = new TextField();
        goals2Field = new TextField();
        
        goals1Field.setText("0");
        goals2Field.setText("0");
        
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(label1, 0, 0);
        inputFieldsPane.add(home, 1, 0);
        inputFieldsPane.add(label2, 0, 1);
        inputFieldsPane.add(away, 1,1);
        inputFieldsPane.add(label3, 0, 2);
        inputFieldsPane.add(goals1Field, 1, 2);
        inputFieldsPane.add(label4, 0, 3);
        inputFieldsPane.add(goals2Field, 1, 3);
        
        gameTableView = new TableView<>();

        TableColumn<Game, String> nameColumn = new TableColumn<>("Home");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("home"));
	gameTableView.getColumns().add(nameColumn);
	    
        TableColumn<Game, String> name2Column = new TableColumn<>("Away");
        name2Column.setCellValueFactory(new PropertyValueFactory<>("away"));
	gameTableView.getColumns().add(name2Column);
	
        TableColumn<Game, String> goals1Column = new TableColumn<>("HGoals");
        goals1Column.setCellValueFactory(new PropertyValueFactory<>("goals1"));
	gameTableView.getColumns().add(goals1Column);
	    
        TableColumn<Game, String> goals2Column = new TableColumn<>("AGoals");
        goals2Column.setCellValueFactory(new PropertyValueFactory<>("goals2"));
	gameTableView.getColumns().add(goals2Column);
        
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(gameTableView, 0, 0);
        rootGridPane.add(buttonFlowPane, 0, 1);        
        
        tableSync();
        
        scene = new Scene(rootGridPane,600,400);
        
    }
    
    
    public void tableSync() {
        List<Game> items = gameTableView.getItems();
        items.clear();
        
        for(Game t: Main.games) {
            items.add(t);
            System.out.println(t.home);
        }
    }    
}
