package cardpay.convert.service;

import cardpay.convert.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class CsvOrderReader implements OrderReader {
    @Override
    public List<Order> readFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            return IntStream.range(0, lines.size()).mapToObj( i -> {
                String line = lines.get(i);
                Order order = this.parseLine(line);
                order.setFilename(filename);
                order.setLine(i + 1);
                return order;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            return Arrays.asList(Order.error(e.getMessage()));
        }
    }

    @Override
    public Order parseLine(String line) {
        Order result = new Order();
        try {
            String[] split = line.split(",");
            if (split.length != 4) {
                result.setResult("Wrong format: " + line);
            }

            result.setId(Integer.parseInt(split[0]));
            result.setAmount(Double.parseDouble(split[1]));
            result.setCurrency((split[2]));
            result.setComment((split[3]));
            result.setResult("OK");
        } catch (Exception e) {
            result.setResult(e.getMessage());
        }
        return result;
    }
}
