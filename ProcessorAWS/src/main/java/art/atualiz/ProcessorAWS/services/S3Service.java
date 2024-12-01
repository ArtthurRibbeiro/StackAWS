package art.atualiz.ProcessorAWS.services;

import java.io.File;
import java.net.URI;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import org.springframework.stereotype.Service;

@Service
public class S3Service {
    private final S3Client s3 = S3Client.builder()
        .endpointOverride(URI.create("http://localhost:4566")) // Endpoint do LocalStack
        .region(Region.SA_EAST_1) 
        .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test", "test")
        ))
        .serviceConfiguration(S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build())
        .build();


    final private String bucketName = "processinglogs";

    public void FileUpload(File file, String nome) {
        try {
            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(nome)
                            .build(),
                    file.toPath());  // Converte o File para Path
            System.out.println("Arquivo " + file.getName() + " carregado para o bucket " + bucketName + ".");
        } catch (S3Exception e) {
            System.err.println("Erro ao carregar arquivo: " + e.awsErrorDetails().errorMessage());
        } finally {
            s3.close();
        }
    }

    public void connectionTest() {
        S3Client s3 = S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack
                .region(Region.US_EAST_1) // Região padrão
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test") // Credenciais padrão do LocalStack
                ))
                .build();
                
        try {
            ListBucketsResponse response = s3.listBuckets();
            if (response.buckets().isEmpty()) {
                System.out.println("Conexão bem-sucedida! Nenhum bucket encontrado.");
            } else {
                System.out.println("Conexão bem-sucedida! Buckets existentes:");
                response.buckets().forEach(bucket -> System.out.println(" - " + bucket.name()));
            }
        } catch (S3Exception e) {
            System.err.println("Erro ao conectar ao S3: " + e.awsErrorDetails().errorMessage());
        } finally {
            s3.close();
        }
    }

    public void verificaBucket( ) {
    try {
        // Verifica se o bucket existe
        s3.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
        System.out.println("O bucket '" + bucketName + "' existe.");
    } catch (NoSuchBucketException e) {
        // Caso não exista, cria o bucket
        System.out.println("Bucket '" + bucketName + "' não encontrado. Criando...");
        s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        System.out.println("Bucket '" + bucketName + "' criado com sucesso.");
    } catch (S3Exception e) {
        System.err.println("Erro ao verificar ou criar o bucket: " + e.awsErrorDetails().errorMessage());
    }
}


}

