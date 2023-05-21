/**
 * ClientHandler.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket s;

    private Server server;
    private PrintWriter out;
    private Scanner in;
    private static int CLIENTS_COUNT = 0;
    private String chatName;
    private String clientAddress;

    public ClientHandler(Socket s, Server server) {
        this.server = server;
        try {
            this.s = s;
            out = new PrintWriter(s.getOutputStream());
            in = new Scanner(s.getInputStream());
            CLIENTS_COUNT++;
            clientAddress = s.getLocalAddress().toString().replaceFirst("/", "") + ":" + s.getPort();
            chatName = "User #" + CLIENTS_COUNT;

            String newUserMessage = String.format("New User connected: <%s> (%s)", chatName, clientAddress);
            sendBroadcastMessage("", newUserMessage, 1);
            String localWelcomeMessage =
                    "\n" +
                            "  _____       _     _     _ _      _____ _           _   \n" +
                            " |  __ \\     | |   | |   (_) |    / ____| |         | |  \n" +
                            " | |__) |__ _| |__ | |__  _| |_  | |    | |__   __ _| |_ \n" +
                            " |  _  // _` | '_ \\| '_ \\| | __| | |    | '_ \\ / _` | __|\n" +
                            " | | \\ \\ (_| | |_) | |_) | | |_  | |____| | | | (_| | |_ \n" +
                            " |_|  \\_\\__,_|_.__/|_.__/|_|\\__|  \\_____|_| |_|\\__,_|\\__|\n" +
                            "                                                         \n" +

                            "Welcome to Rabbit chat! Type /h for list of commands.";
            sendLocalMessage("", localWelcomeMessage, 3);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendLocalMessage(String name, String message, int timeFormat) {
        String time;
        switch (timeFormat) {
            case (1) -> time = DateTimeFormatter.ofPattern("dd-MM-yy, HH:mm:ss")
                    .format(LocalDateTime.now());
            case (2) -> time = DateTimeFormatter.ofPattern("HH:mm:ss")
                    .format(LocalDateTime.now());
            default -> time = "";
        }
        String nameOut = name.length() > 0 ? "<" + name + ">" : "";
        String finalMessage = name.length() == 0 ? message : time + " " + nameOut + " " + message;

        out.println(finalMessage);
        out.flush();
    }

    public void sendLocalSystemMessage(String message) {
        sendLocalMessage("", message, 3);
    }

    public void sendBroadcastMessage(String name, String message, int timeFormat) {
        for (ClientHandler c : server.getUserList()) {
            c.sendLocalMessage(name, message, timeFormat);
        }
    }


    @Override
    public void run() {
        while (true) {
            if (in.hasNext()) {
                String message = in.nextLine();
                System.out.println(chatName + ": " + message);

                if (message.matches("^/h")) {
                    message = "List of commands: \n" +
                            "/n: change name \n" +
                            "/q: quit \n" +
                            "/h: help";
                    sendLocalSystemMessage(message);
                } else {
                    if (message.matches("^/n .*")) {
                        String newName = message.substring(3, message.length());
                        if (newName.length() > 0) {
                            message = "User '" + chatName + "' (" + clientAddress + ") changed name to " + newName;
                            chatName = newName;
                        }
                    }
                    sendBroadcastMessage(chatName, message, 2);
                }
                if (message.matches("^/q")) {
                    break;
                }
            }
        }
        try {
            System.out.println("Клиент отключился");
            in.close();
            out.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
