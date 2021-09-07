#!/bin/bash

nameFileCup='SintacticoAST.cup';
nameFileSimbols='SimbolosAST';
nameFileBack='AnalizadorSintacticoAST';

dirCup='/home/yefer/Documentos/Compi/java-cup-11a.jar'
comandJava='java -jar'
PARSER='-parser'
SYMBOLS='-symbols'
echo "$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup"
$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup