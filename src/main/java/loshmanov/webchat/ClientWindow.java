/**
 * ClientWindow.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// добавить функционал - сообщения отв сех клиентов и имена
public class ClientWindow extends JFrame {
    private final String serverHost;
    private final int serverPort;
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    private JTextArea serverMsgElement;

    private JTextField clientMsgElement;

    private Font fontMain;
    private Font fontButton;

    private String windowTitle;

    public ClientWindow(String host, int port) {
        serverHost = host;
        serverPort = port;

        initConnection();
        initGui();
        initServerListener();

    }

    private void initConnection() {
        try {
            socket = new Socket(serverHost, serverPort);
            out = new PrintWriter(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGui() {
        setBounds(600, 300, 1000, 600);
        setLocationRelativeTo(null);
        if (socket != null){
            String windowTitle = socket.getLocalAddress().toString().replaceFirst("/", "") + ":" + socket.getLocalPort();
        }
        else {
            windowTitle = "No connection";

        }
        setTitle(windowTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Image windowIcon = Toolkit.getDefaultToolkit().getImage("icon.png");
        //File fileName = new File(this.getClass().getClassLoader().getResource("/icon.png").getFile());

//        URL url1 = this.getClass().getResource("icon.png");
//        System.out.println(url1);
        setIconImage(windowIcon);

        serverMsgElement = new JTextArea();
        serverMsgElement.setEditable(false);
        serverMsgElement.setLineWrap(true);
        fontMain = new Font(Font.DIALOG_INPUT, Font.PLAIN, 22);

        serverMsgElement.setFont(fontMain);
        serverMsgElement.setBackground(new Color(43, 43, 43));
        serverMsgElement.setForeground(new Color(58, 151, 64));
        JScrollPane scrollPane = new JScrollPane(serverMsgElement);
        if (socket == null){
            serverMsgElement.setText("Couldn't connect to Rabbit Chat server. Try again later.");
        }
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        JButton sendButton = new JButton("SEND");
        sendButton.setBackground(new Color(76, 80, 82));
        sendButton.setForeground(new Color(58, 151, 64));
        fontButton = new Font("Arial", Font.PLAIN, 23);
        sendButton.setFont(fontButton);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        clientMsgElement = new JTextField();
        clientMsgElement.setFont(fontMain);
        clientMsgElement.setBackground(new Color(43, 43, 43));
        clientMsgElement.setForeground(new Color(58, 151, 64));
        clientMsgElement.setToolTipText("Enter your message to chat");

        clientMsgElement.setCaretColor(new Color(58, 151, 64));

        clientMsgElement.setBorder(BorderFactory.createCompoundBorder(clientMsgElement.getBorder(),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)));

        clientMsgElement.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(clientMsgElement.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        bottomPanel.add(clientMsgElement, BorderLayout.CENTER);

        sendButton.addActionListener(e -> {
            String message = clientMsgElement.getText();
            sendMessage(message);
            clientMsgElement.grabFocus();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });

        setVisible(true);
        clientMsgElement.grabFocus();

    }

    public void sendMessage(String message) {
        if (message.isEmpty()) {
            return;
        }
        out.println(message);
        out.flush();
        clientMsgElement.setText("");
    }

    public void closeConnection() {
        out.println("end");
        out.flush();
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initServerListener() {
        Thread serverListenerThread = new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        if (message.matches("^/q")) {
                            closeConnection();
                            break;
                        }
                        serverMsgElement.append(message + "\n");
                        clientMsgElement.grabFocus();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverListenerThread.start();

    }

}
