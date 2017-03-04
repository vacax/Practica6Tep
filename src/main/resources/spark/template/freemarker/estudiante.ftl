<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>${accionFormulario} Estudiante</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>${accionFormulario} Estudiante</h1>
        <!-- /procesarNuevoEstudiante, representa el controlador que va a guardar el estudiante.... -->
        <form action="${urlAccion}" method="POST">
            <!-- El objeto enviado tiene que estar en formato POJO -->
            Matricula: <input type="number" name="matricula" value="${estudiante.matricula?string["#########"]}" readonly ="readonly" /><br/>
            Nombre <input type="text" name="nombre" value="${estudiante.nombre}" <#if accion == 2 > readonly ="readonly" </#if> /><br/>
            Apellido <input type="text" name="apellido" value="${estudiante.apellido}" <#if accion == 2 > readonly ="readonly" </#if> /><br/>
            <input type="submit" value="Enviar...."/>
        </form>
    </body>
</html>

