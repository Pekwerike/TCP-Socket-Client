import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class VideosMovement {

    void receiveVideo(Socket server) throws IOException {
        InputStream serverIS = server.getInputStream();
        BufferedInputStream serverBIS = new BufferedInputStream(serverIS);
        DataInputStream serverDIS = new DataInputStream(serverBIS);

        // read the number of video file sent
        int totalFiles = serverDIS.readInt();

        long[] videosSize = new long[totalFiles];
        String[] videosName = new String[totalFiles];

        // read the names and length for each file
        for(int i = 0; i < totalFiles; i++){

        }
    }
}
