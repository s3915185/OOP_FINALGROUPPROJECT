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

package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sample.Controllers.*;

import java.io.IOException;

public class changeCategory {
    public Stage stage;
    private Parent base;

    // This basically loads a new fxml file, generate a controller, and set stage for it

    // To: Newest
    public void toNewPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/NewestPage.fxml"));
        base = loader.load();
        NewestController newestController = loader.getController();
        newestController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Covid
    public void toCovidPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/CovidPage.fxml"));
        base = loader.load();
        CovidController covidController = loader.getController();
        covidController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Politics
    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/PoliticsPage.fxml"));
        base = loader.load();
        PoliticsController politicsController = loader.getController();
        politicsController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Business
    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/BusinessPage.fxml"));
        base = loader.load();
        BusinessController businessController = loader.getController();
        businessController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Tech
    public void toTechPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/TechPage.fxml"));
        base = loader.load();
        TechController techController = loader.getController();
        techController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Health
    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/HealthPage.fxml"));
        base = loader.load();
        HealthController healthController = loader.getController();
        healthController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Sports
    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/SportsPage.fxml"));
        base = loader.load();
        SportController sportController = loader.getController();
        sportController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Entertainment
    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/EntertainmentPage.fxml"));
        base = loader.load();
        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: World
    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/WorldPage.fxml"));
        base = loader.load();
        WorldController worldController = loader.getController();
        worldController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }

    // To: Others
    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/OthersPage.fxml"));
        base = loader.load();
        OthersController othersController = loader.getController();
        othersController.setListPage(0);
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(base);
        stage.show();
    }
}
