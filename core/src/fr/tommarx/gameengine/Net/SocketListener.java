package fr.tommarx.gameengine.Net;

import java.net.Socket;

public interface SocketListener {

    void onMessage(String message, String content, Socket socket);

    void onConnection(Socket socket);

    void onDisconnection(Socket socket);

}
