<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 6></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "страны">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="CountryNameAdd">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="CountryNameAdd">
                <small id="AirLineName" class="form-text text-muted">Название страны.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addCountry()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "страны">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID страны позволяет идентифицировать ее в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="CountryNameEdit">Название</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="CountryNameEdit">
                <small id="AirLineName" class="form-text text-muted">Название страны.</small>
            </div>
        </div>
        <@modal.modalFooter "saveCountry()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Страны.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getCountries();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "стран"></@modal.updatePanel>
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
                <tbody id="CountriesList">
                <#list countries as country>
                    <tr>
                        <th scope="row">${country.id}</th>
                        <td>${country.name}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editCountry('${country.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeCountry('${country.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>