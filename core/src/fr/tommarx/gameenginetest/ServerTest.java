package fr.tommarx.gameenginetest;

import java.net.Socket;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Net.ServerSocket;
import fr.tommarx.gameengine.Net.SocketListener;

public class ServerTest extends Screen{

    ServerSocket server;

    public ServerTest(Game game) {
        super(game);
        server = new ServerSocket(8080, new SocketListener() {
            public void onMessage(String message, String content, java.net.Socket s) {
                if (message.equals("Name")) {
                    System.out.println("New player connected : " + content);
                }
            }

            public void onConnection(Socket socket) {
                System.out.println("New client : " + socket.getInetAddress().getHostAddress());
                server.send("Hello", "", socket);
            }

            public void onDisconnection(Socket socket) {

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
