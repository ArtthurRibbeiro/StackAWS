package art.atualiz.ProcessorAWS.services;

import java.io.File;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import art.atualiz.ProcessorAWS.models.TbTarefas;

@Service
public class TarefaProcessor {

    @Autowired
    MsgService msgService;

    @Autowired
    LogService logService;

    @Autowired
    FileManagerService fileManagerService;

    @Autowired
    S3Service s3Service;
    
    private Random random = new Random();
    
    public TbTarefas processaTarefa(TbTarefas tarefa) throws Exception{

        int tempoDecorrido = 0;

        int multiplicador = 1;
        for (int i = 0; i <3; i++) {
            System.out.println("--- Processando ---");
            int milisec = random.nextInt(1000, 5000) * multiplicador;
            tempoDecorrido += milisec;
            Thread.sleep((milisec));
        }

        tarefa.concluir();

        String log = logService.gerarLog(tarefa, (double) tempoDecorrido / 1000);
        String titulo = tarefa.getId().toString();

        File file = fileManagerService.criaFile(log, titulo);

        s3Service.FileUpload(file, titulo);

        fileManagerService.deletaFileTeste(titulo);

        return tarefa;
    }   
}
