package com.sinqia.desafiotecnico.domain.model;

import com.sinqia.desafiotecnico.factory.SaleFactory;
import lombok.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.sinqia.desafiotecnico.domain.constant.ApiConstant.SEPARATOR_PATTERN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileReaderModel implements FileModifier {

    private File file;
    private SummaryResultModel readResult;
    private String separatorPattern = SEPARATOR_PATTERN;

    public Boolean shouldRead(File file) {
        return allowedFileExtensionList
                .stream()
                .filter(Objects::nonNull)
                .anyMatch(extension -> this.hasSameExtension(extension, file));
    }

    private Boolean hasSameExtension(String extension, File file) {
        return FilenameUtils.getExtension(file.getAbsolutePath()).equalsIgnoreCase(extension);
    }

    public SummaryResultModel extractResult(File file) throws IOException {

        if (!shouldRead(file)) return null;


        List<String> saleList = new ArrayList<>();
//        String s = readFile(file);
        Scanner scan = new Scanner(file);
        readResult = new SummaryResultModel(0L, 0L, 0L, null);

        do {
            var data = scan.nextLine();

            if (data.contains("001" + separatorPattern)) {
                readResult.setStarterNumberOfSellers(readResult.increaseNumberOfSellers());
            } else if (data.contains("002" + separatorPattern)) {
                readResult.setStarterNumberOfClients(readResult.increaseNumberOfClients());
            } else {
                saleList.add(data);
            }

        } while (scan.hasNextLine());

        var sortedSellList = this.sortSaleListByTheMostExpensive(saleList, separatorPattern);

        readResult.setMostExpensiveSellId(sortedSellList.get(0).getId());
        readResult.setWorstSellerName(sortedSellList.get(sortedSellList.size() - 1).getSalesmanName());

        return readResult;
    }

    private List<SaleModel> sortSaleListByTheMostExpensive(List<String> saleList, String separatorPattern) {
        return saleList
                .stream()
                .filter(Objects::nonNull)
                .map(saleString -> SaleFactory.mapToModel(saleString, separatorPattern))
                .sorted(Comparator.comparing(SaleModel::getTotalValue).reversed())
                .collect(Collectors.toList());
    }
}
