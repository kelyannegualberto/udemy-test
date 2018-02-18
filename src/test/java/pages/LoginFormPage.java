package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    //abordagem estrutural
    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginFormPage digitarSenha(String senha){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);
        return this;
    }

    public HomePage clicarSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new HomePage(navegador);
    }

    //abordagem funcional
    public HomePage fazerLogin(String login, String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new HomePage(navegador);
    }
}
