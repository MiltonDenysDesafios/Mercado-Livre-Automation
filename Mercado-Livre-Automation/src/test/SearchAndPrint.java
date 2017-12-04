package test;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.MercadoLivreHomePage;
import param.ProductParam;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;


public class SearchAndPrint {
	public WebDriver driver;
	private MercadoLivreHomePage mercadoLivreHomePage;
	private ProductParam param;
	@BeforeClass
	private void setUp(){
		String url = "https://www.mercadolivre.com.br/";
		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
		//inicializando o site
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().setSize(new Dimension(1366,768));
		mercadoLivreHomePage = new MercadoLivreHomePage(driver);
		param = new ProductParam();
}
	@AfterClass
	private void windowClose(){
		driver.quit();
	}
	@Test
	public void searchProductsAndPrintFiveFirst() {
		//informa o produto para pesquisa
		param.setProductName("celular");		
		//realiza e pesquisa e printa resultados
		mercadoLivreHomePage.searchProduct(param);
		mercadoLivreHomePage.findFiverFirstElementsAndPrint();
	}
	@Test
	public void takesScreenShot(){
		try {
		//informa o produto para pesquisa
		param.setProductName("computadores");				
		// Informa o index da paginacao
		param.setPaginationIndex("3");
		//Informa index do item para ser selecionado
		param.setProductIndex("50");
		mercadoLivreHomePage.searchProduct(param);
		mercadoLivreHomePage.selectPaginationProductAndPrint(param);
		}catch(IOException ex) {
		System.out.println("[takesScreenShot] Problemas com o metodo");
		}
	}
		



}
