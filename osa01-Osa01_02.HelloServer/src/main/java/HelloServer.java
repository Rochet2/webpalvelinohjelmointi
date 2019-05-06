
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        boolean run = true;
        while (run) {
            Socket socket = server.accept();
            Scanner scanner = new Scanner(socket.getInputStream());
            if (scanner.hasNextLine() && scanner.nextLine().contains("quit")) {
                run = false;
            } else {
            }
            
            System.out.println("shitt");

            // kirjoitetaan vastaus
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("HTTP/1.1 200 OK");
            writer.println("");
            Scanner sc = new Scanner(new File("index.html"));
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                writer.println(s);
                System.out.println(s);
            }
            writer.flush();
            sc.close();

            scanner.close();
            writer.close();
            socket.close();
        }
    }
}
