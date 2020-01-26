<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>Авторизация</title>
    </head>
    <body>
    <#include "navigation/header.ftl">
    <main>
        <h1 class="main-info">Авторизация</h1>
        <div class="container-fluid">
            <section id="login" class="login">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 offset-1">
                            <div class="login__form">
                                <form method="post">
                                    <div class="form-group">
                                        <div class="col-lg-10 offset-1">
                                            <label for="exampleInputEmail1">E-mail: </label>
                                            <input type="text" name="email" class="form-control" id="exampleInputEmail1"
                                                   aria-describedby="emailHelp" placeholder="Введите e-mail">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-10 offset-1">
                                            <label for="exampleInputPassword1">Пароль: </label>
                                            <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                                                   placeholder="Введите пароль">
                                        </div>
                                    </div>
                                    <div class="form-group form-check">
                                        Запомнить меня: <input type="checkbox" id="exampleCheck1" name="rememberMe">
                                    </div>
                                    <input type="submit" value="Вход" class="button7"><br><br>
                                </form>
                                Нет аккаунта? <a href="/register" class="button7">Зарегистрироваться</a>
                            </div>
                        </div>
                    </div>
                </div>
                <#include "configuration/show-info.ftl">
            </section>
            <img src="../../frontend/img/main-photo.jpg" alt="authorizationImg" class="img-fluid ">
        </div>
    </main>
    <#include "configuration/body-configuration.ftl">
    </body>
</html>