package br.ce.wcaquino.suites;

import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.pages.LoginPage;
import br.ce.wcaquino.tests.ContaTest;
import br.ce.wcaquino.tests.MovimentacaoTest;
import br.ce.wcaquino.tests.RemoverMovimentacaoContaTest;
import br.ce.wcaquino.tests.ResumoTest;
import br.ce.wcaquino.tests.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({ ContaTest.class, MovimentacaoTest.class, RemoverMovimentacaoContaTest.class, SaldoTest.class,
		ResumoTest.class })
public class SuiteGeral {

	private static LoginPage page = new LoginPage();

	@BeforeClass
	public static void reset() {
		page.acessarTelaInicial();

		page.setEmail("gustavo.henrique@logicsp.com.br");
		page.setSenha("pa33Lx$k");
		page.entrar();

		page.resetar();
		killDriver();
	}
}
