/**
 * ClientWindow.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import javax.swing.*;
import java.awt.*;
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
      setBounds(600, 300, 500, 500);
      setTitle("Client window");
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      serverMsgElement = new JTextArea();
      serverMsgElement.setEditable(true);
      serverMsgElement.setLineWrap(true);
      JScrollPane scrollPane = new JScrollPane(serverMsgElement);
      add(scrollPane, BorderLayout.CENTER);

      JPanel bottomPanel = new JPanel(new BorderLayout());
      add(bottomPanel, BorderLayout.SOUTH);

      JButton sendButton = new JButton("SEND");
      bottomPanel.add(sendButton, BorderLayout.EAST);

      clientMsgElement = new JTextField();
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


   }
   public void sendMessage(String message){
      if (message.isEmpty()){
         return;
      }
      out.println(message);
      out.flush();
      clientMsgElement.setText("");

   }

   public void closeConnection(){
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

   public void initServerListener(){
      new Thread(() -> {
         try {
            while (true) {
               if (in.hasNext()) {
                  String message = in.nextLine();
                  serverMsgElement.append(message + "\n");
               }
            }
         } catch (Exception e){
            e.printStackTrace();
         }
      }).start();

   }

}
