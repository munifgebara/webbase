<%-- 
    Document   : artigos
    Created on : 22/05/2014, 21:17:50
    Author     : munifgebarajunior
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
        Halcyonic 3.1 by HTML5 UP
        html5up.net | @n33co
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
    <head>
        <title>munif.com.br - artigos</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="js/jquery.min.js"></script>
        <script src="js/config.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-panels.min.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    </head>
    <body class="subpage">
        <jsp:include page="header.jsp"/>
    </div>

    <!-- Content -->
    <div id="content-wrapper">
        <div id="content">
            <div class="container">
                <div class="row">
                    <div class="9u">

                        <!-- Main Content -->
                        <c:forEach  items="${artigos}" var="artigo">
                            <section>
                                <header>
                                    <h2>${artigo.titulo}</h2>
                                    <h3>
                                        ${artigo.assunto.nome}(por ${artigo.usuario.login} em <fmt:formatDate value="${artigo.quando}" pattern="HH:mm dd/MM/yyyy"/>  )
                                    </h3>
                                </header>
                                <p>
                                    ${artigo.texto}

                                </p>
                            </section>
                        </c:forEach>

                    </div>
                    <div class="3u">

                        <!-- Sidebar -->
                        <section>
                            <header>
                                <h2>Assuntos</h2>
                            </header>
                            <ul class="link-list">
                                <c:forEach  items="${assuntos}" var="assunto">
                                    <li>
                                        <a href="controlador?acao=assunto&id=${assunto.id}">${assunto.nome}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->

    <jsp:include page="footer.jsp"/>


</body>
</html>