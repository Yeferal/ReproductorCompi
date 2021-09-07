#!/bin/bash

nameFileCup='Sintactico.cup';
nameFileSimbols='Simbolos';
nameFileBack='AnalizadorSintactico';

dirCup='/home/yefer/Documentos/Compi/java-cup-11a.jar'
comandJava='java -jar'
PARSER='-parser'
SYMBOLS='-symbols'
echo "$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup"
$comandJava $dirCup $PARSER $nameFileBack $SYMBOLS $nameFileSimbols $nameFileCup