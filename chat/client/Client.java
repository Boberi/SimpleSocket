package chat.client;

import chat.Reader;
import chat.Writer;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    private final String address = "127.0.0.1";
    private final int port = 23456;

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.startClient();

    }

    private void startClient() {
        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            System.out.println("Client started!");
            Reader reader = new Reader(socket);
            Writer writer = new Writer(socket);
            reader.start();
            writer.start();
            reader.join(500);
            writer.join(500);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}


