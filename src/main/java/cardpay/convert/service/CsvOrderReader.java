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
public class CsvOrderReader extends OrderReader {

    @Override
    public Order parseLine(String line) {
        Order order = new Order();
        try {
            String[] split = line.split(",");
            if (split.length != 4) {
                order.setResult("Wrong format: " + line);
            }

            order.setId(Integer.parseInt(split[0]));
            order.setAmount(Double.parseDouble(split[1]));
            order.setComment((split[3]));
            order.setResult("OK");
        } catch (Exception e) {
            order.setResult(e.getMessage());
        }
        return order;
    }
}
