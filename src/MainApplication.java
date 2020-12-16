import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        Runnable runnable = () -> {
            Socket server = null;
            try {
                server = new Socket("192.168.43.190", 8085);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Connected to server");

            VideosMovement videosMovement = new VideosMovement();
            // videosMovement.receiveVideo(server);

            File video1 = getVideoFile("148 Introduction to the DOM");
            File video2 = getVideoFile("149 Defining the DOM");
            File video3 = getVideoFile("150 Select and Manipulate");
            File video4 = getVideoFile("152 Important Selector Methods");
            File[] videoCollection = { video2, video1, video3, video4};

            try {
               // videosMovement.transferVideo(videoCollection,server);
                videosMovement.receiveVideo(server);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }


    private static File getVideoFile(String name){
        return new File("C:\\Users\\Prosper's PC\\Desktop\\the web developer bootcamp\\13 DOM Manipulation\\" + name +".mp4");
    }
}
