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
        int result = 0;
        File myFile = new File(toFileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(myFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        List<String> lines;
        try {
            lines = Files.readAllLines(myFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        String strings = String.join(" ", lines);
        String[] split = strings.split(",");
        for (int i = 0; i < split.length; i++) {
            if(i%2 != 0){
                switch (split[i - 1]) {
                    case "buy" -> buyAmount += Integer.parseInt(split[i]);
                    case "supply" -> supplyAmount += Integer.parseInt(split[i]);
                }
            }
        }
        result = supplyAmount + buyAmount;
        try {
            bufferedWriter.write ("supply," + supplyAmount) ;
            bufferedWriter.write ("buy," + buyAmount) ;
            bufferedWriter.write ("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
