package art.atualiz.ProcessorAWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import art.atualiz.ProcessorAWS.services.MsgService;

@SpringBootApplication
public class ProcessorAwsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProcessorAwsApplication.class, args);
	}

	
	@Autowired
	private MsgService msgService;

	@Override
	public void run(String... args) throws Exception {
		// var SQS = "https://localhost.localstack.cloud:4566/000000000000/Fila_EnvioProcessamento";
		// sqsTemplate.send(SQS, new Message("Conectado!"));

		//msgService.enviaMsg("Conectado!");
	}

}
