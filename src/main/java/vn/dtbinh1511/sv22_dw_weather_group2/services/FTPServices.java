package vn.dtbinh1511.sv22_dw_weather_group2.services;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPServices {

    public boolean downloadFileFtp(String ftpHost, String userName, String password,
                                   String localFilePath, String remoteFileName, String remoteFolderName) {

        return false;
    }

    public boolean sendFileFtp(String ftpHost, String userName, String password,
                               String localFilePath, String remoteFileName, String remoteFolderName) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(ftpHost);
        ftp.login(userName, password);
        System.out.println("Connected to " + ftpHost + ".");
//        System.out.print(ftp.getReplyString());
        int reply = ftp.getReplyCode();
        boolean result = false;

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            System.err.println("FTP server refused connection.");
            System.exit(1);
        }
        // create folder remote
        if (!checkDirectoryExists(ftp, remoteFolderName)) {
            createFolder(ftp, remoteFolderName);
        }

        // upload
        result = uploadSingleFile(ftp, localFilePath, remoteFileName);
        ftp.logout();
        System.out.println("Disconnected to " + ftpHost + ".");
        return result;
    }

    public boolean uploadSingleFile(FTPClient ftpClient, String localFilePath, String remoteFileName) {
        try {
            InputStream local = new FileInputStream(localFilePath);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFileName, local);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean createFolder(FTPClient ftpClient, String remoteFolder) throws IOException {

        return checkDirectoryExists(ftpClient, remoteFolder) ? true : ftpClient.makeDirectory(remoteFolder);
    }

    public boolean checkDirectoryExists(FTPClient ftpClient, String dirPath) throws IOException {
        ftpClient.changeWorkingDirectory(dirPath);
        int replyCode = ftpClient.getReplyCode();
        if (replyCode == 550) {
            return false;
        }
        return true;
    }

    public boolean checkFileExists(FTPClient ftpClient, String fileRemote) throws IOException {
        InputStream inputStream = ftpClient.retrieveFileStream(fileRemote);
        int returnCode = ftpClient.getReplyCode();
        if (inputStream == null || returnCode == 550) {
            return false;
        }
        return true;
    }

    public void downloadSingleFile(FTPClient ftpClient, String remoteFile, String downloadLocal) throws IOException {
//        String remoteFile2 = "/test/song.mp3";
//        File downloadFile2 = new File("D:/Downloads/song.mp3");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(downloadLocal));
        InputStream is = ftpClient.retrieveFileStream(remoteFile);
        byte[] bytesArray = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = is.read(bytesArray)) != -1) {
            os.write(bytesArray, 0, bytesRead);
        }

        os.close();
        is.close();
    }
}
