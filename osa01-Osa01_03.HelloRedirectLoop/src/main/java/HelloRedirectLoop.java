
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {
    
    // Max requests in redirect loop was 20
    static int requests = 0;

    public static void main(String[] args) throws Exception {
        int port = 8080;
        ServerSocket server = new ServerSocket(port);
        boolean run = true;
        while (run) {
            System.out.println(requests);
            Socket socket = server.accept();
            ++requests;
            Scanner scanner = new Scanner(socket.getInputStream());
            if (scanner.hasNextLine() && scanner.nextLine().contains("quit")) {
                run = false;
            }

            // kirjoitetaan vastaus
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("HTTP/1.1 302 Found");
            writer.println("Location: http://localhost:"+port);
            writer.flush();

            scanner.close();
            writer.close();
            socket.close();
        }
    }
}
