<%-- 
    Document   : index
    Created on : 18/05/2014, 18:58:51
    Author     : munifgebarajunior
--%>

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
        <title>munif.com.br</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="munif gebara junior" />
        <meta name="keywords" content="munif gebara junior" />
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
    <body>

        <!-- Header -->

        <jsp:include page="header.jsp"/>

        <div id="banner">
            <div class="container">
                <div class="row">
                    <div class="6u">

                        <!-- Banner Copy -->
                        <p>Desenvolvedor, professor e pesquisador</p>
                        <a href="http://github.com/munifgebara" class="button-big">GitHub do Munif</a>
                        <a href="http://munif.com.br/munifold" class="button-big">Versão Anterior</a>

                    </div>
                    <div class="6u">

                        <!-- Banner Image -->
                        <a href="#" class="bordered-feature-image"><img src="images/banner.jpg" alt="" /></a>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Features -->
    <div id="features-wrapper">
        <div id="features">
            <div class="container">
                Últimos Posts
                <div class="row">
                    <div class="3u">





                        <!-- Feature #1 -->
                        <section>
                            <a href="#" class="bordered-feature-image"><img src="images/pic01.jpg" alt="" /></a>
                            <h2>${artigos[0].titulo}(${artigos[0].assunto.nome})</h2>
                            <p>
                                ${artigos[0].texto}                                
                            </p>
                        </section>


                    </div>
                    <div class="3u">

                        <!-- Feature #2 -->
                        <section>
                            <a href="#" class="bordered-feature-image"><img src="images/pic02.jpg" alt="" /></a>
                            <h2>${artigos[1].titulo}(${artigos[1].assunto.nome})</h2>
                            <p>
                                ${artigos[1].texto}
                            </p>
                        </section>

                    </div>
                    <div class="3u">

                        <!-- Feature #3 -->
                        <section>
                            <a href="#" class="bordered-feature-image"><img src="images/pic03.jpg" alt="" /></a>
                            <h2>${artigos[2].titulo}(${artigos[2].assunto.nome})</h2>
                            <p>
                                ${artigos[2].texto}
                            </p>
                        </section>

                    </div>
                    <div class="3u">

                        <!-- Feature #4 -->
                        <section>
                            <a href="#" class="bordered-feature-image"><img src="images/pic04.jpg" alt="" /></a>
                            <h2>${artigos[3].titulo}(${artigos[3].assunto.nome})</h2>
                            <p>
                                ${artigos[3].texto}
                            </p>
                        </section>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Content -->
    <div id="content-wrapper">
        <div id="content">
            <div class="container">
                <div class="row">
                    <div class="4u">

                        <!-- Box #1 -->
                        <section>
                            <header>
                                <h2>Sobre o Munif</h2>
                                <h3>Munif Gebara Junior</h3>
                            </header>
                            <a href="#" class="feature-image"><img src="images/pic05.jpg" alt="" /></a>
                            <p>
                                Começou a programar em 1983, se formou em Ciência da Computação na UEM em 1997 e concluiu o mestrado em 2001 na UTFPR em Robótica, Informática Industrial. 
                                Desenvolve software comercialmente desde 1997 e leciona no ensino superior desde 2001. 
                            </p>
                        </section>

                    </div>
                    <div class="4u">

                        <!-- Box #2 -->
                        <section>
                            <header>
                                <h2>O que eu faço?</h2>
                                <h3>Principais atividades</h3>
                            </header>
                            <ul class="check-list">
                                <li>Aulas na graução e pós-graduação</li>
                                <li>Desenvolvimento de software web, mobile e embarcado</li>
                                <li>Pesquisas</li>
                                <li>Consultorias</li>
                            </ul>
                        </section>

                    </div>
                    <div class="4u">

                        <!-- Box #3 -->
                        <section>
                            <header>
                                <h2>Tecnologias</h2>
                                <h3>Algumas que gosto e recomendo</h3>
                            </header>
                            <ul class="quote-list">
                                <li>
                                    <img src="images/pic06.jpg" alt="" />
                                    <p>"Write once, run anywhere."</p>
                                    <span>Linguagem Java</span>
                                </li>
                                <li>
                                    <img src="images/pic07.jpg" alt="" />
                                    <p>"The world's most popular mobile OS."</p>
                                    <span>Android</span>
                                </li>
                                <li>
                                    <img src="images/pic08.jpg" alt="" />
                                    <p>"Arduino is an open-source electronics prototyping platform based on flexible, easy-to-use Hardware and Software."</p>
                                    <span>Arduino</span>
                                </li>
                            </ul>
                        </section>

                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp"/>


</body>
</html>