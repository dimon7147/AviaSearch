<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 8></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "самолета">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="AirplaneNameAdd">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirplaneNameAdd">
                <small id="AirLineName" class="form-text text-muted">Название самолета.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addPlane()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "самолета">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID самолета позволяет идентифицировать его в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="AirplaneNameEdit">Название</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="AirplaneNameEdit">
                <small id="AirLineName" class="form-text text-muted">Название самолета.</small>
            </div>
        </div>
        <@modal.modalFooter "savePlane()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Самолеты.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getPlanes();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "самолетов"></@modal.updatePanel>
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
                <tbody id="PlanesList">
                <#list planes as plane>
                    <tr>
                        <th scope="row">${plane.id}</th>
                        <td>${plane.name}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editPlane('${plane.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removePlane('${plane.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>