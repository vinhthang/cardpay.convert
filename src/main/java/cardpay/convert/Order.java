package cardpay.convert;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Double amount;
    private String comment;
    private String filename;
    private Integer line;
    private String result;

    public static Order error(String message) {
        Order order = new Order();
        order.setResult(message);
        return order;
    }
}
