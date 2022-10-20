package vn.dtbinh1511.sv22_dw_weather_group2.services;

import java.io.*;

public class FileServices {
    private FTPServices ftpServices;

    public FileServices() {
        ftpServices = new FTPServices();
    }

    private void creatFolder(String absolutePath) {
        File folder = new File(absolutePath);
        if (!folder.exists()) folder.mkdir();
    }

    private File createFile(String filePath) {
        return new File(filePath);
    }

    public String creatFolderAndFile(String folderPath) {
        DateServices format = new DateServices();
        creatFolder(folderPath);
        String filePath = folderPath.concat("/" + format.getFileName());
        File file = createFile(filePath);
        return filePath;
    }

    public boolean saveCSV(String path, String naturalKey, String province, String forecastDate, String description, String minTemp, String maxTemp, String humidity, String windSpeed, String sunrise, String sundown) {
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(naturalKey + "," + province + "," + forecastDate + "," + description + "," + minTemp + "," + maxTemp + "," + humidity + "," + windSpeed + "," + sunrise + "," + sundown);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String downloadCSV(String ip, String username, String password) {
        return null;
    }


    public boolean sendFileFTP(String ip, String username, String password, String fileLocalPath, String remoteFileName, String remoteFolderName) throws IOException {
        return ftpServices.sendFileFtp(ip, username, password, fileLocalPath, remoteFileName, "logs/" + remoteFolderName);
    }
}
