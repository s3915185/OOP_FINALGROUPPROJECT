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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.Controllers.ArticleController;
import sample.NewsObject.News;
import sample.NewsObject.NewsLoader;
import sample.NewsObject.Time;
import sample.SupportClass.MethodsAssist;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

// All the controllers have same actions, so we put them into one big class and inherit from it
public class loadCategoryPage extends changeCategory {
    @FXML
    AnchorPane coverPane;
    @FXML
    ScrollPane parent;
    @FXML
    ArrayList<ImageView> imgList;
    @FXML
    ArrayList<Label> titleList;
    @FXML
    ArrayList<Label> descriptionList;
    @FXML
    ArrayList<Label> timeList;
    @FXML
    ArrayList<Label> publisherList;

    // Private attributes
    private Stage stage;
    private Scene scene;
    private Parent base;


    // Having extra methods to support loading in data, and store 50 articles into the list
    private MethodsAssist methodsAssist = new MethodsAssist();
    private NewsLoader newsLoader = new NewsLoader() ;

    // default parameter
    public loadCategoryPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL, String thanhNienURL, String zingURL) throws IOException {
        newsLoader.loadVnExpress(vnExpressURL);
        newsLoader.loadTuoiTre(tuoiTreURL);
        newsLoader.loadThanhNien(thanhNienURL);
        newsLoader.loadZingNews(zingURL);
        newsLoader.loadNhanDan(nhanDanUrl);

        //sorting the articles
        news_sort();
    }

    // use for splitting the time in the article time box
    private Time TimeModifying(String time, News news) {
        String timePublished;
        //Example:2022-05-20T00:00:00+07:00
        String dateFromSource = time.split("T")[0];
        //dateFromSource = 2022-05-20
        int hourFromSource = 0;
        int minutesFromSource = 0;

        // timePublished = 00:00:00
        timePublished = time.split("T")[1].split("[+]")[0];

        //hourFromSource = 00
        // minutesFromSource = 00
        hourFromSource = Integer.parseInt(timePublished.split(":")[0]);
        minutesFromSource = Integer.parseInt(timePublished.split(":")[1]);
        //yearFromSource = 2022
        //dayFromSource = 20
        //monthFromSource = 05
        int yearFromSource = Integer.parseInt(dateFromSource.split("-")[0]);
        int dayFromSource = Integer.parseInt(dateFromSource.split("-")[2]);
        int monthFromSource = Integer.parseInt(dateFromSource.split("-")[1]);
        return new Time(yearFromSource, monthFromSource, dayFromSource, hourFromSource, minutesFromSource, news);
    }


    private void news_sort() {
        // initials
        int counter = 0;
        ArrayList<Time> timesFromSource = new ArrayList<>();



        for (int i = 0; i < this.newsLoader.getSize(); i++) {
            try {
                Document newsDocument = Jsoup.connect(newsLoader.getNews(i).getNewsURL()).timeout(200).get();

                // This is not for NhanDan
                String timeFromSource = newsDocument.select("meta[itemprop=datePublished]").attr("content");
                if (timeFromSource.isEmpty()) {
                    // This is for Nhan Dan
                    timeFromSource = newsDocument.select("div.box-date").text();
                    if (!timeFromSource.isEmpty()) {
                        String date = timeFromSource.split(", ")[1];
                        String time = timeFromSource.split(", ")[2];
                        String day = date.split("-")[0];
                        String month = date.split("-")[1];
                        String year = date.split("-")[2];
                        String hour = time.split(":")[0];
                        String minutes = time.split(":")[1];
                        timeFromSource = year + "-" + month + "-" + day + "T" + hour + ":" + minutes + ":00+07:00";
                    }
                }

                if (timeFromSource.isEmpty()) {
                    timeFromSource = newsDocument.select("meta[property=article:published_time]").attr("content");
                }
                Time newsTime = TimeModifying(timeFromSource, newsLoader.getNews(i));
                if (!timeFromSource.isEmpty() && (newsTime != null)) {
                    // add to the newsList
                    timesFromSource.add(newsTime);
                    counter++;
                    if (counter == 50) break;
                }
            } catch (Exception ex) {
            }
        }


        // SELECTION SORT
        for (int firstIndexInArray = 0; firstIndexInArray < timesFromSource.size() - 1; firstIndexInArray++) {
            for (int secondIndexInArray = firstIndexInArray; secondIndexInArray < timesFromSource.size(); secondIndexInArray++) {
                if (timesFromSource.get(firstIndexInArray).getMonth() < timesFromSource.get(secondIndexInArray).getMonth()) {
                    Time tempTime = new Time(timesFromSource.get(secondIndexInArray));
                    timesFromSource.set(secondIndexInArray, timesFromSource.get(firstIndexInArray));
                    timesFromSource.set(firstIndexInArray, tempTime);
                } else if (timesFromSource.get(firstIndexInArray).getMonth() == timesFromSource.get(secondIndexInArray).getMonth()) {
                    if (timesFromSource.get(firstIndexInArray).getDay() < timesFromSource.get(secondIndexInArray).getDay()) {
                        Time tempTime = new Time(timesFromSource.get(secondIndexInArray));
                        timesFromSource.set(secondIndexInArray, timesFromSource.get(firstIndexInArray));
                        timesFromSource.set(firstIndexInArray, tempTime);
                    } else if (timesFromSource.get(firstIndexInArray).getDay() == timesFromSource.get(secondIndexInArray).getDay()) {
                        if (timesFromSource.get(firstIndexInArray).getHour() < timesFromSource.get(secondIndexInArray).getHour()) {
                            Time tempTime = new Time(timesFromSource.get(secondIndexInArray));
                            timesFromSource.set(secondIndexInArray, timesFromSource.get(firstIndexInArray));
                            timesFromSource.set(firstIndexInArray, tempTime);
                        } else if (timesFromSource.get(firstIndexInArray).getHour() == timesFromSource.get(secondIndexInArray).getHour()) {
                            if (timesFromSource.get(firstIndexInArray).getMinute() < timesFromSource.get(secondIndexInArray).getMinute()) {
                                Time tempTime = new Time(timesFromSource.get(secondIndexInArray));
                                timesFromSource.set(secondIndexInArray, timesFromSource.get(firstIndexInArray));
                                timesFromSource.set(firstIndexInArray, tempTime);
                            }
                        }
                    }
                }
            }
        }


        // use local date class to compute the time duration of article
        LocalDate localDate = LocalDate.now();
        Calendar calendar = new GregorianCalendar();
        String timeRoll = "";
        String datePublished;
        newsLoader.clearList();
        for (Time times : timesFromSource) {
            if (localDate.getMonthValue() - times.getMonth() == 0) {
                if (localDate.getDayOfMonth() - times.getDay() == 0) {
                    if (calendar.get(Calendar.HOUR_OF_DAY) - times.getHour() == 0) {
                        if (calendar.get(Calendar.MINUTE) - times.getMinute() == 0) {
                            timeRoll = "Just now";
                        } else {
                            timeRoll = (calendar.get(Calendar.MINUTE) - times.getMinute()) + " minutes ago";
                        }
                    } else {
                        timeRoll = (calendar.get(Calendar.HOUR_OF_DAY) - times.getHour()) + " hours ago";
                    }
                } else {
                    timeRoll = (localDate.getDayOfMonth() - times.getDay()) + " days ago";
                }
            } else {
                timeRoll = (localDate.getMonthValue() - times.getMonth()) + " months ago";
            }
            datePublished = times.getDay() + "/" + times.getMonth() + "/" + localDate.getYear() + " - " + times.getHour() + ":" + times.getMinute();
            newsLoader.insert(times.getNews(), datePublished, timeRoll);
        }
    }

    // set the first 10 articles to the frontpage
    public void setListPage(int begin) {
        new MethodsAssist().setImgs(imgList, begin, this.newsLoader);
        methodsAssist.setDescrips(descriptionList, begin, this.newsLoader);
        methodsAssist.setTitles(titleList, begin, this.newsLoader);
        methodsAssist.setTimes(timeList, begin, this.newsLoader);
        methodsAssist.setPublishers(publisherList, begin, this.newsLoader);
    }

    // moveto each page according to the buttons
    public void moveToPage(ActionEvent event) throws IOException {
        Button buttonClicked = (Button) event.getSource();
        int index = Integer.parseInt(buttonClicked.getText()) * 10 -10;
        setListPage(index);
        parent.setVvalue(0);
    }

    // Display the article clicked
    public void toArticle(MouseEvent event) throws IOException {
        // load the article first
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/ArticleView.fxml"));
        base = loader.load();

        // try to find out which article the user clicked
        Label textClicked =(Label) event.getSource();
        int index =Integer.parseInt(textClicked.getId().substring(5)) - 1;



        // get height and width
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        // access controller
        ArticleController articleController = loader.getController();

        // bring the article to stage
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        // make article appears into small rectangles
        try {
            articleController.setBodyContent(this.newsLoader.searchFor(titleList.get(index).getText()));
        } catch (Exception ex) {
            // set error scene
            articleController.articleNotFound();
        }


        // get the stage equals to the size of previous screen
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(base, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
