package cardpay.convert.service;

import cardpay.convert.Order;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JsonOrderReader extends OrderReader {
    private final ObjectReader objectReader;

    public JsonOrderReader() {
        JsonMapper mapper = new JsonMapper();
        objectReader = mapper.reader();
    }

    @Override
    public Order parseLine(String line) {
        Order order = new Order();
        try {
            Map<String, String> map = objectReader.readValue(objectReader.createParser(line), new TypeReference<Map<String, String>>() {});
            order.setComment(map.get("comment"));
            order.setId(Integer.parseInt(map.get("orderId")));
            order.setAmount(Double.parseDouble(map.get("amount")));
            order.setResult("OK");
        } catch (Exception e) {
            order.setResult(e.getMessage());
        }
        return order;
    }
}
