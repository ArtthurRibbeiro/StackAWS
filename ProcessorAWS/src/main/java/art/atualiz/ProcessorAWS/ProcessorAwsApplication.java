package art.atualiz.ProcessorAWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import art.atualiz.ProcessorAWS.services.S3Service;

@SpringBootApplication
public class ProcessorAwsApplication implements CommandLineRunner{

	@Autowired
	S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(ProcessorAwsApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		s3Service.connectionTest();

		s3Service.verificaBucket();
	}

}
