<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 4></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "аэропорта">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="AirportNameAdd">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirportNameAdd">
                <small id="AirLineName" class="form-text text-muted">Название аэропорта.</small>
            </div>
            <div class="form-group">
                <label for="AirportSNameAdd">Код аэропорта ИАТА</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Код аэропорта ИАТА" id="AirportSNameAdd">
                <small id="AirLineName" class="form-text text-muted">Код аэропорта ИАТА.</small>
            </div>
            <div class="form-group">
                <label for="AirportAddressAdd">Адрес</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Адрес" id="AirportAddressAdd">
                <small id="AirLineName" class="form-text text-muted">Адрес аэропорта.</small>
            </div>
            <div class="form-group">
                <label for="AirportCountry">Страна</label>
                <select class="form-control" id="AirportCountry">
                    <#list countries as country>
                        <option value="${country.id}">${country.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <@modal.addmodalFooter "addAirport()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "аэропорта">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID аэропорта позволяет идентифицировать его в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="AirportNameEdit">Название</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirportNameEdit">
                <small id="AirLineName" class="form-text text-muted">Название аэропорта.</small>
            </div>
            <div class="form-group">
                <label for="AirportSNameEdit">Код аэропорта ИАТА</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Код аэропорта ИАТА" id="AirportSNameEdit">
                <small id="AirLineName" class="form-text text-muted">Код аэропорта ИАТА.</small>
            </div>
            <div class="form-group">
                <label for="AirportAddressEdit">Адрес</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Адрес" id="AirportAddressEdit">
                <small id="AirLineName" class="form-text text-muted">Адрес аэропорта.</small>
            </div>
            <div class="form-group">
                <label for="AirportCountryEdit">Страна</label>
                <select class="form-control" id="AirportCountryEdit">
                    <#list countries as country>
                        <option value="${country.id}">${country.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <@modal.modalFooter "saveAirport()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Аэропорты.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getAirports();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "аэропортов"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Название</th>
                    <th scope="col">Код аэропорта ИАТА</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Страна</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="AirportsList">
                <#list airports as airport>
                    <tr>
                        <th scope="row">${airport.id}</th>
                        <td>${airport.name}</td>
                        <td>${airport.shortName}</td>
                        <td>${airport.address}</td>
                        <td>${airport.country.name}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editAirport('${airport.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeAirport('${airport.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>