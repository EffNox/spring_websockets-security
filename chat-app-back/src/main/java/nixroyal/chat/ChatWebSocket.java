package nixroyal.chat;

import java.util.SplittableRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatWebSocket {

	public static void main(String[] args) {
		System.out.println(generateOtp(10));
		SpringApplication.run(ChatWebSocket.class, args);
	}

	public static String generateOtp(int otpLength) {//One Time Password
		SplittableRandom rnd = new SplittableRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < otpLength; i++) {
			sb.append(rnd.nextInt(0,10));
		}
		return sb.toString();
	}

}
