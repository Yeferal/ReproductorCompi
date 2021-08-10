package gui.editor;

import java_cup.runtime.*;
import java.util.ArrayList;

%%
%{
    //coidgo de usuario en sintaxis java
    public void printConsole(String s){
        System.out.print(s);
    }
    String cadena="";
    public Pintar pintar = new Pintar();

%}

    //directivas
%public 
%class AnalizadorLexicoCode
%cupsym SimbolosCode
%cup
%char
//%unicode
%line
%column
%full
//%ignorecase
//%unicode

//expreciones regulares

// Color           Token
// Azul        ->  Palabras reservadas
// Verde       ->  Identificadores
// Naranja     ->  Cadenas, caracteres
// Morado      ->  Números
// Gris        ->  Comentario
// Negro       ->  Otro



Signo               = [-_.!@#%&*|/]
Letra               = ([a-zA-Z] | ñ | Ñ )
Digito              = [0-9]
Numero              = {Digito} {Digito}*
Palabra             = {Letra}+
Cadena              = ("\"" [^\"]* "\"" )
LqSea               =  ({Signo}|{Letra}|{Numero})*
Espacio             = [" "\r\t\b\n]+
//Salto               = [\n\t\r" "]+
%%

<YYINITIAL> {
    //Comentarios
    (">>"({LqSea}|.)* "\n")                 {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosCode.COMENTARIO_SIMPLE , yycolumn, yyline, yytext());}
    ("<-"({LqSea}|.|"\n")* "->")            {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosCode.COMENTARIO , yycolumn, yyline, yytext());}



    //Palabras Reservadas
    (("P"|"p") "ista")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("AND"|"NAND")                          {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("OR"|"NOR")                            {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("XOR"|"NOT")                           {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"ino")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"ion")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"witch")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("lista"|"nombre")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("random"|"circular")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("pistas"|"true")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("false")                               {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}

    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); pintar.pintaVerde((int) yychar,yylength()); return new Symbol(SimbolosCode.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SimbolosCode.NUMERO , yycolumn, yyline, yytext());}
    ({Numero}("."){Numero})                 {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SimbolosCode.NUMERO , yycolumn, yyline, yytext());}
    ({Cadena})                              {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CADENA , yycolumn, yyline, yytext());}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CARACTER , yycolumn, yyline, yytext());}
    ("'"({Letra}|{Signo}|{Digito})"'")      {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CARACTER , yycolumn, yyline, yytext());}


    {Espacio}                               {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.OTROS , yycolumn, yyline, yytext());}
    .                           {//System.out.println("CUALQUIER_SIM: "+yytext()); 
                                    //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                    //listaErrores.add(e);
                                    //return new Symbol(Simbolos.CUALQUIER_SIM , yycolumn, yyline, yytext());
                                            printConsole("ERROR"+yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.OTROS , yycolumn, yyline, yytext());
                                    }
    
}
