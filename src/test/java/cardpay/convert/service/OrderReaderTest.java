package cardpay.convert.service;

import cardpay.convert.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderReaderTest {
    @Test
    public void testCsvOrderReader() {

        OrderReader csvReader = new CsvOrderReader();
        List<Order> result = csvReader.readFile("order.csv");

        assertThat(result.size() == 2);
    }
}
