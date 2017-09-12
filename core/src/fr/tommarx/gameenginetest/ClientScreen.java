package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Net.Socket;
import fr.tommarx.gameengine.Net.SocketListener;

public class ClientScreen extends Screen {

    Socket socket;

    public ClientScreen(Game game) {
        super(game);
        socket = new Socket("127.0.0.1", 8080, new SocketListener() {
            public void onMessage(String message, String content, java.net.Socket s) {
                if (message.equals("Hello")) {
                    System.out.println("A registration has been sent");
                    socket.send("Name", "Tom");
                }
            }

            public void onConnection(java.net.Socket socket) {
                System.out.println("Connected to server");
            }

            public void onDisconnection(java.net.Socket socket) {

            }
        });
    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void update() {

    }

    public void show() {

    }
}
