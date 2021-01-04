<#macro navbar selected isUser isAdmin>
    <nav class="navbar navbar-expand-xl navbar-dark bg-dark">
        <a class="navbar-brand" href="/">AviaSearch</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item <#if selected == 1>selected</#if>">
                    <a class="nav-link" href="/" <#if selected == 1>style="font-weight: bold"</#if>>Главная</a>
                </li>
            </ul>
            <#if isUser>
                <ul class="navbar-nav">
                    <li class="nav-item <#if selected == 2>selected</#if>">
                        <a class="nav-link" href="/user" <#if selected == 2>style="font-weight: bold"</#if>>Личный кабинет</a>
                    </li>
                </ul>
                <#if isAdmin>
                    <ul class="navbar-nav">
                        <li class="nav-item <#if selected == 3>selected</#if>">
                            <a class="nav-link" href="/admin" <#if selected == 3>style="font-weight: bold"</#if>>Админ-панель</a>
                        </li>
                    </ul>
                </#if>
                <ul class="navbar-nav">
                    <li class="nav-item <#if selected == 4>selected</#if>">
                        <a class="nav-link" href="/logout" <#if selected == 4>style="font-weight: bold"</#if>>Выйти</a>
                    </li>
                </ul>
            <#else>
                <ul class="navbar-nav">
                    <li class="nav-item <#if selected == 5>selected</#if>">
                        <a class="nav-link" href="/login" <#if selected == 5>style="font-weight: bold"</#if>>Авторизация</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item <#if selected == 6>selected</#if>">
                        <a class="nav-link" href="/register" <#if selected == 6>style="font-weight: bold"</#if>>Регистрация</a>
                    </li>
                </ul>
            </#if>
        </div>
    </nav>
</#macro>