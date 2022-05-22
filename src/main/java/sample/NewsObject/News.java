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

package sample.NewsObject;

public class News {
    private final String newsURL;
    private final String title;
    private final String descrip;
    private final String imageURL;
    private String newsPublisher;
    private String newsTime;
    private String newsTimeRoll;

    public News(String newsURL, String title, String descrip, String imageURL, String newsTime, String newsTimeRoll) {
        this.newsURL = newsURL;
        this.title = title;
        this.descrip = descrip;
        this.imageURL = imageURL;
        this.newsTime = newsTime;
        this.newsTimeRoll = newsTimeRoll;
        if (newsURL.contains("https://thanhnien.vn")) this.newsPublisher = "Thanh Nien";
        else if (newsURL.contains("https://vnexpress.net")) this.newsPublisher = "VN Express";
        else if (newsURL.contains("https://zingnews.vn")) this.newsPublisher = "Zing News";
        else if (newsURL.contains("https://nhandan.vn")) this.newsPublisher = "Nhan Dan";
        else if (newsURL.contains("https://tuoitre.vn")) this.newsPublisher = "Tuoi Tre";
    }

    public News(String newsURL, String title, String descrip, String imageURL) {
        this.newsURL = newsURL;
        this.title = title;
        this.descrip = descrip;
        this.imageURL = imageURL;
        if (newsURL.contains("https://thanhnien.vn")) this.newsPublisher = "Thanh Nien";
        else if (newsURL.contains("https://vnexpress.net")) this.newsPublisher = "VN Express";
        else if (newsURL.contains("https://zingnews.vn")) this.newsPublisher = "Zing News";
        else if (newsURL.contains("https://nhandan.vn")) this.newsPublisher = "Nhan Dan";
        else if (newsURL.contains("https://tuoitre.vn")) this.newsPublisher = "Tuoi Tre";
    }

    public String getNewsTimeRoll() {
        return this.newsTimeRoll;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public String getTitle() {
        return title;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getNewsPublisher() {
        return newsPublisher;
    }
}
