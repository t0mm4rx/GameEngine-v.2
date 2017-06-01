package fr.tommarx.gameengine.Net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocket {

    private java.net.ServerSocket socket;
    private ArrayList<Socket> clients;
    private Thread t;

    public ServerSocket(int port, SocketListener listener) {
        try {
            socket = new java.net.ServerSocket(port);
            clients = new ArrayList<Socket>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        clients.add(socket.accept());
                        new SocketHandler(clients.get(clients.size() - 1), listener);
                        if (listener != null) {
                            listener.onConnection(clients.get(clients.size() - 1));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public void send(String message, String content, Socket socket) {
        try {
            PrintWriter a = new PrintWriter(socket.getOutputStream());
            a.println(message + "&&::!!" + content);
            a.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, String content) {
        for (Socket c : clients) {
            send(message, content, c);
        }
    }

    public java.net.ServerSocket getSocket() {
        return socket;
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }


}
