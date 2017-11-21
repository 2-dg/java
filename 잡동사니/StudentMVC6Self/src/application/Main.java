package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


   @Override
   public void start(Stage primaryStage) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/view/view.fxml"));
         Scene scene=new Scene(root);
         primaryStage.setTitle("檬殿切积 己利包府");
         primaryStage.setScene(scene);
         primaryStage.show();
   }

   public static void main(String[] args) {
      launch(args);
      
   }
}