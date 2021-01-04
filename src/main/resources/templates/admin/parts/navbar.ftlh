<#macro navbar selected>
    <nav class="navbar navbar-expand-xl navbar-dark bg-dark">
        <a class="navbar-brand" href="/admin">Админ-Панель</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item <#if selected == 1>selected</#if>">
                    <a class="nav-link" href="/admin" <#if selected == 1>style="font-weight: bold"</#if>>Главная </a>
                </li>
                <li class="nav-item <#if selected == 2>selected</#if>">
                    <a class="nav-link" href="/admin/users" <#if selected == 2>style="font-weight: bold"</#if>>Пользователи</a>
                </li>
                <li class="nav-item <#if selected == 3>selected</#if>">
                    <a class="nav-link" href="/admin/airlines" <#if selected == 3>style="font-weight: bold"</#if>>Авиакомпании</a>
                </li>
                <li class="nav-item <#if selected == 4>selected</#if>">
                    <a class="nav-link" href="/admin/airports" <#if selected == 4>style="font-weight: bold"</#if>>Аэропорты</a>
                </li>
                <li class="nav-item <#if selected == 5>selected</#if>">
                    <a class="nav-link" href="/admin/bookedtickets" <#if selected == 5>style="font-weight: bold"</#if>>Купленные билеты</a>
                </li>
                <li class="nav-item <#if selected == 6>selected</#if>">
                    <a class="nav-link" href="/admin/countries" <#if selected == 6>style="font-weight: bold"</#if>>Страны</a>
                </li>
                <li class="nav-item <#if selected == 7>selected</#if>">
                    <a class="nav-link" href="/admin/luggage" <#if selected == 7>style="font-weight: bold"</#if>>Багаж</a>
                </li><li class="nav-item" <#if selected == 8>selected</#if>>
                    <a class="nav-link" href="/admin/planes" <#if selected == 8>style="font-weight: bold"</#if>>Самолеты</a>
                </li><li class="nav-item" <#if selected == 9>selected</#if>>
                    <a class="nav-link" href="/admin/roles" <#if selected == 9>style="font-weight: bold"</#if>>Роли</a>
                </li><li class="nav-item" <#if selected == 10>selected</#if>>
                    <a class="nav-link" href="/admin/tickets" <#if selected == 10>style="font-weight: bold"</#if>>Билеты</a>
                </li>
            </ul>
        </div>
    </nav>
</#macro>