package fr.tommarx.gameengine.Net;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Socket {

    private java.net.Socket socket;
    private PrintWriter out;

    public Socket(String host, int port, SocketListener listener) {
        try {
            socket = new java.net.Socket(host, port);
            out = new PrintWriter(socket.getOutputStream());
            if (listener != null) {
                listener.onConnection(socket);
            }
            new SocketHandler(socket, listener, new Callable() {
                public Object call() throws Exception {
                    return null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message, String content) {
        out.println(message + "&&::!!" + content);
        out.flush();
    }

    public java.net.Socket getSocket() {
        return socket;
    }

}
