import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(900);
            System.out.println("Server ready");

            while (true) {
                Socket c = s.accept();
                System.out.println("Connection established");
                ClientHandler ch = new ClientHandler(c);
                Thread thread = new Thread(ch);
                	thread.start();
            }
         
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        
        public int notif() {
        	return 0;
        }
        public void run()  {
            try {
            	DataInputStream ios=null;
                while (true) {
                	ios = new DataInputStream(clientSocket.getInputStream());
                	Thread.sleep(1000);
                    String msg = ios.readUTF();
                    System.out.println("Message Received from:" + clientSocket.getRemoteSocketAddress()+"Message received: " + msg);
                    if (msg.equals("exit")) {
                        break;
                    }
                    System.out.println("Waiting");
                    
                }
                System.out.println("Completed");
                ios.close();              
                clientSocket.close();
                
            } catch (Exception e) {
                System.out.println("Error handling client: " + e);
            }
        }
    }
}
