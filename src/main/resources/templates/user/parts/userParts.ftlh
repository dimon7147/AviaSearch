<#macro editUserPart>
    <div class="modal-body" id="readyToEdit">
        <div class="form-group" style="text-align: center">
            <h1>Изменение данных:</h1>
            <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
            <div class="alert alert-danger" role="alert" id="ErrorInfo" style="display: none"></div>
        </div>
        <div class="form-group">
            <label for="loginEdit">Логин</label>
            <input name="login" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Логин" id="loginEdit" value="${user.login}">
            <small id="loginHelp" class="form-text text-muted">Логин – персональное имя пользователя для входа на сайт.</small>
        </div>
        <div class="form-group">
            <label for="emailEdit">Почта</label>
            <input name="email" type="email" class="form-control" id="emailEdit" aria-describedby="emailHelp" placeholder="Почта" value="${user.email}">
            <small id="emailHelp" class="form-text text-muted">Почта служит для связи с пользователем.</small>
        </div>
        <div class="form-group">
            <label for="fullNameEdit">ФИО</label>
            <input name="fullname" type="text" class="form-control" id="fullNameEdit" aria-describedby="fullNameHelp" placeholder="ФИО" value="${user.fullName}">
            <small id="fullNameHelp" class="form-text text-muted">Фамилия, Имя, Отчество пользователя.</small>
        </div>
        <div class="form-group">
            <label for="passportEdit">Паспортные данные</label>
            <input name="passport" type="number" class="form-control" id="passportEdit" aria-describedby="passportHelp" placeholder="Паспортные данные" value="${user.passport}">
            <small id="passportHelp" class="form-text text-muted">10 цифр паспорта: серия + номер без пробелов.</small>
        </div>
        <div class="form-group">
            <label for="bDateEdit">Дата рождения</label>
            <input name="dateOfBirth" type="date" class="form-control" id="bDateEdit" aria-describedby="bDateHelp" placeholder="Дата рождения" value="${user.dateOfBirth}">
            <small id="bDateHelp" class="form-text text-muted">Дата рождения пользователя.</small>
        </div>
        <div class="form-group" style="display: none;" id="passwChange">
            <label for="passwordEdit">Новый пароль</label>
            <input name="passport" type="password" class="form-control" id="passwordEdit" aria-describedby="passwordHelp" placeholder="Новый пароль" value="Пароль">
            <small id="passwordHelp" class="form-text text-muted">Не используйте легкие пароли.</small>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-success" onclick="saveUser();"><i class="fas fa-save"> Сохранить</i></button>
            <button type="button" class="btn btn-warning" onclick="changePass();"><i class="fas fa-key"> Сменить пароль</i></button>
        </div>
    </div>
</#macro>