package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage{

    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoContado(String tipo){
        //No select de name "type" escolher a opção Phone
        WebElement combotype =  navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(combotype).selectByValue(tipo);
        return this;
    }

    public AddContactPage digitarContato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }

    public MePage clicarSalvar(){
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
        return new MePage(navegador);
    }

    //abordagem funcional
    public MePage addContato(String tipo, String contato){
        escolherTipoContado(tipo);
        digitarContato(contato);
        clicarSalvar();
        return new MePage(navegador);
    }
}
