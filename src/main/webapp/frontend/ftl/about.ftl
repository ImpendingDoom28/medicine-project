<!DOCTYPE html>
<html lang="en">
<head>
    <#include "configuration/head-configuration.ftl">
    <title>About</title>
</head>
<body>
<#include "navigation/header.ftl">
<div class="container-fluid about">
    <h1 class="text-center">Самые распространенные заболевания</h1>
    <div class="container search">
        <div class="row justify-content-center">
            <form role="search" id="search" class="col-md-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="">
                    <span class="input-group-btn">
        <button class="btn btn-success">
            Поиск
        </button>
        </span>
                </div>
            </form>
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item">Первая новость</li>
        <li class="list-group-item">Вторая новость</li>
    </ul>
</div>
<#include "configuration/body-configuration.ftl">
</body>
</html>