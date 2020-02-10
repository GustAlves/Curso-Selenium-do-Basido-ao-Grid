package br.ce.wcaquino.tests;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

//import java.util.List;

import org.junit.Assert;
import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.pages.MenuPage;
import br.ce.wcaquino.pages.ResumoPage;

public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();

	@Test
	public void test1_ExcluirMovimentacao() {
		menuPage.acessarTelaResumo();
		resumoPage.excluirMovimentacao();
		Assert.assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}

	@Test
	public void test2_ResumoMensal() {
		menuPage.acessarTelaResumo();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());

//		List<WebElement> elementrosEncontrados = getDriver()
//				.findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
//		Assert.assertEquals(0, elementrosEncontrados.size());
	}
}
