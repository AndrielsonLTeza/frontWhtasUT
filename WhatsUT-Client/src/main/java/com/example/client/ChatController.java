package com.example.client;

import com.example.Models.Message;
import com.example.Models.TextMessage;
import com.example.Rmi.ServerRemote;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.rmi.RemoteException;

public class ChatController {

    @FXML private ListView<String> messageListView;
    @FXML private TextField messageField;

    private ServerRemote server;
    private ChatClient clientCallback;
    private String currentUser;

    public void setServer(ServerRemote server) { this.server = server; }

    public void setClientCallback(ChatClient clientCallback) { 
        this.clientCallback = clientCallback; 
        this.clientCallback.setController(this); 
    }

    public void setCurrentUser(String user) { this.currentUser = user; }

    // Métodos chamados pelo ChatClient (via Platform.runLater)
    public void appendMessage(String groupName, Message message) {
        String sender = message.getSender().GetName();
        String content = (message instanceof TextMessage) ? ((TextMessage) message).getContent() : "[Arquivo]";
        messageListView.getItems().add(String.format("[%s] %s: %s", groupName, sender, content));
    }

    public void appendPrivateMessage(String senderName, Message message) {
        String content = (message instanceof TextMessage) ? ((TextMessage) message).getContent() : "[Arquivo]";
        messageListView.getItems().add(String.format("[Privada de %s]: %s", senderName, content));
    }

    public void refreshGroupList() {
        // Implementar lógica de atualização da lista de grupos
        System.out.println("Atualizando lista de grupos...");
    }

    public void refreshRequest(String groupName, String userName) {
        System.out.println("Novo pedido no grupo " + groupName + " de " + userName);
    }

    @FXML
    private void handleSendMessage() {
        String text = messageField.getText();
        if (text != null && !text.isEmpty()) {
            try {
                // Aqui você deve decidir para onde enviar (Grupo ou Privado)
                // Exemplo: server.sendGroupTextMessage("NomeDoGrupo", currentUser, new TextMessage(text, ...));
                messageField.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}