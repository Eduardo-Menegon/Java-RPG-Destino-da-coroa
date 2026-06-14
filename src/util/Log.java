package util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static void registar(String mensagem){
        try(PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))){

            LocalDateTime agora = LocalDateTime.now();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            writer.println("[" + agora.format(formato) + "]" + mensagem);
        }catch (IOException e){
            System.out.println("Erro ao gravar log.");
        }
    }
}
