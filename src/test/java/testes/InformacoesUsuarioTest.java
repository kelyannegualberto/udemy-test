package testes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTest {

    @Test
    public void testAdicionarInformacoesAdicionalDoUsuario(){
        System.setProperty("webdriver.chrome.driver","/home/kelyanne/driver/chromedriver");
        WebDriver navegador = new ChromeDriver();


        //abrir navegador com o site
        navegador.get("https://staging.redeprof.com");

        //Clicar no link que possui o nome "Login"
        navegador.findElement(By.linkText("Login")).click();

        //Identificando o formulário de login com name "form"
        WebElement formularioLogin = navegador.findElement(By.name("form"));

        //Digitar no campo Email e senha
        formularioLogin.findElement(By.id("email")).sendKeys("joaquina@teste.com");
        formularioLogin.findElement(By.id("password")).sendKeys("adaptasenha123");

        //Clicar no botão "Login"
        formularioLogin.findElement(By.className("button btn-sign")).click();

        //Validação
        WebElement geral = navegador.findElement(By.className("clb-subnav-item is-active"));
        String TextGeral = geral.getText();
        Assert.assertEquals("Geral", TextGeral);


    }
}
