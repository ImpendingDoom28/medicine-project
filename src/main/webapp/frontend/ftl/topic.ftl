<!DOCTYPE html>
<html lang="ru">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>${topic.name}</title>
        <script type="application/javascript">
            <#include "../js/topic.js">
        </script>
    </head>
    <body>
        <#include "navigation/header.ftl">
        <div class="container-fluid ">
            <a href="/forum">Назад на форум</a>
            <h1 class="text-center">Тема обсуждения: ${topic.name}</h1>
        </div>
        <div class="container-fluid text-center">
            <#include "configuration/show-info.ftl">
            <div class="row">
                <div class="col-8">
                    <#if topic.messages??>
                        <#list topic.messages as message>
                            <div style="border: 2vh solid black">
                                <h5>${message.author.name} ${message.author.surname} :</h5>
                                <div>${message.text}</div>
                                <div>${message.datetime}</div>
                            </div>
                        </#list>
                    </#if>
                </div>
                <#if bean??>
                    <div class="col">
                        <form method="post">
                            <div>
                                <h5 class="main-info">Отправить сообщение</h5>
                                <form method="post" class="profile__form">
                                    <div class="form-group">
                                        <div class="col-lg-10 offset-1">
                                            <label for="textMessage">Текст сообщения: </label>
                                            <textarea id="textMessage" name="text" class="form-control" placeholder="Введите текст сообщения" oninput="checkForLength()"></textarea>
                                        </div>
                                        <h5 id="chars">0/75</h5>
                                    </div>
                                    <input type="submit" id="submiter" value="Отправить" class="button7"><br><br>
                                </form>
                            </div>
                        </form>
                    </div>
                <#else>
                    <div class="col">
                        <h4>Авторизуйтесь, чтобы оставлять сообщения!</h4>
                    </div>
                </#if>
            </div>
        </div>
        <#include "configuration/body-configuration.ftl">
    </body>
</html>