#!/bin/bash

nameFileCup='SintacticoCode.cup';
nameFileSimbols='SimbolosCode';
nameFileBack='AnalizadorSintacticoCode';

dirCup='/home/yeferal/Documentos/Compi/java-cup-11a.jar'
comandJava='java -jar'
PARSER='-parser'
SYMBOLS='-symbols'
echo "$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup"
$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup

#echo 'cup -parser '$nameFileBack' -symbols '$nameFileSimbols $nameFile;
#cup -parser $nameFileBack -symbols $nameFileSimbols $nameFileCup

