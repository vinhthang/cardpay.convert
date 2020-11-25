package cardpay.convert;

import cardpay.convert.service.ReadFileService;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;


@Slf4j
@EnableAsync
@SpringBootApplication
public class ThangApplication implements ApplicationRunner {
	private final ReadFileService readFileService;
	public ThangApplication(ReadFileService readFileService) {
		this.readFileService = readFileService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ThangApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Order Read-");
		executor.initialize();
		return executor;
	}

	@Bean("csvReader")
	public ObjectReader csvReader() {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema
				.emptySchema()
				.withColumnSeparator(',')
				.withoutHeader()
				.withoutQuoteChar();

		return mapper.readerFor(Map.class).with(schema);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		args.getNonOptionArgs().forEach(filename -> {
			try {
				List<Order> orders = readFileService.read(filename).get();
				orders.stream().forEach(System.out::println);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		});
	}
}
