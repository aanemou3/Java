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


public class TeamScene {
    Scene scene;
    TextField teamField;
    TableView<Team> teamTableView;
    
    public TeamScene(){
    
    
        GridPane rootGridPane = new GridPane(); 
        
        FlowPane buttonFlowPane= new FlowPane();

        Button newTeamBtn= new Button("New Team");
        newTeamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team t = new Team(teamField.getText());
                System.out.println(t.name);
                Main.teams.add(t);
                tableSync();
                teamField.clear();
            }
        });
        
        Button deleteTeamBtn = new Button("Delete Team");
        deleteTeamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = teamField.getText();
                
                //first remove games of this team (start from end of list for correct indexes)
                for(int j = Main.games.size()-1; j >=0; j--){
                    Game g = Main.games.get(j);
                    if(g.home.equals(name) || g.away.equals(name)){
                        System.out.println(name);
                        g.cancel_points();
                        Main.games.remove(j);
                    }
                }
                //remove this team
                for(int i=0; i < Main.teams.size(); i++){
                    Team t = Main.teams.get(i);
                    if(t.name.equals(name)){
                        Main.teams.remove(i);
                        tableSync();
                        teamField.clear();
                        break;
                    }
                }
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
        buttonFlowPane.getChildren().add(newTeamBtn);
        buttonFlowPane.getChildren().add(deleteTeamBtn);
        buttonFlowPane.getChildren().add(backBtn);
        buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);

        GridPane inputFieldsPane = new GridPane();
        Label teamLbl = new Label("Team Name: ");
        teamField = new TextField();
        
        inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
        inputFieldsPane.setVgap(10);
        inputFieldsPane.setHgap(10);
        inputFieldsPane.add(teamLbl, 0, 0);
        inputFieldsPane.add(teamField, 0,1);
        
        teamTableView = new TableView<>();

        TableColumn<Team, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	teamTableView.getColumns().add(nameColumn);
	    
        TableColumn<Team, String> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
	teamTableView.getColumns().add(pointsColumn);
	
        teamTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Team selected = teamTableView.getSelectionModel().getSelectedItem();
                if(selected != null) {
                        teamField.setText(selected.name);
                }
            
            }
        });
        
        rootGridPane.setVgap(10);
        rootGridPane.setHgap(10);
        rootGridPane.add(inputFieldsPane, 1, 0);
        rootGridPane.add(teamTableView, 0, 0);
        rootGridPane.add(buttonFlowPane, 0, 1);        
        
        tableSync();
        
        scene = new Scene(rootGridPane,600,400);
        
    }
    
    
    public void tableSync() {
        List<Team> items = teamTableView.getItems();
        items.clear();
        
        Collections.sort(Main.teams);
        
        for(Team t: Main.teams) {
            items.add(t);
            //System.out.println(t.name);
        }
    }    
}
