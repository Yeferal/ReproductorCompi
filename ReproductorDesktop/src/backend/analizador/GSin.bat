SET JAVA_HOME="C:\Program Files\Java\jdk-11.0.12\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\Usuario\Documents\University_Courses\Current_Semester\Compi2\Proyectos\Proyecto1\ReproductorCompi\ReproductorDesktop\src\backend\analizador
java -jar C:\Users\Usuario\Documents\University_Courses\Current_Semester\Compi2\Proyectos\Proyecto1\ReproductorCompi\ReproductorDesktop\Librerias\java-cup-11a.jar -parser AnalizadorSintactico -symbols Simbolos Sintactico.cup
pause