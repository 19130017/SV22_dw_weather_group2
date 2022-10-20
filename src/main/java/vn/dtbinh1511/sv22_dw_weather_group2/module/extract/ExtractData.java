package vn.dtbinh1511.sv22_dw_weather_group2.module.extract;

import vn.dtbinh1511.sv22_dw_weather_group2.constants.DBConstants;
import vn.dtbinh1511.sv22_dw_weather_group2.constants.Status;
import vn.dtbinh1511.sv22_dw_weather_group2.constants.StrConstants;
import vn.dtbinh1511.sv22_dw_weather_group2.dao.DAO;
import vn.dtbinh1511.sv22_dw_weather_group2.database.ConnectionMySql;
import vn.dtbinh1511.sv22_dw_weather_group2.entities.control.FileConfig;
import vn.dtbinh1511.sv22_dw_weather_group2.entities.control.FileLog;
import vn.dtbinh1511.sv22_dw_weather_group2.services.CrawlData;
import vn.dtbinh1511.sv22_dw_weather_group2.services.DateServices;
import vn.dtbinh1511.sv22_dw_weather_group2.services.FileServices;

import java.io.IOException;
import java.sql.Connection;

public class ExtractData {

    public void crawlData() throws IOException {
        // step 1: connect control database
        ConnectionMySql controlDB = new ConnectionMySql(DBConstants.CONTROL);

        // step 2: check connection
        Connection connection = controlDB.getConnectionDB();
        if (connection == null) {
            System.out.println("Access failed ");
            return;
        } else {
            // step 3: get config
            DAO dao = new DAO(connection);
            FileConfig config = dao.getConfig(StrConstants.SOURCE_NAME_1);

            // step 4: get log status "ES" and hourCrawl = now
            DateServices format = new DateServices();
            FileLog log = dao.getLog(format.getHour(), Status.EXTRACT_STARTING);

            if (log == null) {
                //step 5: create folder & file
                FileServices fileServices = new FileServices();
                String folderPath = StrConstants.ABS_PATH + format.getDateWithDelimited();
                String filePath = fileServices.creatFolderAndFile(folderPath);
                // step 6: addLog status "ES"
                dao.addLog(config.getId(), format.getFileName(), format.getDateCrawl(), format.getHour(), Status.EXTRACT_STARTING, StrConstants.AUTHOR);

                //step 7: crawl data
                CrawlData crawlData = new CrawlData(config.getSourcePath(), filePath);
                boolean success = crawlData.crawSourceOne();
                // step 8: check success
                if (success) {
                    // success -> step 9: send file ftp
                    fileServices.sendFileFTP(config.getIp(), config.getUsername(), config.getPassword(), filePath, format.getFileName(), format.getDateWithDelimited());

                    // step 10: update log
                    log = dao.getLog(format.getHour(), Status.EXTRACT_STARTING);
                    dao.updateLog(log.getId(), Status.EXTRACT_READY);
                } else {
                    // fail -> step 10: update log
                    dao.updateLog(log.getId(), Status.EXTRACT_NULL);
                    return;
                }
            } else {
                System.out.println("Extract Starting");
                return;
            }
        }
    }

    public void loadFile() {
        // step 1: connect control database
        ConnectionMySql controlDB = new ConnectionMySql(DBConstants.CONTROL);

        // step 2: check connection
        Connection connection = controlDB.getConnectionDB();
        if (connection == null) {
            System.out.println("Access failed ");
            return;
        } else {
            // step 3: get config
            DAO dao = new DAO(connection);
            FileConfig config = dao.getConfig(StrConstants.SOURCE_NAME_1);

            // step 4: get log status "ER" and hourCrawl = now
            DateServices format = new DateServices();
            FileLog log = dao.getLog(format.getHour(), Status.EXTRACT_READY);

            if (log != null) {
                // step 5: get file ftp
                FileServices fileServices = new FileServices();
                String filePath = fileServices.downloadCSV(config.getIp(), config.getUsername(), config.getPassword());
            } else {
                return;
            }
        }
    }
}
