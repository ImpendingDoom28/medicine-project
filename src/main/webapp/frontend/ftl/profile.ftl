<!DOCTYPE html>
<html lang="en">
<head>
	<#include "configuration/head-configuration.ftl">
    <title>Профиль:</title>
</head>
<body>
<#include "navigation/header.ftl">
<main>
    <div class="container-fluid align-items-center">
        <div class="row">
            <div class="col-5">
                <div class="card profile"  id="profile" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Профиль: </h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">${bean.userDto.email} </li>
                        <li class="list-group-item">${bean.userDto.name}</li>
                        <li class="list-group-item">${bean.userDto.surname}</li>
                        <li class="list-group-item">Ваши роли:
                            <#list bean.userDto.roles as role>
                                <p>${role.name}</p>
                            </#list>
                        </li>
                        <#list bean.userDto.roles as role>
                            <#if role.name == "admin">
                                <form>
                                    <input class="btn-block" type="button" value="Админ панель" onClick='location.href="/admin"'>
                                </form>
                            </#if>
                        </#list>
                    </ul>
                </div>
            </div>
            <div class="col profile__col">
                <div class="login__form">
                    <p class="main-info">Изменить данные:</p>
                    <form method="post" class="profile__form">
                        <div class="form-group">
                            <div class="col-lg-10 offset-1">
                                <label for="exampleInputEmail1">Имя: </label>
                                <input type="text" name="name" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp" placeholder="Введите имя">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-10 offset-1">
                                <label for="exampleInputPassword1">Фамилия: </label>
                                <input type="text" name="surname" class="form-control" id="exampleInputPassword1"
                                       placeholder="Введите фамилию">
                            </div>
                        </div>
                        <input type="submit" value="Изменить" class="button7"><br><br>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <#include "configuration/show-info.ftl">
</main>
<#include "configuration/body-configuration.ftl">
</body>
</html>