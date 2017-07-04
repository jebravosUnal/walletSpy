import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by EBR3556 on 13/06/2017.
 */
//@SpringBootApplication
public class WalletApp extends SpringBootServletInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WalletApp.class);
    private static Class<WalletApp> applicationClass = WalletApp.class;

    public static void main(String[] args) {
        SpringApplication.run(WalletApp.class, args);
        LOGGER.debug("Hola mundo desde wallet appp");
    }



//    Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
}
