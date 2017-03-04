<html>
    <head>
        <title>Listado Estudiantes</title>
        </head>
    <body>
        <h1>Listando estudiantes</h1>
        <a href="/crear.html">Crear Estudiante</a>
        <table border=1>
            <tr><th>Matricula</th><th>Nombre</th><th>Apellido</th><th></th></tr>
        <#list estudiantes as estudiante>
            <tr>
                <td>${estudiante.matricula?string["#########"]}</td>
                <td>${estudiante.nombre}</td>
                <td>${estudiante.apellido}</td>
                <td><a href="/editar/${estudiante.matricula?string["#########"]}">editar</a> | <a href="/borrar/${estudiante.matricula?string["#########"]}">borrar</a></td>
             </tr>
        </#list>
        </table>
        </body>
    </html>
