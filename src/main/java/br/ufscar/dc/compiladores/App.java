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
            CharStream cs = CharStreams.fromFileName(args[0]);
            AlgumaLexer lex = new AlgumaLexer(cs);
            Token t = null;
            String path = args[1];
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter myWriter = new FileWriter(file);
            boolean error = false;
            while ((t = lex.nextToken()).getType() != Token.EOF && !error) {
                String type = AlgumaLexer.VOCABULARY.getDisplayName(t.getType());
                if(type == "Reserved_word")
                    myWriter.write("<" + '\'' + t.getText() + '\'' + "," + '\'' + t.getText() + '\'' + ">" + "\n");
                else if(type == "UNKNOWN_TOKEN"){
                    myWriter.write("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado\n");
                    error = true;
                } else if(type == "COMMENT_ERROR"){
                    myWriter.write("Linha " + t.getLine() + ": comentario nao fechado\n");
                    error = true;
                } else if(type == "CADEIA_ERROR"){
                    myWriter.write("Linha " + t.getLine() + ": cadeia literal nao fechada\n");
                    error = true;
                }
                else
                    myWriter.write("<" + '\'' + t.getText() + '\'' + "," + type + ">" + "\n");
            }

            myWriter.close();
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
}
