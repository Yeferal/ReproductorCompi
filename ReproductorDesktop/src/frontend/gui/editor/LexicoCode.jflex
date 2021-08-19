package gui.editor;
import java_cup.runtime.Symbol;

%%

%class Lexico
%public
%cupsym Simbolo
%cup
%state COMENTARIO1 COMENTARIO2 CADENA TITULO
%unicode
%line
%char
%column
%{
String cadena="";
PintarPalabras pintar = new PintarPalabras();

%}

numero=[0-9]+
decimal= {numero}+"."{numero}+
letra=[A-Za-zÑñ]
id ={letra}({letra}|"_"|[0-9])*
%%

<YYINITIAL>{
"Proyecto" { pintar.pintaRojo(yychar,yylength()); return new Symbol(Simbolo.Proyecto, yycolumn,yyline); }
"Var" { pintar.pintaMora(yychar,yylength()); return new Symbol(Simbolo.Var, yycolumn,yyline); }
"printer" { return new Symbol(Simbolo.printer, yycolumn,yyline); }
"Context" { pintar.pintaCeles(yychar,yylength());return new Symbol(Simbolo.contexto, yycolumn,yyline); }
"VarRef" { pintar.pintaVerde(yychar,yylength()); return new Symbol(Simbolo.varref, yycolumn,yyline); }
"=" { return new Symbol(Simbolo.igual, yycolumn,yyline); }
"(" {return new Symbol(Simbolo.par_abre, yycolumn,yyline); }
")" {return new Symbol(Simbolo.par_cierra, yycolumn,yyline); }
"{" {return new Symbol(Simbolo.llav_abre, yycolumn,yyline); }
"}" {return new Symbol(Simbolo.llav_cierra, yycolumn,yyline); }
"+" {return new Symbol(Simbolo.mas, yycolumn,yyline); }
"-" {return new Symbol(Simbolo.menos, yycolumn,yyline); }
"/" {return new Symbol(Simbolo.divide, yycolumn,yyline); }
"*" {return new Symbol(Simbolo.multi, yycolumn,yyline); }
"Rq" { return new Symbol(Simbolo.raiz, yycolumn,yyline); }
"Ptn" { return new Symbol(Simbolo.potencia, yycolumn,yyline); }
"," {return new Symbol(Simbolo.coma, yycolumn,yyline); }
";" {return new Symbol(Simbolo.pycoma, yycolumn,yyline); }
"//" { pintar.pintaAzul(yychar,yylength()); yybegin(COMENTARIO1); }
"¿¡" { pintar.pintaAzul(yychar,yylength()); yybegin(COMENTARIO2); }
"[" { pintar.pintaVerCla(yychar,yylength()); yybegin(TITULO);}
[\"] { pintar.pintaNara(yychar,yylength()); yybegin(CADENA); cadena+= yytext();}
[ \t\r\f\n]+ { }
{id} {return new Symbol(Simbolo.id, yycolumn,yyline,new String(yytext()));}
{numero} { return new Symbol(Simbolo.numero, yycolumn,yyline,new String(yytext())); }
{decimal} { return new Symbol(Simbolo.decimal, yycolumn,yyline,new String(yytext())); }

. { System.out.println("ERROR LEXICO:"+yytext()+ " Linea: "+(yyline+1)+" Columna: "+ (yycolumn+1)); 
 erroresLexicos.insertarError(yytext(),Integer.toString(yycolumn+1), Integer.toString(yyline+1), "El caracter no pertenece al lenguaje");
 }
}

<CADENA>{
[\"] { cadena+= yytext(); String tmp=cadena; cadena=""; yybegin(YYINITIAL);  return new Symbol(Simbolo.cadena, yychar,yyline,new String(tmp)); }
[\n] {String tmp=cadena; cadena="";  erroresLexicos.insertarError(tmp,Integer.toString(yycolumn+1), Integer.toString(yyline+1), "Se esperaba cierre de cadena (\")."); 
      yybegin(YYINITIAL);
     }
[^\"] { pintar.pintaNara(yychar,yylength()); cadena+=yytext();}
}

<COMENTARIO1>{
[\n] { yybegin(YYINITIAL); }
[^\n] { pintar.pintaAzul(yychar,yylength());}
}

<COMENTARIO2>{
"¡?" { pintar.pintaAzul(yychar,yylength()); yybegin(YYINITIAL); }
[\n] { }
. { pintar.pintaAzul(yychar,yylength()); }
}

<TITULO>{
{id} { pintar.pintaVerCla(yychar,yylength()); cadena+=yytext();}
{numero} { pintar.pintaVerCla(yychar,yylength()); cadena+=yytext();}
[\t\r\f\n]+ {cadena+=" ";}
" " {cadena+=" ";}
"]" { pintar.pintaVerCla(yychar,yylength()); String tmp=cadena; cadena=""; yybegin(YYINITIAL); System.out.println("TITULO O CONTEXTO "+ tmp ); return new Symbol(Simbolo.titulo, yycolumn,yyline,new String(tmp));}
. {String tmp=yytext(); cadena =""; erroresLexicos.insertarError(tmp,Integer.toString(yycolumn+1), Integer.toString(yyline+1), "Titulo o contexto invalido"); yybegin(YYINITIAL); }
}