package vn.dtbinh1511.sv22_dw_weather_group2.entities.control;

import java.util.Date;

public class FileLog {
    private int id;
    private String fileName;
    private String timeZone;
    private Date dateCrawl;
    private int hourCrawl;
    private String status;
    private String author;

    public FileLog(int id, String fileName, String timeZone, Date dateCrawl, int hourCrawl, String status, String author) {
        this.id = id;
        this.fileName = fileName;
        this.timeZone = timeZone;
        this.dateCrawl = dateCrawl;
        this.hourCrawl = hourCrawl;
        this.status = status;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Date getDateCrawl() {
        return dateCrawl;
    }

    public void setDateCrawl(Date dateCrawl) {
        this.dateCrawl = dateCrawl;
    }

    public int getHourCrawl() {
        return hourCrawl;
    }

    public void setHourCrawl(int hourCrawl) {
        this.hourCrawl = hourCrawl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "FileLog{" +
                "id=" + id +
                ", fileName='" + fileName +
                ", timeZone='" + timeZone +
                ", dateCrawl=" + dateCrawl +
                ", hourCrawl=" + hourCrawl +
                ", status='" + status +
                ", author='" + author +
                '}';
    }
}
