/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2022A
  Assessment: Final Project
  Created  date: 20/4/2022
  Author:   Tran Hoang Vu       s3915185
            Hua Nam Huy         s3991103
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.BaseController.changeCategory;
import sample.NewsObject.News;

import java.io.IOException;
import java.util.ArrayList;

public class ArticleController extends changeCategory {
    @FXML
    VBox articleContent;

    @FXML
    BorderPane coverPane;

    @FXML
    ScrollPane base;

    private Scene previousScene;
//    display error message on the screen
    public void articleNotFound() {
        coverPane.prefWidthProperty().bind(base.widthProperty());
        articleContent.setSpacing(20);
        Label error = new Label();
        error.setText("Error 404: File not found.");
        error.setFont(Font.font("", FontWeight.BOLD, 50));
        error.setWrapText(true);
        articleContent.getChildren().clear();
        articleContent.getChildren().add(error);
    }


    // set title's style
    private void headerStyle(String title) {
        Label head = new Label();
        head.setText(title);
        head.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        head.setWrapText(true);
        head.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
        articleContent.getChildren().add(head);
    }

    // set description's style
    private void descripStyle(String description) {
        Label descrip = new Label();
        descrip.setText(description);
        descrip.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
        descrip.setWrapText(true);
        descrip.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
        articleContent.getChildren().add(descrip);
    }



    //Scrape for Express

    private void ScrapeForExpress(Document doc, News news) {
        Elements paragraph = doc.select("p");
        Elements figure = doc.select("figure");
        String[] paragraphsArray = paragraph.toString().split("\n");
        String[] figures = figure.toString().split("</figure>");
        int count = 0;
        for (String para : paragraphsArray) {
            Document readScript = Jsoup.parse(para);
            if (isParagraph(readScript.text(),news.getDescrip())) {
                ParagraphStyle(readScript.text());}
            if (!isParagraph(readScript.text(),news.getDescrip()) && !readScript.text().contains("Video: ") && !readScript.text().contains("TTO")) {
                try {
                    Document image = Jsoup.parse(figures[count].replaceAll("\n", "") + "</figure>");
                    String[] imageList = image.select("source").attr("data-srcset").split(" ");
                    VBox pictures = new VBox();
                    ImageView picture = new ImageView(new Image(imageList[count]));
                    Label pictureDescrip = new Label(readScript.text());
                    pictureDescrip.setFont(Font.font("Times New Roman", FontWeight.NORMAL,FontPosture.ITALIC,15));
                    pictureDescrip.setWrapText(true);
                    pictureDescrip.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
                    pictures.getChildren().addAll(picture,pictureDescrip);
                    articleContent.getChildren().add(pictures);
                    count++;
                } catch (Exception ex) {}
            }
        }
    }

        // Scrape For Tuoi Tre
    private void ScrapeForTuoiTre(Document doc, News news) {
        int count = 0;
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> descrips = new ArrayList<>();
        Elements list = doc.select("div.main-content-body div.VCSortableInPreviewMode");
        for (Element htmlEle : list) {
            String img = htmlEle.select("img").attr("src");
            images.add(img);
        }
        for (Element htmlEle : list) {
            String desImg = htmlEle.select("div.PhotoCMS_Caption p").text();
            descrips.add(desImg);
        }

        Elements paragraph = doc.select("div.main-content-body p");
        String[] paragraphArrays = paragraph.toString().split("\n");
        for (String paragraphElement : paragraphArrays) {
            Document readScript = Jsoup.parse(paragraphElement);
            if (images.size() > 0 && paragraph.text().contains(descrips.get(count)) && count < descrips.size() - 1) {
                try {
                    VBox pictures = new VBox();
                    ImageView picture = new ImageView(new Image(images.get(count)));
                    Label pictureDescrip = new Label(descrips.get(count));
                    pictureDescrip.setFont(Font.font("Times New Roman", FontWeight.NORMAL,FontPosture.ITALIC,15));
                    pictureDescrip.setWrapText(true);
                    pictureDescrip.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
                    pictures.getChildren().addAll(picture,pictureDescrip);
                    articleContent.getChildren().add(pictures);
                    count++;
                } catch (Exception ex) {}
            }
            String mainText = generateScript(readScript.text(), descrips);
            if(!mainText.equals("") && isParagraph(mainText, news.getDescrip())) {
                ParagraphStyle(mainText);
            }
        }
        Elements author = doc.select("div.main-content-body div.author");
        articleContent.getChildren().add(new Label(author.text()));
    }

    // Scrape for Zing
    private void ScrapeForZing(Document doc, News news) {
        ArrayList<String> articles = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> descrips = new ArrayList<>();


        // get all informations : image, article, description down and save them
        Elements articlePicker = doc.select("div.the-article-body div.inner-article p");
        for (Element el : articlePicker) {
            String articleText = el.text();
            articles.add(articleText);
        }
        Elements Box = doc.select("table.picture tbody tr td");
        String[] BoxImg = Box.select("td.pic").toString().split("\"");
        for (String box : BoxImg) {
            if(box.contains("https://")) {
                images.add(box);
            }
        }
        String[] BoxDes = Box.select("td.pCaption").toString().split("\n");
        for (String box : BoxDes) {
            Document document = Jsoup.parse(box);
            String desImg = document.select("p").text();
            descrips.add(desImg);
        }


        // Loop through a document online again, and display the same way that website displayed
        int count = 0;
        Elements elements = doc.select("div.the-article-body p");
        String[] paragraphs = elements.toString().split("\n");

        for (String paragraph : paragraphs) {
            Document docScript = Jsoup.parse(paragraph);

            if (images.size() > 0 && docScript.text().contains(descrips.get(count)) && count < descrips.size() - 1) {
                try {
                    VBox pictures = new VBox();
                    ImageView picture = new ImageView(new Image(images.get(count)));
                    picture.setFitWidth(720);
                    picture.setPreserveRatio(true);
                    Label pictureDescrip = new Label(descrips.get(count));
                    pictureDescrip.setWrapText(true);
                    pictureDescrip.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
                    pictures.getChildren().addAll(picture,pictureDescrip);
                    articleContent.getChildren().add(pictures);
                    count++;
                } catch (Exception ex) {}
            }
            if (checkZingSpecial(docScript.text(), articles, news.getDescrip())) {
                String mainText = generateScript(docScript.text(), descrips);
                if (!mainText.equals(""))
                    ParagraphStyle(mainText);
            }
        }
    }

    // Scrape for Nhan Dan
    private void ScrapeForNhanDan(Document doc, News news) {
        VBox viewPictures = new VBox();
        Elements pictures = doc.select("div.box-detail-thumb");
        ImageView picture = new ImageView(new Image(pictures.select("img").attr("src")));
        Label pictureDescrip = new Label(pictures.text());//add photo description
        pictureDescrip.setFont(Font.font("Roboto", FontWeight.NORMAL,FontPosture.ITALIC,15));
        pictureDescrip.setWrapText(true);
        pictureDescrip.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
        viewPictures.setSpacing(0);
        viewPictures.getChildren().addAll(picture,pictureDescrip);
        articleContent.getChildren().add(viewPictures);

        Elements docText = doc.select("div.entry-content div.detail-content-body p");
        for (Element para : docText) {
            ParagraphStyle(para.text());
        }
        Label author = new Label(doc.select("div.entry-content div.box-author").text());
        author.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
        author.setWrapText(true);
        author.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
        articleContent.getChildren().add(author);
    }


    // Scrape For Thanh Nien

    private void ScrapeForThanhNien(Document doc, News news) {
        Elements elementsInfo = doc.select("td.pic");
        ImageView picture = new ImageView(new Image(elementsInfo.select("img").attr("src")));
        picture.setPreserveRatio(true);
        picture.setFitWidth(1280);
        articleContent.getChildren().add(picture);
        Elements elements1 = doc.select("div[itemprop=articleBody] div p");
        Elements elements2 = doc.select("div[itemprop=articleBody] div");
        Elements elements;

        if (elements1.size() < elements2.size()) {
            elements = elements2;
        }else {
            elements = elements1;
        }

        for (Element paragraph : elements) {
            String badText = paragraph.select("div.imgcaption p").text();
            if (paragraph.text().contains(badText)) {
                String mainText = paragraph.text().replace(badText, "");
                if (!mainText.equals("")) {
                    ParagraphStyle(mainText);
                }
            }
        }
        Elements author = doc.select("div.left h4");
        Label authLabel = new Label(author.text());
        articleContent.getChildren().add(authLabel);
    }

    public void setBodyContent(News news) throws IOException {

        // layout
        coverPane.prefWidthProperty().bind(base.widthProperty());
        articleContent.setSpacing(20);
        articleContent.setPadding(new Insets(10,10,10,10));

        // style for header and description
        headerStyle(news.getTitle());
        descripStyle(news.getDescrip());

        // Web Scraper
        Document doc = Jsoup.connect(news.getNewsURL()).get();
        switch (news.getNewsPublisher()) {
            // scrape article from VN express
            case "VN Express":  ScrapeForExpress(doc, news); break;

            case "Tuoi Tre": ScrapeForTuoiTre(doc, news);   break;

            case "Zing News":  ScrapeForZing(doc, news);    break;

            case "Nhan Dan": ScrapeForNhanDan(doc, news); break;

            case "Thanh Nien": ScrapeForThanhNien(doc, news);   break;
        }
        Label footer = new Label();
        footer.setText(news.getNewsPublisher() + " - " + news.getNewsTimeRoll());
        articleContent.getChildren().add(footer);
    }

    private void ParagraphStyle(String paragraph) {
        Label text = new Label();
        text.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
        text.setText(paragraph);
        text.prefWidthProperty().bind(articleContent.widthProperty().divide(3).multiply(2));
        text.setWrapText(true);
        articleContent.getChildren().add(text);
    }

    private boolean isParagraph(String paragraph, String description) {
        String[] condition = {"Ảnh:","Video:","TTO","Ảnh chụp màn hình","Ảnh minh họa:" };
        for (String str : condition) {
            if (paragraph.contains(str)) return false;
        }
        return !paragraph.replaceAll("\\s+", "").equalsIgnoreCase(description.replaceAll("\\s+", ""));
    }

    private boolean checkZingSpecial(String text, ArrayList<String> articleTexts, String descrip) {
        for (String articleText : articleTexts) {
            if (text.equals(articleText)) return false;
        }
        return isParagraph(text, descrip);
    }

    public void getPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    // set the previous scene
    public void goBack(ActionEvent actionEvent) {
        double width = ((Node)actionEvent.getSource()).getScene().getWindow().getWidth();
        double height = ((Node)actionEvent.getSource()).getScene().getWindow().getHeight();
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(this.previousScene);
        stage.sizeToScene();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    private String generateScript(String readScript, ArrayList<String> descrips) {
        for (String descrip : descrips) {
             readScript = readScript.replace(descrip, "");
        }
        return readScript;
    }
}
