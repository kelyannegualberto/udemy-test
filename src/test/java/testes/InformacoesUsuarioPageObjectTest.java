package testes;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectTestData.csv")
public class InformacoesUsuarioPageObjectTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    //teste com dados setados
    @Test
    public void addInformacaoUsuarioTest(){
        String validacao = new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin("julio0001", "123456")
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou()
                .addContato("phone", "98989898")
                .capturarTextoToast();

        Assert.assertEquals("Your contact has been added!", validacao);

    }

    //teste com dados em um arquivo .csv
    @Test
    public void addInformacaoUsuarioTestData(
            @Param(name = "login") String login,
            @Param(name = "senha") String senha,
            @Param(name = "tipo") String tipo,
            @Param(name = "contato") String contato,
            @Param(name = "mensagem") String mensagem
    ){
        String validacao = new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin(login,senha)
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou()
                .addContato(tipo, contato)
                .capturarTextoToast();

        Assert.assertEquals(mensagem, validacao);

    }

    @After
    public void quit(){
        navegador.close();
    }
}
