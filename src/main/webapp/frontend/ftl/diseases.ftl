<!DOCTYPE html>
<html lang="ru">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>О болезнях</title>
        <script type="text/javascript">
            <#include "../js/diseases.js">
        </script>
    </head>
    <body>
        <#include "navigation/header.ftl">
        <main class="feed">
            <div class="col-md-10">
                <h1 class="main-info">Популярные болезни:</h1>
                <h5 class="card-header text-center">Поиск болезней</h5>
                <div class="card-body search-card-body">
                    <p class="card-title text-center">Используйте форму для поиска болезней!</p>
                    <div class="row search-row">
                        <div class="col">
                            <label for="disease-name">По имени:</label><br>
                            <input id="disease-name" type="text" title="..." oninput="searchForDiseases()">
                        </div>
                    </div>
                <div class="jumbotron jumbotron-fluid">
                    <div id="res">
                        <#if diseases?size != 0>
                            <#list diseases as disease>
                                <div class="container" style="border: #28a745 1.5vh solid;">
                                    <div class="row">
                                        <div class="col-4">
                                            <img src="${disease.imagePath}" height="256" width="256" class="img-fluid" alt="new">
                                        </div>
                                        <div class="col-6">
                                            <h1 class="display-4">${disease.name}</h1>
                                            <span class="join"><a href="/diseases/id${disease.id}">Подробнее...</a></span>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        <#else>
                            <h1>Пока что болезней нет, но скоро мы всё исправим!!! :)</h1>
                        </#if>
                    </div>
                </div>
            </div>
        </main>
        <#include "configuration/body-configuration.ftl">
    </body>
</html>