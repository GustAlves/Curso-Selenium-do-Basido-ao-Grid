package br.ce.wcaquino.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.ce.wcaquino.core.Propriedades.TipoExecucao;

public class DriverFactory {

	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		WebDriver driver = null;

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {

//			System.setProperty("webdriver.chrome.driver",
//					"/home/gustavo/eclipse-workspace/SeleniumDrivers/chromedriver");

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				driver = new ChromeDriver();
				break;
			}
		}

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				break;
			case CHROME:
				cap = DesiredCapabilities.chrome();
				break;
			}

			try {
				driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			DesiredCapabilities cap = null;

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				break;
			case CHROME:
				cap = DesiredCapabilities.chrome();
				break;
			case IE:
				cap = DesiredCapabilities.internetExplorer();
				break;
			}

			try {
				driver = new RemoteWebDriver(new URL("https://login:'chave da nuvem @ 'URL DA NUVEM/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com a NUVEM");
				e.printStackTrace();
			}
		}

		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}
}
