
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        System.out.println("Give address:");
        Scanner input = new Scanner(System.in);
        String address = input.nextLine();

        try (Socket socket = new Socket(address, 80)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("GET / HTTP/1.1");
            writer.println("Host: " + address);
            writer.println();
            writer.flush();

            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        }
    }
}
