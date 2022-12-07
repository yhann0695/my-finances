package br.com.investimento.financas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public abstract class ArquivoUtils {
    
    public static final String CAMINHO_SERVIDOR = "C:" + File.separator + "documentos" + File.separator;

	public static void writeFile(MultipartFile file) throws IOException {
		Path filePath = Paths.get(CAMINHO_SERVIDOR + file.getOriginalFilename());
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	}

	public static ByteArrayResource readFile(String nomeArquivo) throws IOException {
		File file = new File(ArquivoUtils.CAMINHO_SERVIDOR + nomeArquivo);
		Path path = Paths.get(file.getAbsolutePath());
		return new ByteArrayResource(Files.readAllBytes(path));

	}
}