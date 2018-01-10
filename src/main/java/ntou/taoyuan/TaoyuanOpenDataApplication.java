package ntou.taoyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //定時任務
public class TaoyuanOpenDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaoyuanOpenDataApplication.class, args);
	}
}
