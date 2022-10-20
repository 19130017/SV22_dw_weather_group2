package vn.dtbinh1511.sv22_dw_weather_group2.constants;

public class QUERY {
    public static class CONTROL {
        public static final String GET_CONFIG = "{call proc_get_config(?)}";
        public static final String FIND_ALL_LOG = "";
        public static final String GET_LOG = "CALL proc_get_log(?,?);";
        public static final String UPDATE_LOG = "CALL proc_update_log(?, ?);";
        public static final String CREATE_LOG = "CALL proc_add_log(?, ?, ?, ?, ?, ?);";
    }
}
