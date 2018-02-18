package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Generate {
    public static String dataParaArquivo(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }
}
