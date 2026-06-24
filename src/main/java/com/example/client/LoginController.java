package com.example.client;

import com.example.Rmi.ServerRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private ServerRemote server;

    @FXML
    private TextField usernameField; // Certifique-se de que o id no FXML é "usernameField"

    public void setServer(ServerRemote server) {
        this.server = server;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        try {
            // Verifica no servidor RMI
            if (server.login(username)) { 
                System.out.println("Login bem-sucedido!");
                
                // Carrega a tela de Chat
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat.fxml"));
                Parent chatRoot = loader.load();
                
                // Passa o servidor para o novo controlador (ChatController)
                ChatController chatController = loader.getController();
                chatController.setServer(server);
                chatController.setUsername(username);
                
                // Troca a cena
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(chatRoot));
                stage.setTitle("WhatsUT - Chat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}