module sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens sample to javafx.fxml;
    exports sample;
    exports sample.Controllers;
    opens sample.Controllers to javafx.fxml;
    opens sample.BaseController to javafx.fxml;
    exports sample.BaseController;
}