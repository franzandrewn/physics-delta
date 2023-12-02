package com.example.physicsdelta;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class BodyController {
  @FXML
  public TextField v0Field;
  @FXML
  public TextField mField;
  @FXML
  public TextField nuField;
  @FXML
  public TextField gField;


  // start params
  private double v0 = 10;
  private final double x0 = 0;
  private double m = 1;
  private double coef = 0.4;
  private final double g = 9.8;
  private double a = coef * m * g;
  private final double t0 = 0;
  private final int deltaT = 10;

  // variable params
  private double t = t0;
  private double v = v0;
  private double x = x0;

  @FXML
  public Label varParams;
  @FXML
  public Rectangle body;

  @FXML
  public void start() {
    v0Field.setDisable(true);
    mField.setDisable(true);
    nuField.setDisable(true);
    v0 = Double.parseDouble(v0Field.getText());
    m = Double.parseDouble(mField.getText());
    coef = Double.parseDouble(nuField.getText());
    a = coef * m * g;
    System.out.printf("a: %.3f\n", a);
    new Thread(() -> {
      while (v > 0) {
        try {
          Thread.sleep(deltaT);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        t += deltaT / 1000.0;
        v = v0 - a * t;
        x = v0 * t - (a * t * t) / 2;
        if (v < 0) {
          x = (v0 * v0) / (2 * coef * g);
          v = 0.0;
        }
        System.out.printf("t: %.3f\t\tv: %.3f\t\tx: %.3f\n", t, v, x);
        Platform.runLater(this::refreshBodyAndInfo);
      }
    }).start();
  }

  public void reset() {
    v0Field.setDisable(false);
    mField.setDisable(false);
    nuField.setDisable(false);
    v = v0;
    x = x0;
    t = t0;
    Platform.runLater(this::refreshBodyAndInfo);
  }

  private void refreshBodyAndInfo() {
    body.setX(x*5);
    varParams.setText(String.format("v = %.3f, x = %.3f", v, x));
  }
}