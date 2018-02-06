package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/home/kelyanne/driver/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //abrir navegador com o site
        navegador.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void testAdicionarInformacoesAdicionalDoUsuario(){
        //Clicar no link que possui o nome "Login"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login com name "form"
        WebElement formularioLogin = navegador.findElement(By.id("signinbox"));

        //Digitar no campo Email e senha
        formularioLogin.findElement(By.name("login")).sendKeys("julio0001");
        formularioLogin.findElement(By.name("password")).sendKeys("123456");

        //Clicar no botão "Login"
        navegador.findElement(By.linkText("SIGN IN")).click();

        navegador.findElement(By.className("me")).click();

        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        navegador.findElement(By.xpath("//div[@id='moredata']//button[@data-target='addmoredata']")).click();

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //No select de name "type" escolher a opção Phone
        WebElement combotype = popupAddMoreData.findElement(By.name("type"));
        new Select(combotype).selectByVisibleText("Phone");

        //digitar o contato
        popupAddMoreData.findElement(By.name("contact")).sendKeys("+55117777777");

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagem = navegador.findElement(By.id("toast-container"));
        String validacao = mensagem.getText();
        Assert.assertEquals("Your contact has been added!", validacao);


    }


}
