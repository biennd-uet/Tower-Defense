module towerdefense {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;

    exports townerdefense;
    opens townerdefense;
    opens townerdefense.engine;
    opens townerdefense.control;
}