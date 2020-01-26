<!DOCTYPE html>
<html lang="ru">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>MainPage</title>
    </head>
    <body>
        <#include "navigation/header.ftl">
        <main>
            <h1 class="main-info">Главная</h1>
            <div class="first-block container-fluid">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <#list news as new>
                            <#if new?is_first>
                                <div class="carousel-item active">
                            <#else>
                                <div class="carousel-item">
                            </#if>
                                <img class="d-block w-100" src="${new.imagePath}" alt="Первый слайд">
                                <div class="carousel-caption d-none d-md-block">
                                    <h3>${new.title}</h3>
                                    <p>${new.previewText}...</p>
                                    <a href="/feed/id${new.id}">Читать далее...</a>
                                </div>
                            </div>
                        </#list>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
            <div class="second-block container-fluid ">
                <#assign count = 0>
                <#if count%3 = 0>
                    <div class="row justify-content-center">
                </#if>
                    <#list otherNews as new>
                        <div class="col-md-2">
                            <div class="card">
                                <img class="card-img" src="${new.imagePath}" alt="..." width="256" height="256">
                                <h5 class="card-title"> ${new.title}</h5>
                                <p class="card-text">${new.previewText}...</p>
                                <form>
                                    <input class="btn btn-primary" type="button" value="Подробнее..." onClick='location.href="/feed/id${new.id}"'>
                                </form>
                            </div>
                        </div>
                        <#assign count=count+1>
                    </#list>
                <#if count%3 = 1>
                    </div>
                </#if>
            </div>
        </main>
        <#include "configuration/body-configuration.ftl">
    </body>
</html>