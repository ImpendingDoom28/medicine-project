<!DOCTYPE html>
<html lang="ru">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>${new.title}</title>
    </head>
    <body>
    <#include "navigation/header.ftl">
    <main class="feed">
        <div class="col-md-10">
            <p style="display: inline-block">
                <img src="${new.imagePath}" height="256" width="256" alt="">
            </p>
            <h1 style="display: inline-block">${new.title}</h1>
            <h3>Описание:</h3>
            <div class="jumbotron jumbotron-fluid">
                ${new.text}
            </div>
        </div>
    </main>
    <#include "configuration/body-configuration.ftl">
    </body>
</html>