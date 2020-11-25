package cardpay.convert.service;

import cardpay.convert.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReadFileService {
    private final OrderReader csvOrderReader;

    public ReadFileService(@Qualifier("csvOrderReader") OrderReader csvOrderReader) {
        this.csvOrderReader = csvOrderReader;
    }

    @Async
    public CompletableFuture<List<Order>> read(String fileName) {
        if (fileName.endsWith("csv")) {
            return CompletableFuture.completedFuture(csvOrderReader.readFile(fileName));
        }
        if (fileName.endsWith("json")) {
            return CompletableFuture.completedFuture(csvOrderReader.readFile(fileName));
        }

        return CompletableFuture.completedFuture(Arrays.asList(Order.error("File type not support, " + fileName)));
    }
}
