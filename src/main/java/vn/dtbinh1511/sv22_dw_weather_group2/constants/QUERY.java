package vn.dtbinh1511.sv22_dw_weather_group2.constants;

public class QUERY {
    public static class CONTROL {
        public static final String GET_CONFIG = "{call proc_get_config(?)}";
        public static final String GET_LOG = "CALL proc_get_log(?,?);";
        public static final String UPDATE_LOG = "CALL proc_update_log(?, ?);";
        public static final String CREATE_LOG = "CALL proc_add_log(?, ?, ?, ?, ?, ?);";
    }

    public static class STAGING {
        public static final String TRUNCATE_STAGING = "CALL proc_truncate_staging()";
        public static final String LOAD_FILE_STAGING = "LOAD DATA INFILE ? INTO TABLE staging FIELDS TERMINATED BY  ',' ENCLOSED BY '' LINES TERMINATED BY '\n';";
    }


}
