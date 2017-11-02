import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by EBR3556 on 02/11/2017.
 */

@SpringBootApplication
public class WalletApiApp extends SpringBootServletInitializer {

    private final static Logger LOGGER = LogManager.getLogger(WalletApiApp.class);

    private static Class<WalletApiApp> applicationClass = WalletApiApp.class;

    public static void main(String[] args) {
        LOGGER.debug("@@@@Hola mundo desde wallet appp");
        SpringApplication.run(WalletApiApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
