package vn.dtbinh1511.sv22_dw_weather_group2.entities.control;

public class FileConfig {
    private int id;
    private String sourceName;
    private String sourcePath;
    private String ip;
    private String username;
    private String password;

    public FileConfig(int id, String sourceName, String sourcePath, String ip, String username, String password) {
        this.id = id;
        this.sourceName = sourceName;
        this.sourcePath = sourcePath;
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "FileConfig{" +
                "id=" + id +
                ", sourceName='" + sourceName +
                ", sourcePath='" + sourcePath +
                ", ip='" + ip +
                ", username='" + username +
                ", password='" + password +
                '}';
    }
}
