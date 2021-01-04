<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 3></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "авиакомпании">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="AirLineNameAdd">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirLineNameAdd">
                <small id="AirLineName" class="form-text text-muted">Название авиакомпании.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addAvia()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "авиакомпании">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID авиакомпании позволяет идентифицировать ее в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="AirLineNameEdit">Название</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirLineNameEdit">
                <small id="AirLineName" class="form-text text-muted">Название авиакомпании.</small>
            </div>
        </div>
        <@modal.modalFooter "saveAvia()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Авиакомпании.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getAirlines();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "авиакомпаний"></@modal.updatePanel>
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
                <tbody id="AirlinesList">
                <#list airlines as airline>
                    <tr>
                        <th scope="row">${airline.id}</th>
                        <td>${airline.name}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editAvia('${airline.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeAvia('${airline.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>