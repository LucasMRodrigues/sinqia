package com.sinqia.desafiotecnico.service;

import com.sinqia.desafiotecnico.domain.model.FileReaderModel;
import com.sinqia.desafiotecnico.domain.model.FileWriterModel;
import com.sinqia.desafiotecnico.domain.model.SummaryResultModel;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.Objects;

import static com.sinqia.desafiotecnico.domain.constant.ApiConstant.*;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class DirectoryWatcherService {

    private Path path = FOLDER_PATH_IN;

    public void watchDirectoryFolder() {

        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println("Observando o diretório: " + path);

        FileSystem fs = path.getFileSystem();

        try (WatchService service = fs.newWatchService()) {

            path.register(service, ENTRY_CREATE);
            System.out.println(path.getFileName());

            WatchKey key;

            while (true) {

                key = service.take();

                Kind<?> kind;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {

                    kind = watchEvent.kind();

                    if (ENTRY_CREATE == kind) {

                        Path newPath = ((WatchEvent<Path>) watchEvent)
                                .context();
                        System.out.println("Novo arquivo encontrado no diretório!");
                        System.out.println(newPath);

                        File dir = new File(String.valueOf(path));

                        File[] matches = dir.listFiles(new FilenameFilter()
                        {
                            public boolean accept(File dir, String name)
                            {
                                return name.endsWith(".dat");
                            }
                        });

                        FileReaderModel fileReader = new FileReaderModel();
                        File file = new File(matches[0].getAbsolutePath());
                        SummaryResultModel results = fileReader.extractResult(file);

                        if (Objects.nonNull(results)) {
                            FileWriterModel fileWriter = new FileWriterModel();
                            fileWriter.writeFile(results, file);
                        }
                    }

                    if (!key.reset()) {
                        break;
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
