package suporte;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;

public class ScreenShot {
    public static void tirarScreenshot(WebDriver navegador, String arquivo){
        //tirando o print screen da tela
        File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            //guardando o print em um arquivo
            FileUtils.copyFile(screenshot, new File(arquivo));
        } catch (Exception e){
            System.out.println("Houveram problemas ao copiar o arquivo para a pasta" + e.getMessage());
        }
    }
}
