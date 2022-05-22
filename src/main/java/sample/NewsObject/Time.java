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

public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private News news;

    public Time(Time time) {
        this.year = time.getYear();
        this.month = time.getMonth();
        this.day = time.getDay();
        this.hour = time.getHour();
        this.minute = time.getMinute();
        this.news = time.getNews();
    }

    public Time(int year, int month, int day, int hour, int minute, News news) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.news = news;
    }

    public News getNews() {
        return this.news;
    }

    public int getYear() {return year;}

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
