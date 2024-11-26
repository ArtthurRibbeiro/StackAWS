package art.atualiz.StackAWS.consumer;

import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class Consumer {

    @SqsListener("Fila_RetornoProcessamento")
    public void listen(Message message){
        System.out.println("Mensagem recebida!");
        System.out.println("---" + message.content() + "---");

    }

}
