package backend.analizador.comprobaciones.tablaSimbolos;
import backend.analizador.ErrorLexico;
import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.Stack;

%%
%{

    public ArrayList<ErrorLexico> listaErrores = new ArrayList<>();

    private void agregarError(ErrorLexico errorL){
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
                return new Symbol(SimbolosTS.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                //System.out.println("DEDENT: "+(ambito-1));
                return new Symbol(SimbolosTS.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                //System.out.println("SALTO DE LINEA: "+ambito);
            }else{
                //error de identacion
                //System.out.println("Error de identacion");
                //agregarError(new ErrorLexico(yyline + 1,yycolumn + 1, yytext(), "Error de Indentacion", "Sintactico"));
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
                return new Symbol(SimbolosTS.INDENT , yycolumn, yyline, yytext());
            }else if((ambito-1)==size){
                pilaAmbitos.pop();
                //System.out.println("DEDENT: "+(ambito-1));
                return new Symbol(SimbolosTS.DEDENT , yycolumn, yyline, yytext());
            }else if(ambito==size){
                //UN SALATO DE LINEA
                //System.out.println("SALTO DE LINEA: "+ambito);
            }else{
                //error de identacion
                //System.out.println("Error de identacion");
                //agregarError(new ErrorLexico(yyline + 1,yycolumn + 1, yytext(), "Error de Indentacion", "Sintactico"));
            }
        }
        return null;
    }

%}

    //directivas
%public 
%class AnalizadorLexicoTS
%cupsym SimbolosTS
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
    //({CommentSimple}})                 {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosTS.COMENTARIO_LINEA , yycolumn, yyline, yytext());}
    ({CommentBloque})                       {printConsole("COMENTARIO: "+yytext()+"\n"); /*return new Symbol(SimbolosTS.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());*/}

    (">>" ({LqSea}|{Blancos})* "\n")        {printConsole("COMENTARIO_SIMPLE: "+yytext()+"\n"); /*return new Symbol(SimbolosTS.COMENTARIO_LINEA , yycolumn, yyline, yytext());*/}
    //("<-"({LqSea}|"\n")* "->")            {printConsole("COMENTARIO: "+yytext()+"\n"); pintar.pintaGris((int) yychar,yylength()); return new Symbol(SimbolosTS.COMENTARIO_BLOQUE , yycolumn, yyline, yytext());}

    //IDENTACIONES
    ({Tabs})                                { Symbol sim = symbol(yytext(), yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}
    ({TabsS})                               { Symbol sim = symbol(yytext(), yytext().length()); if(sim!=null){return sim;}else{/*IGNORAR*/}}

    //signos o SimbolosTS reservados
    "("                                     {return new Symbol(SimbolosTS.PA_A , yycolumn, yyline, yytext());}
    ")"                                     {return new Symbol(SimbolosTS.PA_C , yycolumn, yyline, yytext());}
    "["                                     {return new Symbol(SimbolosTS.CORCHETE_A , yycolumn, yyline, yytext());}
    "]"                                     {return new Symbol(SimbolosTS.CORCHETE_C , yycolumn, yyline, yytext());}
    "{"                                     {return new Symbol(SimbolosTS.LLAVE_A , yycolumn, yyline, yytext());}
    "}"                                     {return new Symbol(SimbolosTS.LLAVE_C , yycolumn, yyline, yytext());}
    ","                                     {return new Symbol(SimbolosTS.COMA , yycolumn, yyline, yytext());}
    ";"                                     {return new Symbol(SimbolosTS.PUNTO_COMA , yycolumn, yyline, yytext());}
    "="                                     {return new Symbol(SimbolosTS.IGUAL , yycolumn, yyline, yytext());}

    //operadores aritmeticos
    "+"                                     {return new Symbol(SimbolosTS.MAS , yycolumn, yyline, yytext());}
    "-"                                     {return new Symbol(SimbolosTS.MENOS , yycolumn, yyline, yytext());}
    "*"                                     {return new Symbol(SimbolosTS.POR , yycolumn, yyline, yytext());}
    "/"                                     {return new Symbol(SimbolosTS.DIVISION , yycolumn, yyline, yytext());}
    "%"                                     {return new Symbol(SimbolosTS.MODULO , yycolumn, yyline, yytext());}
    "^"                                     {return new Symbol(SimbolosTS.POTENCIA , yycolumn, yyline, yytext());}

    //operadores relacionales
    "=="                                    {return new Symbol(SimbolosTS.IGUAL_IGUAL , yycolumn, yyline, yytext());}
    "!="                                    {return new Symbol(SimbolosTS.DIFERENTE , yycolumn, yyline, yytext());}
    ">"                                     {return new Symbol(SimbolosTS.MAYOR_Q , yycolumn, yyline, yytext());}
    "<"                                     {return new Symbol(SimbolosTS.MENOR_Q , yycolumn, yyline, yytext());}
    ">="                                    {return new Symbol(SimbolosTS.MAYOR_IGUAL , yycolumn, yyline, yytext());}
    "<="                                    {return new Symbol(SimbolosTS.MENOR_IGUAL , yycolumn, yyline, yytext());}
    "!!"                                    {return new Symbol(SimbolosTS.IS_NULL , yycolumn, yyline, yytext());}

    //operadores logicos
    "&&"                                    {return new Symbol(SimbolosTS.AND , yycolumn, yyline, yytext());}
    "!&&"                                   {return new Symbol(SimbolosTS.NAND , yycolumn, yyline, yytext());}
    "||"                                    {return new Symbol(SimbolosTS.OR , yycolumn, yyline, yytext());}
    "!||"                                   {return new Symbol(SimbolosTS.NOR , yycolumn, yyline, yytext());}
    "&|"                                    {return new Symbol(SimbolosTS.XOR , yycolumn, yyline, yytext());}
    "!"                                     {return new Symbol(SimbolosTS.NOT , yycolumn, yyline, yytext());}

    //Operadores de incremento/decremento
    "+="                                    {return new Symbol(SimbolosTS.MAS_IGUAL , yycolumn, yyline, yytext());}
    "++"                                    {return new Symbol(SimbolosTS.MAS_MAS , yycolumn, yyline, yytext());}
    "--"                                    {return new Symbol(SimbolosTS.MENOS_MENOS , yycolumn, yyline, yytext());}

    //Notas musicales
    "Do"                                    {return new Symbol(SimbolosTS.DO , yycolumn, yyline, yytext());}
    "Do#"                                   {return new Symbol(SimbolosTS.DO_S , yycolumn, yyline, yytext());}
    "Re"                                    {return new Symbol(SimbolosTS.RE , yycolumn, yyline, yytext());}
    "Re#"                                   {return new Symbol(SimbolosTS.RE_S , yycolumn, yyline, yytext());}
    "Mi"                                    {return new Symbol(SimbolosTS.MI , yycolumn, yyline, yytext());}
    "Fa"                                    {return new Symbol(SimbolosTS.FA , yycolumn, yyline, yytext());}
    "Fa#"                                   {return new Symbol(SimbolosTS.FA_S , yycolumn, yyline, yytext());}
    "Sol"                                   {return new Symbol(SimbolosTS.SOL , yycolumn, yyline, yytext());}
    "Sol#"                                  {return new Symbol(SimbolosTS.SOL_S , yycolumn, yyline, yytext());}
    "La"                                    {return new Symbol(SimbolosTS.LA , yycolumn, yyline, yytext());}
    "La#"                                   {return new Symbol(SimbolosTS.LA_S , yycolumn, yyline, yytext());}
    "Si"                                    {return new Symbol(SimbolosTS.SI , yycolumn, yyline, yytext());}
    
    //Palabras Reservadas
    (("P"|"p") "ista")                      {return new Symbol(SimbolosTS.PISTA, yycolumn, yyline, yytext());}
    (("E"|"e")"xtiende")                    {return new Symbol(SimbolosTS.EXTIENDE , yycolumn, yyline, yytext());}
    (("K"|"k") "eep")                       {return new Symbol(SimbolosTS.KEEP , yycolumn, yyline, yytext());}
    (("V"|"v")"ar" )                        {return new Symbol(SimbolosTS.VAR , yycolumn, yyline, yytext());}
    (("C"|"c") "adena")                     {return new Symbol(SimbolosTS.CADENA_RSV , yycolumn, yyline, yytext());}
    (("E"|"e")"ntero" )                     {return new Symbol(SimbolosTS.ENTERO , yycolumn, yyline, yytext());}
    (("D"|"d") "oble")                      {return new Symbol(SimbolosTS.DOBLE , yycolumn, yyline, yytext());}
    (("B"|"b") "oolean")                    {return new Symbol(SimbolosTS.BOOLEAN , yycolumn, yyline, yytext());}
    (("C"|"c") "aracter")                   {return new Symbol(SimbolosTS.CARACTER_RSV , yycolumn, yyline, yytext());}
    (("A"|"a") "rreglo")                    {return new Symbol(SimbolosTS.ARREGLO , yycolumn, yyline, yytext());}
    (("S"|"s")"i")                          {return new Symbol(SimbolosTS.SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"ino")                        {return new Symbol(SimbolosTS.SINO , yycolumn, yyline, yytext());}
    (("S"|"s")"ino si")                     {return new Symbol(SimbolosTS.SINO_SI , yycolumn, yyline, yytext());}
    
    (("S"|"s")"witch")                      {return new Symbol(SimbolosTS.SWITCH , yycolumn, yyline, yytext());}
    (("C"|"c")"aso")                        {return new Symbol(SimbolosTS.CASO , yycolumn, yyline, yytext());}
    (("S"|"s")"alir")                       {return new Symbol(SimbolosTS.SALIR , yycolumn, yyline, yytext());}
    (("D"|"d")"efault")                     {return new Symbol(SimbolosTS.DEFAULT , yycolumn, yyline, yytext());}
    (("P"|"p")"ara")                        {return new Symbol(SimbolosTS.PARA , yycolumn, yyline, yytext());}
    (("M"|"m")"ientras")                    {return new Symbol(SimbolosTS.MIENTRAS , yycolumn, yyline, yytext());}
    (("H"|"h")"acer")                       {return new Symbol(SimbolosTS.HACER , yycolumn, yyline, yytext());}
    (("C"|"c")"ontinuar")                   {return new Symbol(SimbolosTS.CONTINUAR , yycolumn, yyline, yytext());}
    (("R"|"r")"etornar")                    {return new Symbol(SimbolosTS.RETORNAR , yycolumn, yyline, yytext());}
    (("V"|"v")"oid")                        {return new Symbol(SimbolosTS.VOID , yycolumn, yyline, yytext());}
    (("R"|"r")"eproducir")                  {return new Symbol(SimbolosTS.REPRODUCIR , yycolumn, yyline, yytext());}
    (("E"|"e")"sperar")                     {return new Symbol(SimbolosTS.ESPERAR , yycolumn, yyline, yytext());}
    (("O"|"o")"rdenar")                     {return new Symbol(SimbolosTS.ORDENAR , yycolumn, yyline, yytext());}
    (("A"|"a")"scendente")                  {return new Symbol(SimbolosTS.ASCENDENTE , yycolumn, yyline, yytext());}
    (("D"|"d")"escendente")                 {return new Symbol(SimbolosTS.DESCENDENTE , yycolumn, yyline, yytext());}
    (("P"|"p")"ares")                       {return new Symbol(SimbolosTS.PARES , yycolumn, yyline, yytext());}
    (("I"|"i")"mpares")                     {return new Symbol(SimbolosTS.IMPARES , yycolumn, yyline, yytext());}
    (("P"|"p")"rimos")                      {return new Symbol(SimbolosTS.PRIMOS , yycolumn, yyline, yytext());}
    (("S"|"s")"umarizar")                   {return new Symbol(SimbolosTS.SUMARIZAR , yycolumn, yyline, yytext());}
    (("L"|"l")"ongitud")                    {return new Symbol(SimbolosTS.LONGITUD , yycolumn, yyline, yytext());}
    (("M"|"m")"ensaje")                     {return new Symbol(SimbolosTS.MENSAJE , yycolumn, yyline, yytext());}
    (("P"|"p")"rincipal")                   {return new Symbol(SimbolosTS.PRINCIPAL , yycolumn, yyline, yytext());}
 
    (("F"|"f")"alse")                       {return new Symbol(SimbolosTS.FALSE , yycolumn, yyline, false);}
    (("T"|"t")"rue")                        {return new Symbol(SimbolosTS.TRUE , yycolumn, yyline, true);}
    (("F"|"f")"also")                       {return new Symbol(SimbolosTS.FALSE , yycolumn, yyline, false);}
    (("V"|"v")"erdadero")                   {return new Symbol(SimbolosTS.TRUE , yycolumn, yyline, true);}

    //REGULARES
    ({Letra}("_"|{Letra}|{Numero})*)        {return new Symbol(SimbolosTS.IDENTIFICADOR , yycolumn, yyline, yytext());}
    ({Numero})                              {return new Symbol(SimbolosTS.NUMERO , yycolumn, yyline, new Integer(yytext()));}
    ({Numero}("\."){Numero})                {return new Symbol(SimbolosTS.DECIMAL , yycolumn, yyline, new Double(yytext()));}
    ({Cadena})                              {return new Symbol(SimbolosTS.CADENA , yycolumn, yyline, yytext());}
    ("'"("#'"|"##"|"#r"|"#t"|"#n")"'")      {return new Symbol(SimbolosTS.CARACTER , yycolumn, yyline, yytext().substring(1, yytext().length()-1));}
    ("'"({Letra}|{Signo}|{Digito})"'")      {return new Symbol(SimbolosTS.CARACTER , yycolumn, yyline, yytext().substring(1, yytext().length()-1));}


    {Espacio}                               {/*Ignore*/}
    .                                       {/*Ignore*/}
    
}
