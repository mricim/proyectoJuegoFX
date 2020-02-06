package mapas.ciudad;

import javafx.stage.Stage;

public class PrimaryStageControlador {
    protected Stage stage;

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void cambiarNombreStage(String nuevoNombre) {
        if (nuevoNombre == null) {
            stage.setTitle("Nombre Aplicación");
        } else {
            stage.setTitle(nuevoNombre);
        }

    }
}
