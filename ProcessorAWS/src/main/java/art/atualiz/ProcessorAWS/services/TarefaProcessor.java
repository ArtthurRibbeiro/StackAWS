package art.atualiz.ProcessorAWS.services;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import art.atualiz.ProcessorAWS.models.TbTarefas;

@Service
public class TarefaProcessor {

    @Autowired
    MsgService msgService;
    
    private Random random = new Random();
    
    public TbTarefas processaTarefa(TbTarefas tarefa) throws InterruptedException{

        int multiplicador = 10;
        for (int i = 0; i <3; i++) {
            System.out.println("--- Processando ---");
            Thread.sleep((random.nextInt(100, 500) * multiplicador));
        }
        tarefa.concluir();

        return tarefa;
    }   
}
