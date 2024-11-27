package art.atualiz.ProcessorAWS.models;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void concluir(){
        this.setStatus("Concluído");
        this.setConcluidoEm(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }

}
