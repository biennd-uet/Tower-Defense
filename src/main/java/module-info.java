module towerdefense {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    exports townerdefense;
    opens townerdefense;
    opens townerdefense.engine;
    opens townerdefense.control;
    opens townerdefense.model;
}