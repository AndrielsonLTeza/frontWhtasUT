package com.example.client;

import com.example.Rmi.ServerRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.rmi.Naming;

public class WhatsUTClientApp extends Application {
    private static ServerRemote server;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Conecta ao servidor RMI
        server = (ServerRemote) Naming.lookup("rmi://localhost:1099/WhatsUT");

        // 2. Carrega o FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        
        Parent root = loader.load();
        
        // 3. Cria a cena com o root carregado
        Scene scene = new Scene(root, 400, 300);
        
        // 4. Aplica o CSS (se o arquivo existir no seu resources)
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: style.css não encontrado, ignorando estilo.");
        }

        // 5. Passa a referência do servidor para o controlador
        LoginController controller = loader.getController();
        controller.setServer(server);

        // 6. Configura a janela
        primaryStage.setTitle("WhatsUT - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}