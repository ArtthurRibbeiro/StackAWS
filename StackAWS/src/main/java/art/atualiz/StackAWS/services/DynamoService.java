package art.atualiz.StackAWS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Service
public class DynamoService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    public void verificaTabela(String tabelaNome) {
        // Verifica se a tabela já existe
        ListTablesResponse response = dynamoDbClient.listTables(ListTablesRequest.builder().build());

        if (response.tableNames().contains(tabelaNome)) {
            System.out.println("A tabela já existe: " + tabelaNome);
            return;
        }

        System.out.println("Tabela não encontrada. Criando a tabela: " + tabelaNome);

        // Criação da tabela
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
            .tableName(tabelaNome)
            .keySchema(KeySchemaElement.builder()
                .attributeName("id_tarefa")
                .keyType(KeyType.HASH) // Chave primária
                .build())
            .attributeDefinitions(AttributeDefinition.builder()
                .attributeName("id_tarefa")
                .attributeType(ScalarAttributeType.S) // Tipo: String
                .build())
            .billingMode(BillingMode.PAY_PER_REQUEST) // Modo de faturamento
            .build();

        try {
            dynamoDbClient.createTable(createTableRequest);
            System.out.println("Tabela criada com sucesso: " + tabelaNome);
        } catch (ResourceInUseException e) {
            System.err.println("Erro: A tabela já está em uso.");
        } catch (DynamoDbException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}