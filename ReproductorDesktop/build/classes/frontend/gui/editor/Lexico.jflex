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
%unicode
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
Letra               = [a-záéíóúA-ZÁÉÍÓÚÑñ]
Digito              = [0-9]
Numero              = {Digito} {Digito}*
Palabra             = {Letra}+
Cadena              = ("\"" ([^\"]* | {Letra}) "\"" )
LqSea               =  ({Signo}|{Letra}|{Numero}|{Cadena})*
Espacio             = [" "\r\t\b\n]+
//Salto               = [\n\t\r" "]+
%%

<YYINITIAL> {
    //("â")*          {/*Ignore*/}

    //Comentarios
    (">>"({LqSea})* "\n")                 {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosCode.COMENTARIO_LINEA , yycolumn, yyline, yytext());}
    ("<-"({LqSea}|"\n")* "->")            {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosCode.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());}

    //signos o simbolos reservados
    "("                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.PA_A , yycolumn, yyline, yytext());}
    ")"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.PA_C , yycolumn, yyline, yytext());}
    "["                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.CORCHETE_A , yycolumn, yyline, yytext());}
    "]"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.CORCHETE_C , yycolumn, yyline, yytext());}
    "{"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.LLAVE_A , yycolumn, yyline, yytext());}
    "}"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.LLAVE_C , yycolumn, yyline, yytext());}
    ","                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.COMA , yycolumn, yyline, yytext());}
    ";"                                     {printConsole(yytext()+"\n"); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.PUNTO_COMA , yycolumn, yyline, yytext());}
    "="                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.IGUAL , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MAS , yycolumn, yyline, yytext());}
    "-"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MENOS , yycolumn, yyline, yytext());}
    "*"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.POR , yycolumn, yyline, yytext());}
    "/"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.DIVISION , yycolumn, yyline, yytext());}
    "%"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MODULO , yycolumn, yyline, yytext());}
    "^"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.POTENCIA , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.IGUAL_IGUAL , yycolumn, yyline, yytext());}
    "!="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.DIFERENTE , yycolumn, yyline, yytext());}
    ">"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MAYOR_Q , yycolumn, yyline, yytext());}
    "<"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MENOR_Q , yycolumn, yyline, yytext());}
    ">="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MAYOR_IGUAL , yycolumn, yyline, yytext());}
    "<="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MENOR_IGUAL , yycolumn, yyline, yytext());}
    "!!"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.IS_NULL , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.AND , yycolumn, yyline, yytext());}
    "!&&"                                   {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.NAND , yycolumn, yyline, yytext());}
    "||"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.OR , yycolumn, yyline, yytext());}
    "!||"                                   {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.NOR , yycolumn, yyline, yytext());}
    "&|"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.XOR , yycolumn, yyline, yytext());}
    "!"                                     {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.NOT , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MAS_IGUAL , yycolumn, yyline, yytext());}
    "++"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MAS_MAS , yycolumn, yyline, yytext());}
    "--"                                    {printConsole(yytext()); pintar.pintaNegro((int) yychar,yylength()); return new Symbol(SimbolosCode.MENOS_MENOS , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.DO , yycolumn, yyline, yytext());}
    "Do#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.DO_S , yycolumn, yyline, yytext());}
    "Re"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.RE , yycolumn, yyline, yytext());}
    "Re#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.RE_S , yycolumn, yyline, yytext());}
    "Mi"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.MI , yycolumn, yyline, yytext());}
    "Fa"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.FA , yycolumn, yyline, yytext());}
    "Fa#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.FA_S , yycolumn, yyline, yytext());}
    "Sol"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SOL , yycolumn, yyline, yytext());}
    "Sol#"                                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SOL_S , yycolumn, yyline, yytext());}
    "La"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.LA , yycolumn, yyline, yytext());}
    "La#"                                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.LA_S , yycolumn, yyline, yytext());}
    "Si"                                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SI , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PISTA, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.EXTIENDE , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.KEEP , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.VAR , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.CADENA_RSV , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.ENTERO , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.DOBLE , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.BOOLEAN , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.CARACTER_RSV , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.ARREGLO , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SI , yycolumn, yyline, yytext());}
    (("S"|"s")"ino")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SINO , yycolumn, yyline, yytext());}
    (("S"|"s")"witch")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SWITCH , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.CASO , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SALIR , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.DEFAULT , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PARA , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.MIENTRAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.HACER , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.CONTINUAR , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.RETORNAR , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.VOID , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.REPRODUCIR , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.ESPERAR , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.ORDENAR , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.ASCENDENTE , yycolumn, yyline, yytext());}
    (("A"|"a")"escendente")                 {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.DESCENDENTE , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PARES , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.IMPARES , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PRIMOS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.SUMARIZAR , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.LONGITUD , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.MENSAJE , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PRINCIPAL , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.FALSE , yycolumn, yyline, yytext());}
    (("T"|"t")"rue")                        {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.TRUE , yycolumn, yyline, yytext());}

 /*   ("lista"|"nombre")                      {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("random"|"circular")                   {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("pistas"|"true")                       {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("false")                               {printConsole(yytext()); pintar.pintaAzul((int) yychar,yylength()); return new Symbol(SimbolosCode.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
*/
    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); pintar.pintaVerde((int) yychar,yylength()); return new Symbol(SimbolosCode.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SimbolosCode.NUMERO , yycolumn, yyline, yytext());}
    ({Numero}("\."){Numero})                 {printConsole(yytext()); pintar.pintaMorado((int) yychar,yylength()); return new Symbol(SimbolosCode.DECIMAL , yycolumn, yyline, yytext());}
    ({Cadena})                              {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CADENA , yycolumn, yyline, yytext());}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CARACTER , yycolumn, yyline, yytext());}
    ("'"({Letra}|{Signo}|{Digito})"'")      {printConsole(yytext()); pintar.pintaNaranja((int) yychar,yylength()); return new Symbol(SimbolosCode.CARACTER , yycolumn, yyline, yytext());}


    {Espacio}                               {/*Ignore*/}
    .                           {//System.out.println("CUALQUIER_SIM: "+yytext()); 
                                    //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","Error Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                    //listaErrores.add(e);
                                    //return new Symbol(Simbolos.CUALQUIER_SIM , yycolumn, yyline, yytext());
                                            printConsole("ERROR"+yytext()); pintar.pintaNegro((int) yychar,yylength()); /*return new Symbol(SimbolosCode.OTROS , yycolumn, yyline, yytext());*/
                                    }
    
}
