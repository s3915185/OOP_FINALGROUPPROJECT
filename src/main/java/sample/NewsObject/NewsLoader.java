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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsLoader {
    ArrayList<News> newsLoader = new ArrayList<>();


    public void insert(News news, String publishedDate, String timeRoll) {
        newsLoader.add(new News(news.getNewsURL(), news.getTitle(), news.getDescrip(), news.getImageURL(), publishedDate, timeRoll));
    }


    // Load for Thanh Nien
    public void loadThanhNien(String url) throws  IOException {
        String newsURL;
        String title;
        String descrip;
        String imageURL;
        String origin = "https://thanhnien.vn/";
        Document doc = Jsoup.connect(url).get();
        Elements articles = doc.select("div.relative article.story");
        for (Element article : articles) {
                newsURL = article.select("a").attr("href");
                if (!newsURL.contains("https:")) newsURL = origin + newsURL;
                title = article.select("a").attr("title");
                descrip = article.select("div.summary").text();
                imageURL = article.select("a img").attr("data-src");
                if (imageURL.isEmpty() || title.isEmpty()) {
                    continue;
                }
            this.newsLoader.add(new News(newsURL, title, descrip, imageURL));
        }
    }


    // Load for Zing news
    public void loadZingNews(String url) throws  IOException {
        String originalURL = "https://zingnews.vn";
        String newsURL;
        String title;
        String descrip;
        String imageURL;

        Document document = Jsoup.connect(url).get();
        Elements article = document.select("article.article-item");
        String[] articleStrings = article.toString().split("</article>");
        for (String articleText: articleStrings) {
            Document readScript = Jsoup.parse(articleText.replaceAll("\n", "") + "</article>");
            newsURL = originalURL + readScript.select("a").attr("href");
            title = readScript.select("header p.article-title").text();
            imageURL = readScript.select("img").attr("data-src");
            descrip = readScript.select("header p.article-summary").text();
            if (imageURL.isEmpty()) {
                imageURL = readScript.select("img").attr("src");
            }
            if (newsURL.isEmpty() || imageURL.isEmpty() || title.isEmpty()) {
                continue;
            }

            this.newsLoader.add(new News(newsURL, title, descrip, imageURL));
        }
    }

    // Load for tuoi tre
    public void loadTuoiTre(String url) throws  IOException {
        String originalURL = "https://tuoitre.vn";
        String newsURL;
        String title;
        String descrip;
        String imageURL;

        Document doc = Jsoup.connect(url).get();
        Elements articles = doc.select("li.news-item");
        String[] articleTexts = articles.toString().split("</li>");
        for (String article: articleTexts) {
            Document readScript = Jsoup.parse(article.replaceAll("\n", "") + "</li>");
            title = readScript.select("a").attr("title");
            newsURL = originalURL + readScript.select("a").attr("href");
            if(!url.equals("https://congnghe.tuoitre.vn")) {
                imageURL = readScript.select("a img").attr("src");
            } else {
                // get image url for tuoi tre tech
                Document image = Jsoup.connect(newsURL).get();
                imageURL = image.select("div.main-content-body div.VCSortableInPreviewMode img").attr("src");
            }
            descrip = readScript.select("div.name-news p.sapo").text();

            if(title.isEmpty() || imageURL.isEmpty() || newsURL.isEmpty() || descrip.isEmpty()) continue;
            this.newsLoader.add(new News(newsURL, title, descrip, imageURL));
        }
    }

    // load for vn express
    public void loadVnExpress(String webURL) throws IOException {
        Document doc = Jsoup.connect(webURL).get();
        Elements articles = doc.select("article.item-news");
        String newsURL;
        String title;
        String descrip;
        String imageURL[];
        for (Element article: articles) {
            title = article.select("h3 a").attr("title");
            descrip = article.select("p a").text();
            imageURL = article.select("div a picture source").attr("srcset").toString().split(" ");
            if (imageURL[0].isEmpty()) {
                imageURL = imageURL = article.select("div a picture source").attr("data-srcset").toString().split(" ");
            }
            newsURL = article.select("h3 a").attr("href");
            if (imageURL[0].isEmpty() || newsURL.isEmpty() || descrip.isEmpty() || title.isEmpty()) {
                continue;
            }
            this.newsLoader.add(new News(newsURL, title, descrip, imageURL[0]));
        }
    }

    // load for nhan dan
    public void loadNhanDan(String url) throws  IOException {
        String link = "https://nhandan.vn";
        String newsURL;
        String title;
        String descrip;
        String imageURL;

        Document doc = Jsoup.connect(url).get();
        Elements articles = doc.select("article");
        for (Element article: articles) {
            title = article.select("div.box-img a").attr("title");
            imageURL = article.select("div.box-img a img").attr("data-src");
            newsURL = link + article.select("div.box-img a").attr("href");
            descrip = article.select("div.box-des p").text();
            newsLoader.add(new News(newsURL, title, descrip, imageURL));
        }
    }

    // find article that is saved in the arrays, then show it to the article
    public News searchFor(String title) {
        for (News news : this.newsLoader) {
            if (news.getTitle().equals(title)) {
                return news;
            }
        }
        return null;
    }

    public void clearList() {
        newsLoader.clear();
    }

    public int getSize() { return this.newsLoader.size(); }

    public News getNews(int index) {
        return newsLoader.get(index);
    }
}
