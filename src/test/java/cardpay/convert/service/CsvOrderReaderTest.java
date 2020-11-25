package cardpay.convert.service;


import cardpay.convert.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class CsvOrderReaderTest {
    @Autowired
    private OrderReader csvOrderReader;

    @Test
    public void testReadLine_success() {
        Order order = csvOrderReader.parseLine("1,100,USD,order payment");
        log.debug("order", order);
        assertThat(order != null);
        assertThat(order.getId() == 1);
        assertThat(order.getAmount() == 100.0);
        assertThat("OK".equals(order.getResult()));
    }

    @Test
    public void testReadLine_wrong_amount() {
        Order order = csvOrderReader.parseLine("1,10x0,USD,order payment");
        log.debug("order", order);
        assertThat(order != null);
        assertThat(order.getId() == 1);
        assertThat(order.getAmount() == null);
        assertThat(!"OK".equals(order.getResult()));
        assertThat("Amount 10x0".equals(order.getResult()));
    }

    @Test
    public void testReadLine_wrong_format() {
        Order order = csvOrderReader.parseLine("1,,10x0,USD,order payment");
        log.debug("order", order);
        assertThat(order != null);
        assertThat(order.getId() == 1);
        assertThat(order.getAmount() == null);
        assertThat(!"OK".equals(order.getResult()));
        assertThat("Wrong format: 1,,10x0,USD,order payment".equals(order.getResult()));
    }
}
