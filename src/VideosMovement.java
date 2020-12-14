import java.io.*;
import java.net.Socket;

public class VideosMovement {

    void receiveVideo(Socket server) throws IOException {
        InputStream serverIS = server.getInputStream();
        BufferedInputStream serverBIS = new BufferedInputStream(serverIS);
        DataInputStream serverDIS = new DataInputStream(serverBIS);

        // read the number of video file sent
        int totalFiles = serverDIS.readInt();

        int[] videosSize = new int[totalFiles];
        String[] videosName = new String[totalFiles];

        // read the names and length for each video
        for (int i = 0; i < totalFiles; i++) {
            videosSize[i] = (int) serverDIS.readLong();
            videosName[i] = serverDIS.readUTF();
        }

        // read the bytes for each video
        for (int i = 0; i < totalFiles; i++) {
            byte[] buffer = new byte[videosSize[i]];
            serverDIS.read(buffer, 0, videosSize[i]);
            FileOutputStream receivedVideo = new FileOutputStream(createFile(videosName[i]));
            receivedVideo.write(buffer);
            receivedVideo.close();
        }
        System.out.println(String.format("%d videos received", totalFiles));

    }

    void transferVideo(File[] videoCollection, Socket clientSocket) throws IOException {
        OutputStream clientSocketOS = clientSocket.getOutputStream();
        BufferedOutputStream clientSocketBOS = new BufferedOutputStream(clientSocketOS);
        DataOutputStream clientSocketDOS = new DataOutputStream(clientSocketBOS);

        //write the amount of video to transfer
        clientSocketDOS.writeInt(videoCollection.length);

        // write the length and name of each video to the clientSocket DataOutputStream
        for(int i = 0; i < videoCollection.length; i++){
            clientSocketDOS.writeLong(videoCollection[i].length());
            clientSocketDOS.writeUTF(videoCollection[i].getName());
        }

        // write the bytes of each video to the clientSocket DataOutputStream
        for(int i = 0; i < videoCollection.length; i++){
            FileInputStream videoFileInputStream = new FileInputStream(videoCollection[i]);
            byte[] buffer = videoFileInputStream.readAllBytes();
            clientSocketDOS.write(buffer);
            videoFileInputStream.close();
        }

        clientSocketDOS.close();
        System.out.println("Done");
    }


    private static File createFile(String videoName) {
        return new File("C:\\Users\\Prosper's PC\\Pictures\\" + videoName + ".mp4");
    }
}
