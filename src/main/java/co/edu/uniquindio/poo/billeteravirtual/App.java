package co.edu.uniquindio.poo.billeteravirtual;

import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.viewController.AdministradorViewController;
import co.edu.uniquindio.poo.billeteravirtual.viewController.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;
    @SuppressWarnings("exports")
    public static BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        openLogin();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openLogin() {
        inicializarData();
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(App.class.getResource("Login.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader1.load(); // Cambiado de VBox a AnchorPane

            LoginViewController loginViewController = loader1.getController();
            loginViewController.setApp(this);

            Scene scene1 = new Scene(rootLayout);
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar Login.fxml");
            e.printStackTrace();
        }
    }

    public void openAdministrador() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(App.class.getResource("Administrador.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader2.load();
            AdministradorViewController administradorViewController = loader2.getController();
            administradorViewController.setApp(this);

            Scene scene2 = new Scene(rootLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void inicializarData() {

    }
}
