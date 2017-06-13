package fr.tommarx.gameengine.Net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ServerSocket {

    private java.net.ServerSocket socket;
    private Thread t;
    private ArrayList<Socket> clients;

    public ServerSocket(int port, final SocketListener listener) {
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
                        final Socket s = socket.accept();
                        clients.add(s);
                        new SocketHandler(s, listener, new Callable() {
                            public Object call() throws Exception {
                                clients.remove(s);
                                return null;
                            }
                        });
                        if (listener != null) {
                            listener.onConnection(s);
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
