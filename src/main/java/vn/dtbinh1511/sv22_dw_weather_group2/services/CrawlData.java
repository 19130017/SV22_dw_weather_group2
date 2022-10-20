package vn.dtbinh1511.sv22_dw_weather_group2.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class CrawlData {
    private String url;
    private String path;

    public CrawlData(String url, String path) {
        this.url = url;
        this.path = path;
    }

    public boolean crawSourceOne() throws IOException {
        // connect website
        Connection con = Jsoup.connect(url).timeout(3000);
        Document doc = con.get();

        boolean success = true;
        FileServices fileServices = new FileServices();
        DateServices dateServices = new DateServices();
        //get link
        Elements elements = doc.select(".mega-submenu li a[href]");
        for (Element e : elements) {
            String newURL = url + e.attr("href") + "/7-ngay-toi";
            // connect content to craw
            con = Jsoup.connect(newURL);
            doc = con.get();

            //get data
            String dayCraw = dateServices.getDate();

            String province = doc.select(".location-name-main").text();
            elements = doc.select("details.weather-day");

            for (Element element : elements) {
                String forecastDate = element.select(".summary-day span").text().trim();
                if (forecastDate.length() == 7) {
                    forecastDate = dayCraw;
                } else {
                    int lastDelimited = forecastDate.lastIndexOf("/");
                    forecastDate = dateServices.getYear() + "-" + forecastDate.substring(lastDelimited + 1) + "-" + forecastDate.substring(lastDelimited - 2, lastDelimited);
                }

                String naturalKey = removeAccent(province + forecastDate.replace("-", ""));
                String description = element.select(".summary-description-detail").text().trim();
                String minTemp = element.select(".summary-temperature-min").text().trim().substring(0, 2);
                String maxTemp = element.select(".summary-temperature-max-value").text().trim().substring(0, 2);
                String humidity = element.select(".summary-humidity").text().trim().substring(0, 2);
                String windSpeed = element.select(".summary-speed").text().trim();
                windSpeed = windSpeed.substring(windSpeed.indexOf(" ") + 1, windSpeed.lastIndexOf(" "));

                String sunrise = element.select(".weather-content-item-sun .weather-sun span:first-child").text().trim();
                String sundown = element.select(".weather-content-item-sun .weather-sun span:last-child").text().trim();

                success = fileServices.saveCSV(path, naturalKey, province, forecastDate, description, minTemp, maxTemp, humidity, windSpeed, sunrise, sundown);
            }
        }
        return success;
    }

    private String removeAccent(String s) {
        s = s.replace(" ", "").toUpperCase();
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
