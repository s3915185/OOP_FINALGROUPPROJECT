/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2022A
  Assessment: Final Project
  Created  date: 20/4/2022
  Author:   Tran Hoang Vu       s3915185
            Hua Nam Huy         s3881103
            Nguyen Giang Huy    s3836454
            Nguyen Phan Duc Anh s3915181
  Last modified: 22/05/2022
  Acknowledgement:
  JavaFX Animations: https://www.youtube.com/watch?v=UdGiuDDi7Rg
  JavaFX Animations and Transition Splash Screen / Welcome Screen - Netbeans and SceneBuilder: https://www.youtube.com/watch?v=Fy0ZVF7EPC4
  JavaFX Splash Screen 2 : Loading In a Seperate Window: https://www.youtube.com/watch?v=f06uUtkmtDE
  HTML & CSS Full Course 🌎 -Learn to create a website-【𝙁𝙧𝙚𝙚】: https://www.youtube.com/watch?v=cyuzt1Dp8X8
  JavaFx Event Handling Information: https://www.tutorialspoint.com/javafx/javafx_event_handling.htm
  HTML Parsing Jsoup: https://www.scrapingbee.com/blog/java-parse-html-jsoup/
  Selection Sort: https://www.programiz.com/dsa/selection-sort
  Scene Switching: https://www.youtube.com/watch?v=hcM-R-YOKkQ
  JavaFX and Threads: https://www.youtube.com/watch?v=Xb6j8VfHxJo
  Java Web Scraping: https://www.youtube.com/watch?v=yw7B85174JQ
*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controllers.NewestController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/loadingScreen.fxml"));
        Parent homePage;

        try {
            homePage = loader.load();
        }catch (Exception ex) {
            loader = new FXMLLoader(getClass().getResource("/FXMLs/NewestPage.fxml"));
            homePage = null;
            try {
                homePage = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // set content for the homepage
            NewestController newController = loader.getController();
            newController.setListPage(0);
        }

        stage.setTitle("Anh Em Cot Cheo News!");
        Image icon = new Image("file:src/main/resources/Image/loadingScreen.png");
        stage.getIcons().add(icon);
        stage.setScene(new Scene(homePage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
