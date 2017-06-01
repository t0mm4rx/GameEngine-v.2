package fr.tommarx.gameengine.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler {

    BufferedReader reader;

    public SocketHandler(Socket socket, SocketListener listener) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String message = reader.readLine();
                        if (listener != null) {
                            if (message.split("&&::!!").length > 1) {
                                listener.onMessage(message.split("&&::!!")[0], message.split("&&::!!")[1], socket);
                            } else {
                                listener.onMessage(message.split("&&::!!")[0], "", socket);
                            }

                        }
                    } catch (IOException e) {
                        listener.onDisconnection(socket);
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
