package art.atualiz.StackAWS.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import org.springframework.web.bind.annotation.PathVariable;
import art.atualiz.StackAWS.models.TbTarefas;
import art.atualiz.StackAWS.services.MsgService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private DynamoDbTemplate dynamoDbTemplate;

    @Autowired
    MsgService msgService;

    @GetMapping("/{nome}/{desc}")
    public ResponseEntity<Void> salvar(@PathVariable("nome") String nome, @PathVariable("desc") String desc) throws JsonProcessingException {

        var tarefa = new TbTarefas(nome, desc); //tarefa criada como em processo

        try {
            msgService.enviaTarefa(tarefa);
            
        } catch (Exception e) {
            tarefa.setStatus("Pendente");
        }

        dynamoDbTemplate.save(tarefa);
        
        return ResponseEntity.ok().build();
    }

//     @GetMapping("/{id}")
// public ResponseEntity<List<TbTarefas>> listarTarefas(@PathVariable("id") String id) {

//     var key = Key.builder()
//                  .partitionValue(id)
//                  .build();

//     var condicao = QueryConditional.keyEqualTo(key);

//     var query = QueryEnhancedRequest.builder()
//                                      .queryConditional(condicao)
//                                      .build();

//     var result = dynamoDbTemplate.query(query, TbTarefas.class);

//     var tarefas = result.items().stream().toList();

//     return ResponseEntity.ok(tarefas);
// }

    @GetMapping("/{id}")
    public ResponseEntity<TbTarefas> buscarPorId(@PathVariable("id") String id) {

        var tarefa = dynamoDbTemplate.load(Key.builder()
                                 .partitionValue(id)
                                 .build(), TbTarefas.class);
                                 
        return tarefa == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(tarefa);
    }

    @GetMapping("/")
    public ResponseEntity<List<TbTarefas>> listarTarefas() {

        var itens = dynamoDbTemplate.scan(ScanEnhancedRequest.builder().build(), TbTarefas.class);

        var tarefas = itens.items().stream().toList();
                                 
        return tarefas == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTarefa(@PathVariable String id, @RequestBody TbTarefas novaTarefa) {

        var tarefa = dynamoDbTemplate.load(Key.builder()
                                 .partitionValue(id)
                                 .build(), TbTarefas.class);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        } else{
            tarefa.setNome(novaTarefa.getNome());
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setStatus(novaTarefa.getStatus());
            dynamoDbTemplate.save(tarefa);
            
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable String id) {

        var tarefa = dynamoDbTemplate.load(Key.builder()
                                 .partitionValue(id)
                                 .build(), TbTarefas.class);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        } else{
            dynamoDbTemplate.delete(tarefa);
            return ResponseEntity.noContent().build();
        }

    }
}
