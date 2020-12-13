import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        Socket server = new Socket("192.168.43.190", 8085);
        System.out.println("Connected to server");

        VideosMovement videosMovement = new VideosMovement();
        videosMovement.receiveVideo(server);

    }


    private static File createFile() {
        return new File("C:\\Users\\Prosper's PC\\Pictures\\new" + System.currentTimeMillis() + ".jpg");
    }
}
