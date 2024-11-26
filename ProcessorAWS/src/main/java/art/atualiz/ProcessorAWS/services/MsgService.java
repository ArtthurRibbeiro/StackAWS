package art.atualiz.ProcessorAWS.services;



import java.util.concurrent.CompletionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import art.atualiz.ProcessorAWS.consumer.Message;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;



@Service
public class MsgService {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private SqsAsyncClient sqsAsyncClient;


    String fila = "Fila_RetornoProcessamento";

    public void enviaMsg(String msg) {

        /*try {
            var SQS = "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/msgFila";
        sqsTemplate.send(SQS, msg);
        } catch (Exception e) {
            System.out.println("é possível que a fila msgFila nao exista ainda");
        }*/

        try {
        sqsTemplate.send(verificaFila(this.fila), new Message("Conectado!"));
        } catch (Exception e) {
            System.out.println("Houve um erro");
        }
    }
 
    //retorna URL da  fila
    private String verificaFila(String fila){
        String urlFila;
        try {
         
            urlFila = this.getFilaUrl(fila);
            System.out.println("Fila encontrada: " + urlFila);
           return urlFila;
        } catch (CompletionException e) {
        if (e.getCause() instanceof QueueDoesNotExistException) {

            urlFila = criaFila(fila);
            System.out.println("Criando nova fila: " + urlFila);
            return urlFila;
        } else {
            
            System.err.println("Erro na verificação da fila: " + e.getCause().getMessage());
            throw e; 
        }
    }
        
        
        /* 
        catch (QueueDoesNotExistException e) {
            urlFila = criaFila(fila);
            System.out.println("Criando nova fila:  " + urlFila);
            return urlFila;
        }*/
    }

    public String criaFila(String fila) {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(fila) 
                .build();
        CreateQueueResponse createQueueResponse = sqsAsyncClient.createQueue(createQueueRequest).join();
        return createQueueResponse.queueUrl();
    }



    //se rodar sozinho esse bregueço da erro
    private String getFilaUrl(String fila){
        String url = sqsAsyncClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(fila).build()).join().queueUrl();
        System.out.println(url);
        return url;

    }
    
    
}
