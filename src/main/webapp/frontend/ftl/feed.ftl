<!DOCTYPE html>
<html lang="ru">
<head>
    <#include "configuration/head-configuration.ftl">
    <title>Новости</title>
</head>
<body>
<#include "navigation/header.ftl">
<main class="feed">
    <div class="col-md-10">
        <h1 class="main-info">Последние новости!</h1>
        <div class="jumbotron jumbotron-fluid">
            <#list news as new>
                <div class="container">
                    <h4 class="display-4">${new.title}</h4>
                    <img src="${new.imagePath}" class="img-fluid" alt="new">
                    <p class="lead">${new.previewText}...</p>
                    <div>
                        <a href="/feed/id${new.id}">Подробнее...</a>
                    </div>
                </div>
                <hr>
            </#list>
        </div>
    </div>
</main>
<#include "configuration/body-configuration.ftl">
</body>
</html>