package chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Writer extends Thread {
    private final Socket socket;

    public Writer(Socket socketForClient) {
        this.socket = socketForClient;
    }

    @Override
    public void run() {
        try (DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in);
        ) {
            while (scanner.hasNext()) {
                String msg = scanner.nextLine();
                output.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
