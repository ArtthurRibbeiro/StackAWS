package art.atualiz.StackAWS.models;


import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class TbTarefas {

    private UUID id;
    private String nome;
    private String descricao;
    private String status;
    private String criadoEm;
    private String concluidoEm;

    public TbTarefas(String nome, String descricao) {
        this.id = UUID.randomUUID();

        this.nome = nome;
        this.descricao = descricao;

        this.status = "Pendente";
        this.criadoEm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        //this.criadoEm = Instant.now();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id_tarefa")
    public UUID getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getStatus() {
        return status;
    }
    
    @DynamoDbAttribute("criado_em")
    public String getCriadoEm() {
        return criadoEm;
    }
    @DynamoDbAttribute("concluido_em")
    public String getConcluidoEm() {
        return concluidoEm;
    }
}
