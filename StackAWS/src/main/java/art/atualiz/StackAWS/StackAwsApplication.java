package art.atualiz.StackAWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import art.atualiz.StackAWS.services.DynamoService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@SpringBootApplication
public class StackAwsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StackAwsApplication.class, args);
	}


	@Bean
	public OpenAPI customOpenAPI() {
	 return new OpenAPI()
	 .info(new Info()
	 .title("Servidor de tarefas")
	 .version("1.0.0")
	 .contact(new
	Contact().email("").name(""))
	 .description("")
	 );
	}

	@Autowired
	private DynamoService dynamoService;

	@Override
	public void run(String... args) throws Exception {
		try {
            dynamoService.verificaTabela("tb_tarefas");
        } catch (DynamoDbException e) {
            System.err.println("Erro ao acessar o DynamoDB: " + e.getMessage());
        }
		// var SQS = "https://localhost.localstack.cloud:4566/000000000000/Fila_EnvioProcessamento";
		// sqsTemplate.send(SQS, new Message("Conectado!"));

		//msgService.enviaMsg("Conectado!");
		
	}
	}