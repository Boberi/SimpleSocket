package chat.server;

import chat.Reader;
import chat.Writer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private final String address = "127.0.0.1";
    private final int port = 23456;

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.run();
        Thread.sleep(100);
    }

    private void run() {
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));) {
            System.out.println("Server started!");
            while (true) {
                Session session = new Session(server.accept());
                session.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Session extends Thread {
    private final Socket socket;

    public Session(Socket socketForClient) {
        this.socket = socketForClient;
    }

    @Override
    public void run() {
        try {
            Reader reader = new Reader(socket);
            reader.start();
            Writer writer = new Writer(socket);
            writer.start();
            reader.join(500);
            writer.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
