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

package sample.Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loadingScreenController implements Initializable {
    @FXML
    AnchorPane coverPane;

    @FXML
    private ImageView rmitLogo;

    @FXML
    private ImageView rollingCircle;

    @FXML
    private ImageView backImage;

    @FXML
    private ImageView text;

    private void setLoading(ImageView c, int angle, int duration, ImageView img, ImageView backimg, ImageView text) {
        RotateTransition rotateCircle = new RotateTransition(Duration.seconds(duration), c);
        rotateCircle.setAutoReverse(false);
        rotateCircle.setByAngle(360);
        rotateCircle.setRate(3);
        rotateCircle.setCycleCount(1000);
        rotateCircle.play();

        TranslateTransition ImgMove = new TranslateTransition(Duration.millis(2000), img);
        ImgMove.setByY(20f);
        ImgMove.setCycleCount(1000);
        ImgMove.setAutoReverse(true);
        ImgMove.play();

        FadeTransition fadeBackground = new FadeTransition(Duration.millis(3000), backimg);
        fadeBackground.setFromValue(1.0);
        fadeBackground.setToValue(0.3);
        fadeBackground.setCycleCount(1000);
        fadeBackground.setAutoReverse(true);
        fadeBackground.play();

        TranslateTransition TextMove = new TranslateTransition(Duration.millis(2000), text);
        TextMove.setByX(10f);
        TextMove.setCycleCount(1000);
        TextMove.setAutoReverse(true);
        TextMove.play();

        FadeTransition TextFade = new FadeTransition(Duration.millis(850), text);
        TextFade.setFromValue(1.0);
        TextFade.setToValue(0.2);
        TextFade.setCycleCount(1000);
        TextFade.setAutoReverse(true);
        TextFade.play();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLoading(rollingCircle, 360, 20, rmitLogo, backImage, text);
        new SplashScreen().start();
    }
    class SplashScreen extends Thread {
        @Override
        public void run() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/NewestPage.fxml"));
            Parent mainPage = null;
            try {
                mainPage = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // set content for the homepage
            NewestController newController = loader.getController();
            newController.setListPage(0);
            Parent finalHomePage = mainPage;
            Platform.runLater(() -> {

                Stage stage = new Stage();
                stage.setScene(new Scene(finalHomePage));
                stage.show();

                coverPane.getScene().getWindow().hide();
            });


        }
    }
}
