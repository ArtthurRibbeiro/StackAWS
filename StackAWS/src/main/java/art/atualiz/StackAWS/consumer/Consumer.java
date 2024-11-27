package art.atualiz.StackAWS.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import art.atualiz.StackAWS.controllers.TarefaController;
import art.atualiz.StackAWS.models.TbTarefas;
import art.atualiz.StackAWS.services.ObjectMapperService;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class Consumer {

    @Autowired
    private ObjectMapperService mapper;

    @Autowired
    private TarefaController controller;

    @SqsListener("Fila_RetornoProcessamento")
    public void listen(String json) throws JsonMappingException, JsonProcessingException{
        TbTarefas tarefa = mapper.JsonParaTarefa(json);

        if (tarefa.getStatus().equals("Concluído")) {
            controller.atualizarTarefa(tarefa.getId().toString(), tarefa);
            System.out.println("--- Tarefa concluída atualizada ---");

            
        } else{
            System.out.println("--- Houve um erro ---");
        }



        
        //System.out.println("Mensagem recebida!");
        //System.out.println("---" + message.content() + "---");

    }

}
