module com.example.physicsdelta {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.example.physicsdelta to javafx.fxml;
  exports com.example.physicsdelta;
}