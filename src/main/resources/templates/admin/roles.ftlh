<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 9></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "роли">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="roleAddName">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="roleAddName">
                <small id="AirLineName" class="form-text text-muted">Название роли.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addRole()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "роли">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID роли позволяет идентифицировать ее в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="roleEditName">Название</label>
                <input name="roleName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="roleEditName">
                <small id="AirLineName" class="form-text text-muted">Название роли.</small>
            </div>
        </div>
        <@modal.modalFooter "saveRole()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Роли.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getRoles();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "ролей"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Название</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="rolesList">
                <#list roles as role>
                    <tr>
                        <th scope="row">${role.id}</th>
                        <td>${role.role}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editRole('${role.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeRole('${role.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>