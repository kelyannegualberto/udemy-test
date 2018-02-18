package testes;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generate;
import suporte.ScreenShot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {

    private WebDriver navegador;

    //pegar nome do teste atual
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp(){

        navegador = Web.createChrome();

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
    }

    @Test
    public void testAdicionarInformacoesAdicionalDoUsuario(@Param(name = "tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada){

        navegador.findElement(By.xpath("//div[@id='moredata']//button[@data-target='addmoredata']")).click();

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //No select de name "type" escolher a opção Phone
        WebElement combotype = popupAddMoreData.findElement(By.name("type"));
        new Select(combotype).selectByValue(tipo);

        //digitar o contato
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagem = navegador.findElement(By.id("toast-container"));
        String validacao = mensagem.getText();
        Assert.assertEquals(mensagemEsperada, validacao);


    }

    @Test
    public void removerContatoUsuario(){

        //Clicar no ícone de excluir pelo seu xpath span[text()="kely@test.com"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"11@11.com.br\"]/following-sibling::a")).click();

        //Confirmar a janela do javascript
        navegador.switchTo().alert().accept();

        //tirar print da tela
        ScreenShot.tirarScreenshot(navegador, "/home/kelyanne/Imagens/teste/" +
                Generate.dataParaArquivo() + testName.getMethodName() +".png");

        //Validar a mensagem Rest in peace, dear email!
        WebElement mensagem = navegador.findElement(By.id("toast-container"));
        String mensagemExcluir = mensagem.getText();
        Assert.assertEquals("Rest in peace, dear email!", mensagemExcluir);

        //Aguardar até 10s para que a janela desapareça - espera explicíta
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagem));

        //fazer logout
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void quit(){
        navegador.close();
    }

}
