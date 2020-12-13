import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        Socket server = new Socket("192.168.43.190", 8085);
        System.out.println("Connected to server");
        InputStream serverIS = server.getInputStream();
        BufferedInputStream serverBIS = new BufferedInputStream(serverIS);
        DataInputStream serverDIS = new DataInputStream(serverBIS);
        //receiveImage(server);
        int n = serverDIS.readInt();
        int[] imageLengths = new int[n];

        for (int i = 0; i < n; i++) {
            imageLengths[i] = (int) serverDIS.readLong();
            System.out.println(imageLengths[i]);
        }

        for (int i = 0; i < n; i++) {
            FileOutputStream receivedImageOS = new FileOutputStream(createFile());
            byte[] buffer = new byte[imageLengths[i]];
            int readByte = serverDIS.read(buffer, 0, imageLengths[i]);
            receivedImageOS.write(buffer);

            receivedImageOS.close();
        }
        serverIS.close();
    }


    private static File createFile() {
        return new File("C:\\Users\\Prosper's PC\\Pictures\\new" + System.currentTimeMillis() + ".jpg");
    }
}
