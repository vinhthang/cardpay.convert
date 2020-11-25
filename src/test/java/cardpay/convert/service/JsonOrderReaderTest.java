package cardpay.convert.service;


import cardpay.convert.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class JsonOrderReaderTest {
    @Autowired
    private OrderReader jsonOrderReader;

    @Test
    public void testReadLine_success() {
        Order order = jsonOrderReader.parseLine("{\"orderId\":3,\"amount\":1.23,\"currency\":\"USD\",\"comment\":\"order payment\"}");
        log.debug("order", order);
        assertThat(order != null);
        assertThat(order.getId() == 1);
        assertThat(order.getAmount() == 100.0);
        assertThat("OK".equals(order.getResult()));
    }

    @Test
    public void testReadLine_wrong_amount() {
        Order order = jsonOrderReader.parseLine("{\"orderId\":3,\"amount\":1.x23,\"currency\":\"USD\",\"comment\":\"order payment\"}");
        log.debug("order", order);
        assertThat(order != null);
        assertThat(!"OK".equals(order.getResult()));
    }

    @Test
    public void testReadLine_wrong_format() {
        String line = "{\"orderId\":3x,\"amount\":1.23,\"currency\":\"USD\",\"comment\":\"order payment\"}";
        Order order = jsonOrderReader.parseLine(line);
        log.debug("order", order);
        assertThat(order != null);
        assertThat(order.getResult()).isNotEqualTo("OK");
    }
}
