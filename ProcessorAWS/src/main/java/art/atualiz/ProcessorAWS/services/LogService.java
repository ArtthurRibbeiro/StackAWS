package art.atualiz.ProcessorAWS.services;

import org.springframework.stereotype.Service;

import art.atualiz.ProcessorAWS.models.TbTarefas;

@Service
public class LogService {
    public String gerarLog(TbTarefas tarefa, Double tempoDecorrido){

        String logTemplate = """
        --- Log de processamento de tarefa ---
        A tarefa de id: %s, nome: %s, que foi criada em: %s, concluída em: %s, foi processada sem erros.
        Tempo decorrido em processo foi: %.2f segundos.
        
        Detalhes:
        id_tarefa: %s
        nome_tarefa: %s
        Criada_em: %s
        Concluida_em: %s
        Tempo_decorrido: %.2f segundos
        
        -------------------------------------
        """;


        return String.format(
            logTemplate,
            tarefa.getId().toString(),
            tarefa.getNome(),
            tarefa.getCriadoEm(),
            tarefa.getConcluidoEm(),
            tempoDecorrido,
            tarefa.getId().toString(),
            tarefa.getNome(),
            tarefa.getCriadoEm(),
            tarefa.getConcluidoEm(),
            tempoDecorrido);
    }

}
