import java.util.*;
import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        try {
            Socket c = new Socket("localhost", 900);
            DataOutputStream dout = new DataOutputStream(c.getOutputStream());
            while (true) {
                System.out.println("Enter message:");
                String str = sc.nextLine();
                dout.writeUTF(str);
                dout.flush();
                if (str.equals("exit")) {
                    break; 
                }
            }
            System.out.println("Exited");
            dout.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        sc.close();
	}

}
