package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){

        System.setProperty("webdriver.chrome.driver","/home/kelyanne/driver/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //abrir navegador com o site
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }
}
