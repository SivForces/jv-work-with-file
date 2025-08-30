package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String Supply = "supply";
        final String Buy = "buy";
        int supplyAmount = 0;
        int buyAmount = 0;

        File myFile = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(myFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }

        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                System.out.println("The line is empty");
                continue;
            }
            int commaIndex = line.indexOf(",");
            String operation = line.substring(0, commaIndex);
            String amountStr = line.substring(commaIndex + 1);
            int amount = Integer.parseInt(amountStr);
            if (operation.equals(Supply)) {
                supplyAmount += amount;
            } else if (operation.equals(Buy)) {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplyAmount + "\n");
            bufferedWriter.write("buy," + buyAmount + "\n");
            bufferedWriter.write("result," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
