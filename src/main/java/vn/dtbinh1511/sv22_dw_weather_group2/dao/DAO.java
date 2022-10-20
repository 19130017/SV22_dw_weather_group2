package vn.dtbinh1511.sv22_dw_weather_group2.dao;

import vn.dtbinh1511.sv22_dw_weather_group2.constants.QUERY;
import vn.dtbinh1511.sv22_dw_weather_group2.entities.control.FileConfig;
import vn.dtbinh1511.sv22_dw_weather_group2.entities.control.FileLog;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DAO {
    Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public FileConfig getConfig(String sourceName) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.GET_CONFIG);
            statement.setString(1, sourceName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new FileConfig(rs.getInt("id"),
                        rs.getString("sourceName"),
                        rs.getString("sourcePath"),
                        rs.getString("ip"),
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileLog getLog(int hour, String status) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.GET_LOG);
            statement.setInt(1, hour);
            statement.setString(2, status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new FileLog(rs.getInt("id"),
                        rs.getString("fileName"),
                        rs.getString("timeZone"),
                        rs.getDate("dateCrawl"),
                        rs.getInt("hourCrawl"),
                        rs.getString("status"),
                        rs.getString("author")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLog(int id, String fileName, Date dateCrawl, int hour, String status, String author) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.CREATE_LOG);
            statement.setInt(1, id);
            statement.setString(2, fileName);
            statement.setDate(3, new java.sql.Date(dateCrawl.getTime()));
            statement.setInt(4, hour);
            statement.setString(5, status);
            statement.setString(6, author);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLog(int id, String status) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.CONTROL.UPDATE_LOG);
            statement.setInt(1, id);
            statement.setString(2, status);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean truncateStaging() {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.TRUNCATE_STAGING);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadFileIntoStaging(String fileLocalPath) {
        try {
            CallableStatement statement = connection.prepareCall(QUERY.STAGING.LOAD_FILE_STAGING);
            statement.setString(1, fileLocalPath);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
