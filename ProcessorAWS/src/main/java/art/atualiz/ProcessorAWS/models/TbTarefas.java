package art.atualiz.ProcessorAWS.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbTarefas {

    private UUID id;
    private String nome;
    private String descricao;
    private String status;
    private String criadoEm;
    private String concluidoEm;

}
