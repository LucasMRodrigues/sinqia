package com.sinqia.desafiotecnico.domain.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ApiConstant {

    public static final Path FOLDER_PATH_IN = Paths.get(System.getProperty("os.name").toLowerCase().contains("windows") ?
            "%HOMEPATH%\\data\\in" : "/Users/" + System.getProperty("user.name") + "/Downloads/Teste/data/in");
    public static final Path FOLDER_PATH_OUT = Paths.get(System.getProperty("os.name").toLowerCase().contains("windows") ?
            "%HOMEPATH%\\data\\out" : "/Users/" + System.getProperty("user.name") + "/Downloads/Teste/data/out");
    public  static final String SEPARATOR_PATTERN = "ç";
}
