package dev.daniel.compo;
import dev.daniel.compo.composer.Composer;
import dev.daniel.compo.composer.Country;
import dev.daniel.compo.composer.Gender;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

// With GregorianCalendar, Days are 1 indexed, Months and Years are 0 indexed. Just sub in Calendar.JANUARY for month. Years and days stay as normal.
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		//System.exit(0);
	}

	// Beethoven DOB:1770-12-17  DOD:1827-03-26
	@Bean
	CommandLineRunner composer(){
		return args -> {
			Composer composer = new Composer(
					1,"Ludwig","van Beethoven", Country.GERMANY, Gender.MALE,
					new GregorianCalendar(1770, Calendar.DECEMBER,17),
					new GregorianCalendar(1827,Calendar.MARCH,26)
			);
			log.info("Composer: " + composer);
		};
	}
}
