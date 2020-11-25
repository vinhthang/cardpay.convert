package cardpay.convert.service;

import cardpay.convert.Order;

import java.util.List;

public interface OrderReader {

    List<Order> readFile(String filename);

    Order parseLine(String line);
}
