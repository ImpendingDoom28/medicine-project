<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "configuration/head-configuration.ftl">
        <title>Registration</title>
        <script type="application/javascript">
            <#include "../js/register.js">
        </script>
    </head>
    <body>
        <#include "navigation/header.ftl">
        <main>
            <h1 class="main-info">Регистрация</h1>
            <div class="container-fluid">
                <section id="login" class="login">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-10 offset-1">
                                <div class="login__form">
                                    <form method="post">
                                        <div class="form-group">
                                            <div class="col-lg-10 offset-1">
                                                <label for="exampleInputEmail1">E-mail<div class="required">*</div>: </label>
                                                <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                                                       aria-describedby="emailHelp" placeholder="Введите e-mail">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-10 offset-1">
                                                <label for="exampleInputPassword1">Пароль<div class="required">*</div>: </label>
                                                <input type="password" oninput="checkPassword()" name="password" class="form-control" id="password"
                                                       placeholder="Введите пароль">
                                            </div>
                                        </div>
                                        <h5 class="card-body__text-info">
                                            Ваш пароль должен соответствовать следующим требованиям:
                                            <ul>
                                                <li id="passwordLengthError">
                                                    Длина пароля должна быть минимум 6 символов
                                                </li>
                                                <li id="passwordContainUpperCaseLetterError">
                                                    Пароль должен содержать хотя бы 1 заглавную букву
                                                </li>
                                                <li id="passwordContainNumberError">
                                                    Пароль должен содержать хотя бы 1 цифру
                                                </li>
                                            </ul>
                                        </h5>
                                        <div class="form-group">
                                            <div class="col-lg-10 offset-1">
                                                <label for="confirmPassword">Подтвердите пароль<div class="required">*</div></label><br>
                                                <input type="password" id="confirmPassword" oninput="checkConfirmPassword()" name="confirmPassword" class="form-control" placeholder="Введите пароль" disabled>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-10 offset-1">
                                                <label for="exampleInputEmail1">Имя: </label>
                                                <input type="text" name="name" class="form-control" id="exampleInputName"
                                                       placeholder="Введите имя">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-10 offset-1">
                                                <label for="exampleInputEmail1">Фамилия: </label>
                                                <input type="text" name="surname" class="form-control" id="exampleInputSurname"
                                                       placeholder="Введите Фамилию">
                                            </div>
                                        </div>
                                        <input type="submit" id="registerButton" value="Зарегистрироваться" class="button7">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <#include "configuration/show-info.ftl">
            <img src="../../frontend/img/example.jpg" alt="authorizationImg" class="img-fluid ">
        </main>
        <#include "configuration/body-configuration.ftl">
    </body>
</html>