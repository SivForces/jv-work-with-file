package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
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
            int bruh = line.indexOf(",");
            String operation = line.substring(0, bruh);
            String amountStr = line.substring(bruh + 1);
            int amount = Integer.parseInt(amountStr);
            if(operation.equals("supply")) {
                supplyAmount += amount;
            } else if (operation.equals("buy")) {
                buyAmount += amount;
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(myFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int result = supplyAmount - buyAmount;
        try {
            bufferedWriter.write("supply," + supplyAmount);
            bufferedWriter.write("buy," + buyAmount);
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
