package art.atualiz.StackAWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class StackAwsApplication {

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
	}