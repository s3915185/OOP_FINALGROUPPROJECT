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

package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.NewsObject.NewsLoader;

import java.util.ArrayList;

public class MethodsAssist {

    public void setTitles(ArrayList<Label> labelList, int begin, NewsLoader newsList){
        int count = begin;
        for (Label title: labelList) {
            title.setFont(Font.font("Time New Roman", FontWeight.BOLD, 25));
            title.setAlignment(Pos.TOP_LEFT);
            title.setWrapText(true);
            title.setText(newsList.getNews(count).getTitle());
            count++;
        }
    }

    public void setDescrips(ArrayList<Label> labelList, int begin, NewsLoader newsList){
        int count = begin;
        for (Label description: labelList) {
            description.setFont(Font.font("Time New Roman", FontWeight.NORMAL, 15));
            description.setAlignment(Pos.TOP_LEFT);
            description.setWrapText(true);
            description.setText(newsList.getNews(count).getDescrip());
            count++;
        }
    }

    public void setImgs(ArrayList<ImageView> imgList, int begin, NewsLoader newsList){
        int count = begin;
        for (ImageView img: imgList) {
                img.setImage(new Image(newsList.getNews(count).getImageURL()));
                img.autosize();
                count++;
        }
    }

    public void setTimes(ArrayList<Label> timeList, int begin, NewsLoader newsList) {
        int count = begin;
        for (Label timeLabel : timeList) {
            timeLabel.setText(newsList.getNews(count).getNewsTimeRoll());
            count++;
        }
    }

    public void setPublishers(ArrayList<Label> publisherList, int begin, NewsLoader newsList) {
        int count = begin;
        for (Label publisher : publisherList) {
            publisher.setText(newsList.getNews(count).getNewsPublisher());
            count++;
        }
    }

}
