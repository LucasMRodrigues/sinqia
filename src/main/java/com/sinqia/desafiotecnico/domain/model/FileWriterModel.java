package com.sinqia.desafiotecnico.domain.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterModel implements FileModifier {

    public void writeFile(SummaryResultModel results, File file) {

        File directory = this.checkIfDirectoryExists();

        File doneFile = new File(directory + File.separator + file.getName().substring(0, file.getName().indexOf(".")) + ".done.dat");

        try (FileWriter fw = new FileWriter(doneFile)) {

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Total de clientes: " + results.getStarterNumberOfClients() + "\n");
            bw.write("Total de vendedores: " + results.getStarterNumberOfSellers() + "\n");
            bw.write("Id da venda mais cara: " + results.getMostExpensiveSellId() + "\n");
            bw.write("Pior vendedor: " + results.getWorstSellerName() + "\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File checkIfDirectoryExists() {

        File directory = new File(String.valueOf(path));

        if (!directory.exists()) {
            directory.mkdirs();
        }

        return directory;
    }
}
