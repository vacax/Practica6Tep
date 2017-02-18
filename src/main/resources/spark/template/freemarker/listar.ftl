<html>
    <head>
        <title>Listado Estudiantes</title>
        </head>
    <body>
        <h1>Listando estudiantes</h1>    
        <table border=1>
            <tr><th>Matricula</th><th>Nombre</th><th>Apellido</th><th></th></tr>
        <#list estudiantes as estudiante>
            <tr>
                <td>${estudiante.matricula}</td>
                <td>${estudiante.nombre}</td>
                <td>${estudiante.apellido}</td>
                <td><a href="#matricula">editar</a> | <a href="#matricula">borrar</a></td>
             </tr>
        </#list>
        </table>
        </body>
    </html>
