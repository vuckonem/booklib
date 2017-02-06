package readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// WOW
//u browseru otvaras: http://localhost:8080/readingList/pera
@SpringBootApplication	//Enable component-scanning and auto configuration
public class ReadingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args); //Bootstrap the application
	}
}
