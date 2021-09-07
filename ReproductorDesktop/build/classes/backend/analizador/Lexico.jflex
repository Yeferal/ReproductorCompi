package backend.analizador;

import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.Stack;

%%
%{

    public ArrayList<ErrorLSS> listaErrores = new ArrayList<>();

    private void agregarError(ErrorLSS errorL){
        listaErrores.add(errorL);
    }

    Stack<Integer> pilaAmbitos = new Stack();
    public void iniciar(){
        pilaAmbitos.push(0);
    }
    //coidgo de usuario en sintaxis java
    public void printConsole(String s){
        //System.out.println(s);
    }
    String cadena="";
    //public Pintar pintar = new Pintar();

    //para la identacion
    boolean estadoIdent = false;
    int contadorAmbito=0, ambitoActual=0;

    public Symbol symbol(String value, int size){
        if(!pilaAmbitos.isEmpty()){
            int ambito = pilaAmbitos.peek();
            if((ambito+1)==size){
                pilaAmbitos.push(ambito+1);
                System.out.println(value+"INDENT: "+(ambito+1));
                return new Symbol(Simbolos.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                System.out.println(value+"DEDENT: "+(ambito-1));
                return new Symbol(Simbolos.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                System.out.println(value+"SALTO DE LINEA: "+ambito);
            }else{
                //ErrorLSS de identacion
                System.out.println(value+"Error de indentacion");
                agregarError(new ErrorLSS(yyline + 1,yycolumn + 1, yytext(), "Error de Indentacion", "Sintactico"));
            }
        }
        return null;
    }

    public Symbol symbol(int size){
        System.out.println("\tTabs!: "+size);
        size = (size/4);
        System.out.println("\tTabsS: "+size);
        if(!pilaAmbitos.isEmpty()){
            int ambito = pilaAmbitos.peek();
            if((ambito+1)==size){
                pilaAmbitos.push(ambito+1);
                System.out.println("IDENT: "+(ambito+1));
                return new Symbol(Simbolos.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                System.out.println("DEDENT: "+(ambito-1));
                return new Symbol(Simbolos.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                System.out.println("SALTO DE LINEA: "+ambito);
            }else{
                //ErrorLSS de identacion
                System.out.println("Error de identacion");
                agregarError(new ErrorLSS(yyline + 1,yycolumn + 1, yytext(), "Error de Indentacion", "Sintactico"));
            }
        }
        return null;
    }

%}

    //directivas
%public 
%class AnalizadorLexico
%cupsym Simbolos
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

LineTerminator = \r|\n|\r\n
InputCaracter = [^\r\n]
//WhiteSpace = {LineTerminator} | [ \t\f]

/*Comentarios*/
//Comment = {ComentarioTradicional} | {FinLineaComment} | {DocumentoComentado}
CommentBloque = ("<-" [^*] ~"->" | "<-" "-"+ ">")
CommentSimple = (">" ">" {InputCaracter}* {LineTerminator}?)
//CommentContenido = ( [^*] | \*+ [^/*] )*


Signo               = [-_.!@#%&*|/]
Letra               = [a-záéíóúA-ZÁÉÍÓÚÑñ]
Digito              = [0-9]
Numero              = {Digito} {Digito}*
Palabra             = {Letra}+
Cadena              = ("\"" ([^\"]* | {Letra}) "\"" )
LqSea               =  ({Signo}|{Letra}|{Numero}|{Cadena}|.)*
Espacio             = [" "\r\b\n]+
Blancos               = [" "\r\b\f""]
Tabs                = [\t]+
TabsS                = ("    "|\b\b\b\b)+
%%

<YYINITIAL> {
    //("â")*          {/*Ignore*/}

    //Comentarios
    //({CommentSimple}})                 {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(Simbolos.COMENTARIO_LINEA , yycolumn, yyline, yytext());}
    ({CommentBloque})                       {printConsole("COMENTARIO: "+yytext()+"\n"); /*return new Symbol(Simbolos.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());*/}

    (">>" ({LqSea}|{Blancos})* "\n")        {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); /*return new Symbol(Simbolos.COMENTARIO_LINEA , yycolumn, yyline, yytext());*/}
    //("<-"({LqSea}|"\n")* "->")            {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(Simbolos.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());}

    //IDENTACIONES
    ({Tabs})                                { Symbol sim = symbol(yytext(), yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}
    ({TabsS}+)                               {/*System.out.println("TABESPACIOS: Linea: "+yyline+", col: "+yycolumn);*/ Symbol sim = symbol(yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}

    //signos o simbolos reservados
    "("                                     {printConsole(yytext()+"\n"); return new Symbol(Simbolos.PA_A , yycolumn, yyline, yytext());}
    ")"                                     {printConsole(yytext()); return new Symbol(Simbolos.PA_C , yycolumn, yyline, yytext());}
    "["                                     {printConsole(yytext()+"\n"); return new Symbol(Simbolos.CORCHETE_A , yycolumn, yyline, yytext());}
    "]"                                     {printConsole(yytext()); return new Symbol(Simbolos.CORCHETE_C , yycolumn, yyline, yytext());}
    "{"                                     {printConsole(yytext()+"\n"); return new Symbol(Simbolos.LLAVE_A , yycolumn, yyline, yytext());}
    "}"                                     {printConsole(yytext()+"\n"); return new Symbol(Simbolos.LLAVE_C , yycolumn, yyline, yytext());}
    ","                                     {printConsole(yytext()); return new Symbol(Simbolos.COMA , yycolumn, yyline, yytext());}
    ";"                                     {printConsole(yytext()+"\n"); return new Symbol(Simbolos.PUNTO_COMA , yycolumn, yyline, yytext());}
    "="                                     {printConsole(yytext()); return new Symbol(Simbolos.IGUAL , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {printConsole(yytext()); return new Symbol(Simbolos.MAS , yycolumn, yyline, yytext());}
    "-"                                     {printConsole(yytext()); return new Symbol(Simbolos.MENOS , yycolumn, yyline, yytext());}
    "*"                                     {printConsole(yytext()); return new Symbol(Simbolos.POR , yycolumn, yyline, yytext());}
    "/"                                     {printConsole(yytext()); return new Symbol(Simbolos.DIVISION , yycolumn, yyline, yytext());}
    "%"                                     {printConsole(yytext()); return new Symbol(Simbolos.MODULO , yycolumn, yyline, yytext());}
    "^"                                     {printConsole(yytext()); return new Symbol(Simbolos.POTENCIA , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {printConsole(yytext()); return new Symbol(Simbolos.IGUAL_IGUAL , yycolumn, yyline, yytext());}
    "!="                                    {printConsole(yytext()); return new Symbol(Simbolos.DIFERENTE , yycolumn, yyline, yytext());}
    ">"                                     {printConsole(yytext()); return new Symbol(Simbolos.MAYOR_Q , yycolumn, yyline, yytext());}
    "<"                                     {printConsole(yytext()); return new Symbol(Simbolos.MENOR_Q , yycolumn, yyline, yytext());}
    ">="                                    {printConsole(yytext()); return new Symbol(Simbolos.MAYOR_IGUAL , yycolumn, yyline, yytext());}
    "<="                                    {printConsole(yytext()); return new Symbol(Simbolos.MENOR_IGUAL , yycolumn, yyline, yytext());}
    "!!"                                    {printConsole(yytext()); return new Symbol(Simbolos.IS_NULL , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {printConsole(yytext()); return new Symbol(Simbolos.AND , yycolumn, yyline, yytext());}
    "!&&"                                   {printConsole(yytext()); return new Symbol(Simbolos.NAND , yycolumn, yyline, yytext());}
    "||"                                    {printConsole(yytext()); return new Symbol(Simbolos.OR , yycolumn, yyline, yytext());}
    "!||"                                   {printConsole(yytext()); return new Symbol(Simbolos.NOR , yycolumn, yyline, yytext());}
    "&|"                                    {printConsole(yytext()); return new Symbol(Simbolos.XOR , yycolumn, yyline, yytext());}
    "!"                                     {printConsole(yytext()); return new Symbol(Simbolos.NOT , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {printConsole(yytext()); return new Symbol(Simbolos.MAS_IGUAL , yycolumn, yyline, yytext());}
    "++"                                    {printConsole(yytext()); return new Symbol(Simbolos.MAS_MAS , yycolumn, yyline, yytext());}
    "--"                                    {printConsole(yytext()); return new Symbol(Simbolos.MENOS_MENOS , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {printConsole(yytext()); return new Symbol(Simbolos.DO , yycolumn, yyline, yytext());}
    "Do#"                                   {printConsole(yytext()); return new Symbol(Simbolos.DO_S , yycolumn, yyline, yytext());}
    "Re"                                    {printConsole(yytext()); return new Symbol(Simbolos.RE , yycolumn, yyline, yytext());}
    "Re#"                                   {printConsole(yytext()); return new Symbol(Simbolos.RE_S , yycolumn, yyline, yytext());}
    "Mi"                                    {printConsole(yytext()); return new Symbol(Simbolos.MI , yycolumn, yyline, yytext());}
    "Fa"                                    {printConsole(yytext()); return new Symbol(Simbolos.FA , yycolumn, yyline, yytext());}
    "Fa#"                                   {printConsole(yytext()); return new Symbol(Simbolos.FA_S , yycolumn, yyline, yytext());}
    "Sol"                                   {printConsole(yytext()); return new Symbol(Simbolos.SOL , yycolumn, yyline, yytext());}
    "Sol#"                                  {printConsole(yytext()); return new Symbol(Simbolos.SOL_S , yycolumn, yyline, yytext());}
    "La"                                    {printConsole(yytext()); return new Symbol(Simbolos.LA , yycolumn, yyline, yytext());}
    "La#"                                   {printConsole(yytext()); return new Symbol(Simbolos.LA_S , yycolumn, yyline, yytext());}
    "Si"                                    {printConsole(yytext()); return new Symbol(Simbolos.SI , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {printConsole(yytext()); return new Symbol(Simbolos.PISTA, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {printConsole(yytext()); return new Symbol(Simbolos.EXTIENDE , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {printConsole(yytext()); return new Symbol(Simbolos.KEEP , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {printConsole(yytext()); return new Symbol(Simbolos.VAR , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {printConsole(yytext()); return new Symbol(Simbolos.CADENA_RSV , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {printConsole(yytext()); return new Symbol(Simbolos.ENTERO , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {printConsole(yytext()); return new Symbol(Simbolos.DOBLE , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {printConsole(yytext()); return new Symbol(Simbolos.BOOLEAN , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {printConsole(yytext()); return new Symbol(Simbolos.CARACTER_RSV , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {printConsole(yytext()); return new Symbol(Simbolos.ARREGLO , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {printConsole(yytext()); return new Symbol(Simbolos.SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"ino")                        {printConsole(yytext()); return new Symbol(Simbolos.SINO , yycolumn, yyline, yytext());}
    (("S"|"s")"ino si")                     {printConsole(yytext()); return new Symbol(Simbolos.SINO_SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"witch")                      {printConsole(yytext()); return new Symbol(Simbolos.SWITCH , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {printConsole(yytext()); return new Symbol(Simbolos.CASO , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {printConsole(yytext()); return new Symbol(Simbolos.SALIR , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {printConsole(yytext()); return new Symbol(Simbolos.DEFAULT , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {printConsole(yytext()); return new Symbol(Simbolos.PARA , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {printConsole(yytext()); return new Symbol(Simbolos.MIENTRAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {printConsole(yytext()); return new Symbol(Simbolos.HACER , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {printConsole(yytext()); return new Symbol(Simbolos.CONTINUAR , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {printConsole(yytext()); return new Symbol(Simbolos.RETORNAR , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {printConsole(yytext()); return new Symbol(Simbolos.VOID , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {printConsole(yytext()); return new Symbol(Simbolos.REPRODUCIR , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {printConsole(yytext()); return new Symbol(Simbolos.ESPERAR , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {printConsole(yytext()); return new Symbol(Simbolos.ORDENAR , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {printConsole(yytext()); return new Symbol(Simbolos.ASCENDENTE , yycolumn, yyline, yytext());}
    (("D"|"d")"escendente")                 {printConsole(yytext()); return new Symbol(Simbolos.DESCENDENTE , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {printConsole(yytext()); return new Symbol(Simbolos.PARES , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {printConsole(yytext()); return new Symbol(Simbolos.IMPARES , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {printConsole(yytext()); return new Symbol(Simbolos.PRIMOS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {printConsole(yytext()); return new Symbol(Simbolos.SUMARIZAR , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {printConsole(yytext()); return new Symbol(Simbolos.LONGITUD , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {printConsole(yytext()); return new Symbol(Simbolos.MENSAJE , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {printConsole(yytext()); return new Symbol(Simbolos.PRINCIPAL , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {printConsole(yytext()); return new Symbol(Simbolos.FALSE , yycolumn, yyline, yytext());}
    (("T"|"t")"rue")                        {printConsole(yytext()); return new Symbol(Simbolos.TRUE , yycolumn, yyline, yytext());}
    (("F"|"f")"also")                       {printConsole(yytext()); return new Symbol(Simbolos.FALSE , yycolumn, yyline, yytext());}
    (("V"|"v")"erdadero")                   {printConsole(yytext()); return new Symbol(Simbolos.TRUE , yycolumn, yyline, yytext());}

 /*   ("lista"|"nombre")                      {printConsole(yytext()); return new Symbol(Simbolos.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("random"|"circular")                   {printConsole(yytext()); return new Symbol(Simbolos.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("pistas"|"true")                       {printConsole(yytext()); return new Symbol(Simbolos.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
    ("false")                               {printConsole(yytext()); return new Symbol(Simbolos.PALABRAS_RESERVADAS , yycolumn, yyline, yytext());}
*/
    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {printConsole(yytext()); return new Symbol(Simbolos.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {printConsole(yytext()); return new Symbol(Simbolos.NUMERO , yycolumn, yyline, yytext());}
    ({Numero}("\."){Numero})                {printConsole(yytext()); return new Symbol(Simbolos.DECIMAL , yycolumn, yyline, yytext());}
    ({Cadena})                              {printConsole(yytext()); return new Symbol(Simbolos.CADENA , yycolumn, yyline, yytext());}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {printConsole(yytext()); return new Symbol(Simbolos.CARACTER , yycolumn, yyline, yytext());}
    ("'"({Letra}|{Signo}|{Digito})"'")      {printConsole(yytext()); return new Symbol(Simbolos.CARACTER , yycolumn, yyline, yytext());}


    {Espacio}                               {/*Ignore*/}
    .                           {//System.out.println("CUALQUIER_SIM: "+yytext()); 
                                    //ErrorG e = new ErrorG(yyline+1, yycolumn+1,yytext(),"Lexico","ErrorLSS Lexico token: " + yytext()+"   Linea: " + (yyline+1) + " ,    Columna: " + (yycolumn+1));
                                    //listaErrores.add(e);
                                    //return new Symbol(Simbolos.CUALQUIER_SIM , yycolumn, yyline, yytext());
                                            //printConsole("ERR: "+yytext()+"\n"); /*return new Symbol(Simbolos.OTROS , yycolumn, yyline, yytext());*/
                                    }
    
}
