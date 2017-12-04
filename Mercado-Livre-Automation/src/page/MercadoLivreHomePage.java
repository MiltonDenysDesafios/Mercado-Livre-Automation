package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import param.ProductParam;
import pojo.ProductPojo;
import util.ScreenShot;

public class MercadoLivreHomePage {
	
	WebDriver driver;
	private List<ProductPojo>listPojo = new ArrayList<>();
	
	public MercadoLivreHomePage(WebDriver driver){
		this.driver=driver;
	}
	
	/*metodo responsável por pesquisar um item
	 * @param parametros para manipulaçãoda pagina
	 */
	public void searchProduct(ProductParam productParam){
		
		WebElement searchField = driver.findElement(By.xpath("html/body/header/div/form/input"));
		searchField.clear();
		searchField.sendKeys(productParam.getProductName());
		driver.findElement(By.xpath("html/body/header/div/form/button[3]")).click();
	}
	public void findFiverFirstElementsAndPrint(){
		for(int i=1; i<=5;i++){
			ProductPojo foundProduct = new ProductPojo();
			foundProduct.setPrice(driver.findElement(By.xpath("(.//span[@class='price-fraction'])[" + i + "]")).getText());
			foundProduct.setTitle(driver.findElement(By.xpath("(.//span[@class='main-title'])[" + i + "]")).getText());
			//armazenar index para facilitar a leitura
			foundProduct.setIndex(i);
			//adiciona pojos em uma lista de pojos
			listPojo.add(foundProduct);
		}
		printProduct(listPojo);
	}
	/*Metodo responsavel por imprimir os itens encontrados
	 * @param listaPojo lista de detalhes do item
	 * */
	private void printProduct(List<ProductPojo>listPojo){
		for(ProductPojo productPojo:listPojo){
			System.out.println("item nº" + productPojo.getIndex());
			System.out.println("Preco: R$" + productPojo.getPrice());
			System.out.println("Descricao: " + productPojo.getTitle());
		}
	}
	public void scrollBottom() {
		//inicia o scoll da pagina
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//executa o scrolldown na pagina
		jse.executeScript("window.scrollBy(0,7500)", "");
	}
	public void selectProduct(ProductParam param) {
		//Verifica o index informado antes de executar o scrolldown
		if(Integer.parseInt(param.getProductIndex())>25) {
			scrollBottom();
		}
		driver.findElement(By.xpath(".//*[@id='results-section']/div[2]/ul/li["+param.getPaginationIndex()+"]/a")).click();		
	}
	//metodo responsavel por selecionar paginacao
	public void openPaginationIndex(ProductParam param) {
		scrollBottom();
		driver.findElement(By.xpath(".//*[@id='results-section']/div[2]/ul/li["+param.getPaginationIndex()+"]/a")).click();
	}
	//metodo responsavel por selecionar o item da pagina e printar
	public void selectPaginationProductAndPrint(ProductParam param) throws IOException{
		openPaginationIndex(param);
		selectProduct(param);
		ScreenShot.takeScreenShot(driver,  param.getPaginationIndex());
	}
	
	
	
	
}
