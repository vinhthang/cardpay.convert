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
        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(1);
        assertThat(order.getAmount()).isEqualTo(100.0);
        assertThat(order.getResult()).isEqualTo("OK");
    }

    @Test
    public void testReadLine_wrong_amount() {
        Order order = csvOrderReader.parseLine("1,10x0,USD,order payment");
        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(1);
        assertThat(order.getAmount()).isNull();
        assertThat(order.getResult()).isNotEqualTo("OK");
    }

    @Test
    public void testReadLine_wrong_format() {
        Order order = csvOrderReader.parseLine("1,,10x0,USD,order payment");
        assertThat(order).isNotNull();
        assertThat(order.getResult()).isEqualTo("Wrong format: 1,,10x0,USD,order payment");
    }
}
