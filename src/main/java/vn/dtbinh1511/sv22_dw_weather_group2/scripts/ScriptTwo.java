package vn.dtbinh1511.sv22_dw_weather_group2.scripts;

import vn.dtbinh1511.sv22_dw_weather_group2.module.extract.ExtractData;

import java.io.IOException;

public class ScriptTwo {
    public static void main(String[] args) throws IOException {
        new ExtractData().loadFile();
    }
}
