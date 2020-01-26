<!DOCTYPE html>
<html lang="en">
<head>
    <#include "configuration/head-configuration.ftl">
    <title>Админ панель</title>
</head>
<body>
<#include "navigation/header.ftl">
<main>
    <h1 class="main-info">Добавить новость:</h1>
    <div class="container-fluid">
        <section id="login" class="login">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-1">
                        <div class="login__form">
                            <form method="post" action="/admin/news" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputEmail1">Название новости: </label>
                                        <input type="text" name="news_name" class="form-control" id="exampleInputEmail1"
                                               aria-describedby="emailHelp" placeholder="Введите название" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputPassword1">Текст статьи: </label>
                                        <textarea id="exampleInputPassword1" name="news_text" class="form-control" placeholder="Введите текст статьи"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputEmail1">Картинка новости: </label>
                                        <input type="file" name="news_image" class="form-control" id="exampleInputEmail1"
                                               aria-describedby="emailHelp" placeholder="Картинка новости" required alt="empty shell">
                                    </div>
                                </div>
                                <input type="submit" value="Создать новость" class="button7"><br><br>
                            </form >
                            <#include "configuration/show-info.ftl">
                            <h1 class="main-info">Добавить болезнь:</h1>
                            <form method="post" action="/admin/disease" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputEmail1">Название болезни: </label>
                                        <input type="text" name="disease_name" class="form-control" id="exampleInputEmail1"
                                               aria-describedby="emailHelp" placeholder="Введите название" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputPassword1">Описание болезни: </label>
                                        <textarea id="exampleInputPassword1" name="disease_text" class="form-control" placeholder="Введите описание болезни"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-10 offset-1">
                                        <label for="exampleInputEmail1">Картинка болезни: </label>
                                        <input type="file" name="disease_image" class="form-control" id="exampleInputEmail1"
                                               aria-describedby="emailHelp" placeholder="Картинка новости" required alt="empty shell">
                                    </div>
                                </div>
                                <input type="submit" value="Добавить болезнь" class="button7"><br><br>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<#include "configuration/body-configuration.ftl">
</body>
</html>