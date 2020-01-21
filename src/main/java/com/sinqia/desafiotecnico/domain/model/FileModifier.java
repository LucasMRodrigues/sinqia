package com.sinqia.desafiotecnico.domain.model;

import java.nio.file.Path;
import java.util.List;

import static com.sinqia.desafiotecnico.domain.constant.ApiConstant.*;

public interface FileModifier {

    Path path = FOLDER_PATH_OUT;
    List<String> allowedFileExtensionList = List.of("dat", "txt");
}
