package cardpay.convert.service;

import cardpay.convert.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class OrderReader {

    public List<Order> readFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            return IntStream.range(0, lines.size()).mapToObj(i -> {
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

    abstract Order parseLine(String line);
}
