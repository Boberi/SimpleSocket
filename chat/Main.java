package chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.simpleSimulating();
    }

    private void simpleSimulating() {
        ArrayList<String> chat = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("sent")) {
                System.out.println(line.replaceFirst(" sent", ":"));
            }
        }
    }
}

