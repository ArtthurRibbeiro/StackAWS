package art.atualiz.StackAWS.models.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import art.atualiz.StackAWS.models.Tarefa;

@EnableScan
public interface TarefaRepository extends CrudRepository<Tarefa, String> {
}
