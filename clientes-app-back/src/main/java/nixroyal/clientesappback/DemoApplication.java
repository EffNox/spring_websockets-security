package nixroyal.clientesappback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	// private @Autowired BCryptPasswordEncoder pwEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// String pass= "123456";
		// for (int i = 0; i < 4; i++) {
		// 	String pwEcoded=pwEncoder.encode(pass);
		// 	System.out.println(pwEcoded);
		// }
	}

}
