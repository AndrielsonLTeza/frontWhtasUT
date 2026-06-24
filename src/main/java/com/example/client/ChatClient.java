package com.example.client;

import com.example.Rmi.ClientRemote;
import com.example.Models.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;

public class ChatClient extends UnicastRemoteObject implements ClientRemote {
    
    private ChatController controller; // Referência para a tela

    protected ChatClient() throws RemoteException {
        super();
    }

    // Método para conectar o controlador assim que a tela abrir
    public void setController(ChatController controller) {
        this.controller = controller;
    }

    @Override
    public void refreshMessage(String groupName, Message message) throws RemoteException {
        if (controller != null) {
            // Platform.runLater garante que a atualização ocorra na thread da UI
            Platform.runLater(() -> controller.appendMessage(groupName, message));
        }
    }

    // Implemente os outros métodos refresh... da mesma forma
    @Override
    public void refreshGroups() throws RemoteException {
        Platform.runLater(() -> controller.refreshGroupList());
    }

    @Override
    public void refreshRequest(String groupName, String userName) throws RemoteException {
        if (controller != null) {
            Platform.runLater(() -> controller.refreshRequest(groupName, userName));
        }
    }

    @Override
    public void refreshPrivateMessage(String senderName, Message message) throws RemoteException {
        if (controller != null) {
            Platform.runLater(() -> controller.appendPrivateMessage(senderName, message));
        }
    }
}