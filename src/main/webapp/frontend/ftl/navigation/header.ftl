<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/" id="green"><p>Медицина сегодня</p></a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/feed">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/diseases">О&nbsp;болезнях</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/forum">Форум</a>
                </li>
                <#if !(bean??)>
                    <li class="auth">
                        <a class="nav-link" href="/login">Авторизация</a>
                    </li>
                <#else>
                    <li class="auth">
                        <span>${bean.userDto.surname} ${bean.userDto.name}</span>
                    </li>
                    <li style="margin-left: auto;" class="with-image">
                        <i class="fas fa-address-card"></i>
                        <a href="/profile/id${bean.userDto.id}" class="with-image-link">Профиль</a>
                    </li>
                    <li id="logout" class="with-image">
                        <i class="fas fa-sign-out-alt"></i>
                        <a class="with-image-link" href="/logout">Выйти</a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>
</header>
