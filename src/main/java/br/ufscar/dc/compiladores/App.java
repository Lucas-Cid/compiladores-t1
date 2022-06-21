package br.ufscar.dc.compiladores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class App 
{
    public static void main( String[] args )
    {
        try {
            // Abre arquivo de entrada
            CharStream cs = CharStreams.fromFileName(args[0]);

            // Coloca o arquivo no lexer
            LaLexer lex = new LaLexer(cs);
            Token t = null;

            // Cria arquivo de saída e eventuais diretórios necessários
            String path = args[1];
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            // Abre arquivo para escrita 
            FileWriter myWriter = new FileWriter(file);

            boolean error = false;

            // Continua buscando por novos tokens até chegar no fim do arquivo ou encontrar um erro
            while ((t = lex.nextToken()).getType() != Token.EOF && !error) {
                // Pega o tipo do token encontrado
                String type = LaLexer.VOCABULARY.getDisplayName(t.getType());

                // Se o token for uma palavra reservada, imprime a pŕopria palavra como tipo
                if(type == "Reserved_word")
                    myWriter.write("<" + '\'' + t.getText() + '\'' + "," + '\'' + t.getText() + '\'' + ">" + "\n");
                // Se for um token desconhecido, aponta a linha em que ele aparece e avisa do erro
                else if(type == "UNKNOWN_TOKEN"){
                    myWriter.write("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado\n");
                    error = true;
                // Caso haja um comentário não fechado, avisa a linha do erro
                } else if(type == "COMMENT_ERROR"){
                    myWriter.write("Linha " + t.getLine() + ": comentario nao fechado\n");
                    error = true;
                // Caso haja uma cadeia não fechada, avisa a linha do erro
                } else if(type == "CADEIA_ERROR"){
                    myWriter.write("Linha " + t.getLine() + ": cadeia literal nao fechada\n");
                    error = true;
                }
                // Senão, imprime o token e o tipo do token
                else
                    myWriter.write("<" + '\'' + t.getText() + '\'' + "," + type + ">" + "\n");
            }

            myWriter.close();
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
}
