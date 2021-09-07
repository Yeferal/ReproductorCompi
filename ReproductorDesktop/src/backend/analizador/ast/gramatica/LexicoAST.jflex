package backend.analizador.ast.gramatica;
import backend.analizador.ErrorLSS;
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
                //System.out.println("INDENT: "+(ambito+1));
                return new Symbol(SimbolosAST.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                //System.out.println("DEDENT: "+(ambito-1));
                return new Symbol(SimbolosAST.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                //System.out.println("SALTO DE LINEA: "+ambito);
            }else{
                //ErrorLSS de identacion
                //System.out.println("ErrorLSS de identacion");
                //agregarError(new ErrorLSS(yyline + 1,yycolumn + 1, yytext(), "ErrorLSS de Indentacion", "Sintactico"));
            }
        }
        return null;
    }

    public Symbol symbol(int size){
        size = (size/4);
        if(!pilaAmbitos.isEmpty()){
            int ambito = pilaAmbitos.peek();
            if((ambito+1)==size){
                pilaAmbitos.push(ambito+1);
                //System.out.println("IDENT: "+(ambito+1));
                return new Symbol(SimbolosAST.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                //System.out.println("DEDENT: "+(ambito-1));
                return new Symbol(SimbolosAST.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                //System.out.println("SALTO DE LINEA: "+ambito);
            }else{
                //ErrorLSS de identacion
                //System.out.println("ErrorLSS de identacion");
                //agregarError(new ErrorLSS(yyline + 1,yycolumn + 1, yytext(), "ErrorLSS de Indentacion", "Sintactico"));
            }
        }
        return null;
    }

%}

    //directivas
%public 
%class AnalizadorLexicoAST
%cupsym SimbolosAST
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
TabsS                = ("    ")+
%%

<YYINITIAL> {
    //("â")*          {/*Ignore*/}

    //Comentarios
    //({CommentSimple}})                 {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosAST.COMENTARIO_LINEA , yycolumn, yyline, yytext());}
    ({CommentBloque})                       {/*printConsole("COMENTARIO: "+yytext()+"\n");*/ /*return new Symbol(SimbolosAST.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());*/}

    (">>" ({LqSea}|{Blancos})* "\n")        {/*printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n");*/ /*return new Symbol(SimbolosAST.COMENTARIO_LINEA , yycolumn, yyline, yytext());*/}
    //("<-"({LqSea}|"\n")* "->")            {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosAST.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());}

    //IDENTACIONES
    ({Tabs})                                { Symbol sim = symbol(yytext(), yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}
    ({TabsS})                               { Symbol sim = symbol(yytext(), yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}

    //signos o SimbolosAST reservados
    "("                                     {return new Symbol(SimbolosAST.PA_A , yycolumn, yyline, yytext());}
    ")"                                     {return new Symbol(SimbolosAST.PA_C , yycolumn, yyline, yytext());}
    "["                                     {return new Symbol(SimbolosAST.CORCHETE_A , yycolumn, yyline, yytext());}
    "]"                                     {return new Symbol(SimbolosAST.CORCHETE_C , yycolumn, yyline, yytext());}
    "{"                                     {return new Symbol(SimbolosAST.LLAVE_A , yycolumn, yyline, yytext());}
    "}"                                     {return new Symbol(SimbolosAST.LLAVE_C , yycolumn, yyline, yytext());}
    ","                                     {return new Symbol(SimbolosAST.COMA , yycolumn, yyline, yytext());}
    ";"                                     {return new Symbol(SimbolosAST.PUNTO_COMA , yycolumn, yyline, yytext());}
    "="                                     {return new Symbol(SimbolosAST.IGUAL , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {return new Symbol(SimbolosAST.MAS , yycolumn, yyline, yytext());}
    "-"                                     {return new Symbol(SimbolosAST.MENOS , yycolumn, yyline, yytext());}
    "*"                                     {return new Symbol(SimbolosAST.POR , yycolumn, yyline, yytext());}
    "/"                                     {return new Symbol(SimbolosAST.DIVISION , yycolumn, yyline, yytext());}
    "%"                                     {return new Symbol(SimbolosAST.MODULO , yycolumn, yyline, yytext());}
    "^"                                     {return new Symbol(SimbolosAST.POTENCIA , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {return new Symbol(SimbolosAST.IGUAL_IGUAL , yycolumn, yyline, yytext());}
    "!="                                    {return new Symbol(SimbolosAST.DIFERENTE , yycolumn, yyline, yytext());}
    ">"                                     {return new Symbol(SimbolosAST.MAYOR_Q , yycolumn, yyline, yytext());}
    "<"                                     {return new Symbol(SimbolosAST.MENOR_Q , yycolumn, yyline, yytext());}
    ">="                                    {return new Symbol(SimbolosAST.MAYOR_IGUAL , yycolumn, yyline, yytext());}
    "<="                                    {return new Symbol(SimbolosAST.MENOR_IGUAL , yycolumn, yyline, yytext());}
    "!!"                                    {return new Symbol(SimbolosAST.IS_NULL , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {return new Symbol(SimbolosAST.AND , yycolumn, yyline, yytext());}
    "!&&"                                   {return new Symbol(SimbolosAST.NAND , yycolumn, yyline, yytext());}
    "||"                                    {return new Symbol(SimbolosAST.OR , yycolumn, yyline, yytext());}
    "!||"                                   {return new Symbol(SimbolosAST.NOR , yycolumn, yyline, yytext());}
    "&|"                                    {return new Symbol(SimbolosAST.XOR , yycolumn, yyline, yytext());}
    "!"                                     {return new Symbol(SimbolosAST.NOT , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {return new Symbol(SimbolosAST.MAS_IGUAL , yycolumn, yyline, yytext());}
    "++"                                    {return new Symbol(SimbolosAST.MAS_MAS , yycolumn, yyline, yytext());}
    "--"                                    {return new Symbol(SimbolosAST.MENOS_MENOS , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {return new Symbol(SimbolosAST.DO , yycolumn, yyline, yytext());}
    "Do#"                                   {return new Symbol(SimbolosAST.DO_S , yycolumn, yyline, yytext());}
    "Re"                                    {return new Symbol(SimbolosAST.RE , yycolumn, yyline, yytext());}
    "Re#"                                   {return new Symbol(SimbolosAST.RE_S , yycolumn, yyline, yytext());}
    "Mi"                                    {return new Symbol(SimbolosAST.MI , yycolumn, yyline, yytext());}
    "Fa"                                    {return new Symbol(SimbolosAST.FA , yycolumn, yyline, yytext());}
    "Fa#"                                   {return new Symbol(SimbolosAST.FA_S , yycolumn, yyline, yytext());}
    "Sol"                                   {return new Symbol(SimbolosAST.SOL , yycolumn, yyline, yytext());}
    "Sol#"                                  {return new Symbol(SimbolosAST.SOL_S , yycolumn, yyline, yytext());}
    "La"                                    {return new Symbol(SimbolosAST.LA , yycolumn, yyline, yytext());}
    "La#"                                   {return new Symbol(SimbolosAST.LA_S , yycolumn, yyline, yytext());}
    "Si"                                    {return new Symbol(SimbolosAST.SI , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {return new Symbol(SimbolosAST.PISTA, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {return new Symbol(SimbolosAST.EXTIENDE , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {return new Symbol(SimbolosAST.KEEP , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {return new Symbol(SimbolosAST.VAR , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {return new Symbol(SimbolosAST.CADENA_RSV , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {return new Symbol(SimbolosAST.ENTERO , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {return new Symbol(SimbolosAST.DOBLE , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {return new Symbol(SimbolosAST.BOOLEAN , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {return new Symbol(SimbolosAST.CARACTER_RSV , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {return new Symbol(SimbolosAST.ARREGLO , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {return new Symbol(SimbolosAST.SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"ino")                        {return new Symbol(SimbolosAST.SINO , yycolumn, yyline, yytext());}
    (("S"|"s")"ino si")                     {return new Symbol(SimbolosAST.SINO_SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"witch")                      {return new Symbol(SimbolosAST.SWITCH , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {return new Symbol(SimbolosAST.CASO , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {return new Symbol(SimbolosAST.SALIR , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {return new Symbol(SimbolosAST.DEFAULT , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {return new Symbol(SimbolosAST.PARA , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {return new Symbol(SimbolosAST.MIENTRAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {return new Symbol(SimbolosAST.HACER , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {return new Symbol(SimbolosAST.CONTINUAR , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {return new Symbol(SimbolosAST.RETORNAR , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {return new Symbol(SimbolosAST.VOID , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {return new Symbol(SimbolosAST.REPRODUCIR , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {return new Symbol(SimbolosAST.ESPERAR , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {return new Symbol(SimbolosAST.ORDENAR , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {return new Symbol(SimbolosAST.ASCENDENTE , yycolumn, yyline, yytext());}
    (("D"|"d")"escendente")                 {return new Symbol(SimbolosAST.DESCENDENTE , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {return new Symbol(SimbolosAST.PARES , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {return new Symbol(SimbolosAST.IMPARES , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {return new Symbol(SimbolosAST.PRIMOS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {return new Symbol(SimbolosAST.SUMARIZAR , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {return new Symbol(SimbolosAST.LONGITUD , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {return new Symbol(SimbolosAST.MENSAJE , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {return new Symbol(SimbolosAST.PRINCIPAL , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {return new Symbol(SimbolosAST.FALSE , yycolumn, yyline, false);}
    (("T"|"t")"rue")                        {return new Symbol(SimbolosAST.TRUE , yycolumn, yyline, true);}
    (("F"|"f")"also")                       {return new Symbol(SimbolosAST.FALSE , yycolumn, yyline, false);}
    (("V"|"v")"erdadero")                   {return new Symbol(SimbolosAST.TRUE , yycolumn, yyline, true);}

    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {return new Symbol(SimbolosAST.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {return new Symbol(SimbolosAST.NUMERO , yycolumn, yyline, new Integer(yytext()));}
    ({Numero}("\."){Numero})                {return new Symbol(SimbolosAST.DECIMAL , yycolumn, yyline, new Double(yytext()));}
    ({Cadena})                              {return new Symbol(SimbolosAST.CADENA , yycolumn, yyline, yytext().substring(1, yytext().length()-1));}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {return new Symbol(SimbolosAST.CARACTER , yycolumn, yyline, yytext().substring(1, yytext().length()-1));}
    ("'"({Letra}|{Signo}|{Digito})"'")      {return new Symbol(SimbolosAST.CARACTER , yycolumn, yyline, yytext().substring(1, yytext().length()-1));}


    {Espacio}                               {/*Ignore*/}
    .                                       {/*Ignore*/}
    
}

