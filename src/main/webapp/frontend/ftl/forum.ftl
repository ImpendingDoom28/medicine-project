<!DOCTYPE html>
<html lang="ru">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>Форум о медицине</title>
    </head>
    <body>
        <#include "navigation/header.ftl">
        <div class="container-fluid ">
            <h1 class="text-center">Форум о медицине</h1>
            <p class="col-md-6 offset-md-3 text-center">Этот форум был создан для того, чтобы вы могли обсудить актуальные на данный момент проблемы медицины, попросить помощи, а также спросить советов по лечению у других людей.</p>
        </div>
        <div class="container-fluid text-center">
            <#include "configuration/show-info.ftl">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Тема</th>
                        <th scope="col">Последнее сообщение</th>
                        <th scope="col">Количество сообщений</th>
                    </tr>
                </thead>
                <tbody>
                    <#if topics??>
                        <#list topics as topic>
                            <tr>
                                <td>${topic.name}
                                    <a href="/forum/id${topic.id}">Присоединиться</a></td>
                                <td>
                                    <#if !(topic.lastMessage??)>
                                        Пока что нет сообщений - будьте первым, кто оставит!
                                    <#else>
                                        ${topic.lastMessage.author.name}: ${topic.lastMessage.text}
                                    </#if>
                                </td>
                                <td>${topic.messagesCount}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td>Тем для обсуждений нет :(</td>
                            <td>Создать новую тему для обсуждения:</td>
                            <td>1</td>
                        </tr>
                    </#if>
                    <#if bean??>
                        <tr>
                            <td>Создать новую тему для обсуждения</td>
                            <td>
                                <form method="post" action="/forum/create">
                                    <div class="form-group">
                                        <div class="col-lg-10 offset-1">
                                            <input type="text" name="name" class="form-control" id="exampleInputPassword1"
                                                   placeholder="Введите название темы">
                                        </div>
                                    </div>
                                    <input type="submit" value="Создать тему" class="button7">
                                </form>
                            </td>
                            <td>Создать новую тему для обсуждения</td>
                        </tr>
                    </#if>
                </tbody>
            </table>
        </div>
    <#include "configuration/body-configuration.ftl">
    </body>
</html>