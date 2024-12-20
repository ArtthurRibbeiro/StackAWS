package art.atualiz.ProcessorAWS.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import art.atualiz.ProcessorAWS.models.TbTarefas;
import art.atualiz.ProcessorAWS.services.MsgService;
import art.atualiz.ProcessorAWS.services.ObjectMapperService;
import art.atualiz.ProcessorAWS.services.TarefaProcessor;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class Consumer {

    @Autowired
    private ObjectMapperService mapper;

    @Autowired
    private TarefaProcessor processador;

    @Autowired
    MsgService msgService;

    @SqsListener("Fila_EnvioProcessamento")
    public void listen(String json) throws Exception{
        TbTarefas tarefa = mapper.JsonParaTarefa(json);

        if (tarefa.getStatus().equals("Em processo")) {
            tarefa = processador.processaTarefa(tarefa);
        }

        msgService.enviaTarefa(tarefa);

        


        
        //System.out.println("Mensagem recebida!");
        //System.out.println("---" + message.content() + "---");

    }

}

