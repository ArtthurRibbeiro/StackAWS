package art.atualiz.ProcessorAWS.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class FileManagerService {

    
    
public File criaFile(String conteudo, String titulo) throws Exception{

    StringBuffer str = new StringBuffer();
		str.append(conteudo);
        File file = null;

		try {
			file = new File(takePath(titulo)); 
            FileWriter out = new FileWriter(file);
            out.write(str.toString());
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return file;
        
}

public void deletaFileTeste(String titulo) throws Exception{
  File file = pegaFile(titulo);

  if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
}
        
public String takePath(String titulo) throws Exception {

    String basePath = System.getProperty("user.dir");
    String caminhoArquivo = basePath + File.separator + titulo + ".txt";

    System.out.println(caminhoArquivo);

    return caminhoArquivo;
}

public File pegaFile(String titulo) throws Exception{
    File file = new File(takePath(titulo));

    return file;

}


}
