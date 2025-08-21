<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<HTML>
    <head>
        <link rel="stylesheet" href="styles.css">
    </head>
    <BODY>

    <div class="row">
        <div class="column">
        Liste femmes
        <table>
            <c:forEach var = "joueur" items="${listeFemmes}">
                <TR>
                    <TD>${joueur.prenom}</TD><TD>${joueur.nom}</TD>
                </TR>
            </c:forEach>
        </table>
        </div>
        <div class="column">
        Liste hommes
        <table>
            <c:forEach var = "joueur" items="${listeHommes}">
                <TR>
                    <TD>${joueur.prenom}</TD><TD>${joueur.nom}</TD>
                </TR>
            </c:forEach>
        </table>
        </div>
    </div>


    </BODY>
</HTML>
