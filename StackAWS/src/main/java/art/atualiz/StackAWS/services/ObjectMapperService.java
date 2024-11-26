package art.atualiz.StackAWS.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import art.atualiz.StackAWS.models.TbTarefas;

public class ObjectMapperService {
    @Autowired
    ObjectMapper mapper;
    
    public String tarefaParaJson(TbTarefas tarefa) throws JsonProcessingException{
        return mapper.writeValueAsString(tarefa);
    }

    public TbTarefas JsonParaTarefa(String json) throws JsonMappingException, JsonProcessingException{
        return mapper.readValue(json, TbTarefas.class);
    }
}
