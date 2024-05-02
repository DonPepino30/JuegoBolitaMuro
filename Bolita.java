import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Bolita implements Initializable {
    @FXML
    private AnchorPane marco;

    @FXML
    private Circle circulo;

    private Line linea;
    Timeline jueguito = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

        double diferencialx = 2;
        double diferencialy = 2;

        @Override
        public void handle(ActionEvent actionEvent) {

            circulo.setLayoutX(circulo.getLayoutX() + diferencialx);
            circulo.setLayoutY(circulo.getLayoutY() + diferencialy);

            Bounds limite = marco.getBoundsInLocal();
            boolean bordeDerecho = circulo.getLayoutX() >= (limite.getMaxX() - circulo.getRadius());
            boolean bordeIzquierdo = circulo.getLayoutX() <= (limite.getMaxX() + circulo.getRadius());
            boolean bordeInferior = circulo.getLayoutY() >= (limite.getMaxY() - circulo.getRadius());
            boolean bordeSuperior = circulo.getLayoutX() <= (limite.getMaxY() + circulo.getRadius());

            if(bordeDerecho || bordeIzquierdo){
                diferencialx *= -1;
            }
            if(bordeInferior || bordeSuperior){
                diferencialy *= -1;
            }
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jueguito.setCycleCount(Animation.INDEFINITE);
        jueguito.play();

        linea.setStartX(marco.getWidth() / 2);
        linea.setStartY(marco.getHeight() / 2);
        linea.setEndX(marco.getWidth() / 2);
        linea.setEndY(marco.getHeight() / 2 + 10);
        linea.setStrokeWidth(2);
        linea.setStroke(Color.BLACK);
        linea.setStrokeLineCap(StrokeLineCap.ROUND);
        linea.setVisible(true);
    }
}