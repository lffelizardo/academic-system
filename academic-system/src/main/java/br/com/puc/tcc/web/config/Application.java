package br.com.puc.tcc.web.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.com.puc.tcc.web")
@Configuration
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class, DatabaseConfiguration.class);
		DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        try {
			log.info("\n----------------------------------------------------------\n\t" +
			        "Application '{}' is running! Access URLs:\n\t" +
			        "External: \thttp://{}:{}\n----------------------------------------------------------",
			    env.getProperty("spring.application.name"),
			    env.getProperty("server.port"),
			    InetAddress.getLocalHost().getHostAddress(),
			    env.getProperty("server.port"));
		} catch (UnknownHostException e) {
			log.error(e.getMessage(),e);
		}
	}

	@PostConstruct
    public void initApplication() {
        log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }

    }
}
