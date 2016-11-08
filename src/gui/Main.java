package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
      launch(args);
  }
  
  @Override
  public void start(Stage s) {
      s.setTitle(Names.application.get());
      Button b = new Button();
      b.setText("Say 'Hello World'");
      b.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent __) {
              System.out.println("Hello World!");
          }
      });
      
      StackPane r = new StackPane();
      r.getChildren().add(b);
      s.setScene(new Scene(r, 300, 300));
      s.show();
  }
}
