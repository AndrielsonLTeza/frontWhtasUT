package com.example.client;

import com.example.Rmi.ServerRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    private ServerRemote server;

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    public void setServer(ServerRemote server) { this.server = server; }

    @FXML
    private void handleLogin() {
        try {
            // 1. Instancia o callback que criamos (ChatClient)
            ChatClient clientCallback = new ChatClient();
            
            // 2. Passa o callback para o método login
            boolean success = server.login(userField.getText(), passField.getText(), clientCallback);
            
            if (success) {
                // 3. Carrega a janela do Chat
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat.fxml"));
                Parent root = loader.load();

                // 4. Configura o ChatController
                ChatController controller = loader.getController();
                controller.setServer(server);
                controller.setClientCallback(clientCallback); 

                // 5. Exibe a janela
                Stage stage = new Stage();
                stage.setTitle("WhatsUT - " + userField.getText());
                stage.setScene(new Scene(root));
                stage.show();

                // 6. Fecha o Login
                ((Stage) userField.getScene().getWindow()).close();
            } else {
                System.out.println("Login falhou: verifique usuário ou senha.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}