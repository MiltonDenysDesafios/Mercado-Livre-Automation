package util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ScreenShot {
	private static final Logger LOG = LoggerFactory.getLogger(ScreenShot.class);
	//metodo responsavel por tirar e armazenar um screenshot
	public static void takeScreenShot(WebDriver driver, String name) throws IOException{
		String path = null;
		try{
			//caminho para onde o print sera enviado
			path="C:\\Users\\Denys\\Pictures" + name + ".png";
			//executa o comando screenshot
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//Copia o arquivo da pasta temporaria para a pasta especificada
			FileUtils.copyFile(sourceFile,new File(path));
		}catch(Exception e){
			LOG.error("[Screenshot] Erro ao tirar e armazenar o print: "+e.getCause());
		}
	}
}
