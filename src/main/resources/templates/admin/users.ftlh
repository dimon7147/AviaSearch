<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 2></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "пользователя">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="loginAdd">Логин</label>
                <input name="login" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Логин" id="loginAdd">
                <small id="loginHelp" class="form-text text-muted">Логин – персональное имя пользователя для входа на сайт.</small>
            </div>
            <div class="form-group">
                <label for="emailAdd">Почта</label>
                <input name="email" type="email" class="form-control" id="emailAdd" aria-describedby="emailHelp" placeholder="Почта">
                <small id="emailHelp" class="form-text text-muted">Почта служит для связи с пользователем.</small>
            </div>
            <div class="form-group">
                <label for="fullNameAdd">ФИО</label>
                <input name="fullname" type="text" class="form-control" id="fullNameAdd" aria-describedby="fullNameHelp" placeholder="ФИО">
                <small id="fullNameHelp" class="form-text text-muted">Фамилия, Имя, Отчество пользователя.</small>
            </div>
            <div class="form-group">
                <label for="passportAdd">Паспортные данные</label>
                <input name="passport" type="number" class="form-control" id="passportAdd" aria-describedby="passportHelp" placeholder="Паспортные данные">
                <small id="passportHelp" class="form-text text-muted">10 цифр паспорта: серия + номер без пробелов.</small>
            </div>
            <div class="form-group">
                <label for="bDateAdd">Дата рождения</label>
                <input name="dateOfBirth" type="date" class="form-control" id="bDateAdd" aria-describedby="bDateHelp" placeholder="Дата рождения">
                <small id="bDateHelp" class="form-text text-muted">Дата рождения пользователя.</small>
            </div>
            <div class="form-group">
                <label for="passwordUAdd">Пароль</label>
                <input name="registerTime" type="password" class="form-control" id="passwordUAdd" aria-describedby="rDateHelp" placeholder="Пароль">
                <small id="rDateHelp" class="form-text text-muted">Пароль пользователя. Будет зашифрован.</small>
            </div>
            <div class="form-group">
                <label for="activeAdd">Активирован?</label>
                <select class="form-control" id="activeAdd" aria-describedby="activeHelp" name="active">
                    <option value="1">Да</option>
                    <option value="0">Нет</option>
                </select>
                <small id="activeHelp" class="form-text text-muted">Определяет возможность пользователя заходить на сайт.</small>
            </div>
            <div class="form-group">
                <label for="rolesAddU">Список ролей</label>
                <select multiple class="form-control" id="rolesAddU">
                    <#list roles as role>
                        <option value="${role.id}">${role.role}</option>
                    </#list>
                </select>
            </div>
        </div>
        <@modal.addmodalFooter "addUser()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <div class="modal" tabindex="-1" role="dialog" id="userEdit">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Редактирование пользователя</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                    <div class="modal-body" id="waitingToEdit">
                        <div class="d-flex justify-content-center">
                            Идет загрузка данных пользователя, пожалуйста, ожидайте...
                            <div class="spinner-border" role="status">
                                <span class="sr-only">Загрузка...</span>
                            </div>
                        </div>
                    </div>
                <div class="modal-body" id="readyToEdit">
                    <div class="form-group">
                        <label for="idEdit">ID</label>
                        <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                        <small id="idHelp" class="form-text text-muted">ID пользователя позволяет его идентифицировать в БД, его смена невозможна.</small>
                    </div>
                    <div class="form-group">
                        <label for="loginEdit">Логин</label>
                        <input name="login" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Логин" id="loginEdit">
                        <small id="loginHelp" class="form-text text-muted">Логин – персональное имя пользователя для входа на сайт.</small>
                    </div>
                    <div class="form-group">
                        <label for="emailEdit">Почта</label>
                        <input name="email" type="email" class="form-control" id="emailEdit" aria-describedby="emailHelp" placeholder="Почта">
                        <small id="emailHelp" class="form-text text-muted">Почта служит для связи с пользователем.</small>
                    </div>
                    <div class="form-group">
                        <label for="fullNameEdit">ФИО</label>
                        <input name="fullname" type="text" class="form-control" id="fullNameEdit" aria-describedby="fullNameHelp" placeholder="ФИО">
                        <small id="fullNameHelp" class="form-text text-muted">Фамилия, Имя, Отчество пользователя.</small>
                    </div>
                    <div class="form-group">
                        <label for="passportEdit">Паспортные данные</label>
                        <input name="passport" type="number" class="form-control" id="passportEdit" aria-describedby="passportHelp" placeholder="Паспортные данные">
                        <small id="passportHelp" class="form-text text-muted">10 цифр паспорта: серия + номер без пробелов.</small>
                    </div>
                    <div class="form-group">
                        <label for="bDateEdit">Дата рождения</label>
                        <input name="dateOfBirth" type="date" class="form-control" id="bDateEdit" aria-describedby="bDateHelp" placeholder="Дата рождения">
                        <small id="bDateHelp" class="form-text text-muted">Дата рождения пользователя.</small>
                    </div>
                    <div class="form-group">
                        <label for="rDateEdit">Дата регистрации</label>
                        <input name="registerTime" type="datetime-local" class="form-control" id="rDateEdit" aria-describedby="rDateHelp" placeholder="Дата регистрации" disabled="disabled">
                        <small id="rDateHelp" class="form-text text-muted">Дата регистрации пользователя. Смена даты невозможна.</small>
                    </div>
                    <div class="form-group">
                        <label for="activeEdit">Активирован?</label>
                        <select class="form-control" id="activeEdit" aria-describedby="activeHelp" name="active">
                            <option value="1">Да</option>
                            <option value="0">Нет</option>
                        </select>
                        <small id="activeHelp" class="form-text text-muted">Определяет возможность пользователя заходить на сайт.</small>
                    </div>
                    <div class="form-group">
                        <label for="rolesEditU">Список ролей</label>
                        <select multiple class="form-control" id="rolesEditU">
                            <#list roles as role>
                            <option value="${role.id}">${role.role}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="saveUser();" class="btn btn-primary" data-dismiss="modal">Сохранить изменения</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть без сохранения</button>
                </div>
            </div>
        </div>
    </div>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Пользователи сайта.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getUsers();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "пользователей"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Логин</th>
                    <th scope="col">ФИО</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Паспортные данные</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">Дата регистрации</th>
                    <th scope="col">Активирован</th>
                    <th scope="col">Список ролей</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="UsersList">
                <#list users as user>
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.username}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.passport}</td>
                    <td>${user.dateOfBirth}</td>
                    <td>${user.registerTime}</td>
                    <td>${user.active?string('Да', 'Нет')}</td>
                    <td><#list user.roles as role>
                       ${role.role}
                    </#list></td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm" onclick="editUser('${user.id}');"><i class="fas fa-user-edit"></i></button>
                        <button type="button" class="btn btn-danger btn-sm" onclick="removeUser('${user.id}');"><i class="fas fa-user-slash"></i></button>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>