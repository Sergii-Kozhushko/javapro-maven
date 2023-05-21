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
    private String name;
    private String clientAddress;

    public ClientHandler(Socket s, Server server) {
        this.server = server;
        try {
            this.s = s;
            out = new PrintWriter(s.getOutputStream());
            in = new Scanner(s.getInputStream());
            CLIENTS_COUNT++;
            clientAddress = s.getLocalAddress().toString().replaceFirst("/", "") + ":" + s.getPort();
            name = "User #" + CLIENTS_COUNT;
            System.out.println("UL.size=" + server.getUserList().size());
            String newUserMessage = String.format("New User connected: %s (%s)", name, clientAddress);
            sendBroadcastMessage(newUserMessage, 1);
            sendLocalMessage("New User connected: " + name, 1);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendLocalMessage(String message, int timeFormat) {
        String time;
        switch (timeFormat) {
            case (1) -> time = DateTimeFormatter.ofPattern("dd-MM-yy, HH:mm:ss")
                    .format(LocalDateTime.now());
            case (2) -> time = DateTimeFormatter.ofPattern("HH:mm:ss")
                    .format(LocalDateTime.now());
            default -> time = "";
        }

        out.println(time + " " + message);
        out.flush();
    }

    public void sendBroadcastMessage(String message, int timeFormat) {
        for (ClientHandler c : server.getUserList()) {
            c.sendLocalMessage(message, timeFormat);
        }
    }


    @Override
    public void run() {
        while (true) {
            if (in.hasNext()) {
                String message = in.nextLine();
                System.out.println(name + ": " + message);
                //out.println(name + " : " + message);
                System.out.println("Users count=" + server.getUserList().size());
                sendBroadcastMessage(name + " : " + message, 2);
                if (message.equals("end")) {
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
