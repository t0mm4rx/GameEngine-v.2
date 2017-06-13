package fr.tommarx.gameengine.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

public class SocketHandler {

    BufferedReader reader;
    Thread t1, t2;
    boolean running = true;

    public SocketHandler(final Socket socket, final SocketListener listener, final Callable onDisconnected) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        t1 = new Thread(new Runnable() {
            public void run() {
                while (running) {
                    try {
                        String message = reader.readLine();
                        if (listener != null) {
                            if (running && message != null) {
                                if (message.split("&&::!!").length > 1) {
                                    listener.onMessage(message.split("&&::!!")[0], message.split("&&::!!")[1], socket);
                                } else {
                                    listener.onMessage(message.split("&&::!!")[0], "", socket);
                                }
                            }

                        }
                    } catch (IOException e) {
                        listener.onDisconnection(socket);
                        stop();
                        try {
                            onDisconnected.call();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });
        t2 = new Thread(new Runnable() {
            public void run() {
                while (running) {
                    try {
                        if (reader.read() == -1) {
                            listener.onDisconnection(socket);
                            stop();
                            try {
                                onDisconnected.call();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    private void stop() {
        t1.interrupt();
        t2.interrupt();
        running = false;
    }

}
