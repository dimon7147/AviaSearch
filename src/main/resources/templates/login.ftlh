<#import "parts/page.ftlh" as page>
<#import "parts/auth.ftlh" as auth>
<#import 'parts/navbar.ftlh' as navbar>
<@page.page "Авторизация">
    <@navbar.navbar 5,false,false></@navbar.navbar>
<#--    <@auth.login></@auth.login>-->
        <form action="/login" method="post">
        <div class="modal-body" id="readyToEdit" style="padding-right: 30%; padding-left: 30%;">
            <div class="form-group">
                <label for="idEdit">Логин</label>
                <input name="username" type="text" class="form-control" id="login" aria-describedby="loginHelp" placeholder="Логин">
                <small id="loginHelp" class="form-text text-muted">Логин, указанный при регистрации.</small>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input name="password" type="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="Пароль">
                <small id="passwordHelp" class="form-text text-muted">Пароль.</small>
            </div>
        </div>
        <div class="modal-footer" style="padding-right: 30%; padding-left: 30%;">
            <button type="submit" class="btn btn-primary" data-dismiss="modal" id="auth">Авторизация</button>
        </div>
        </form>
<#--<a href="register">Register</a>-->
</@page.page>