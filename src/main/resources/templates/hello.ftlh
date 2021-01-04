<#import "user/parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<@page.page "Главная">
    <#if user.id == -1>
        <#assign userRole = false, adminRole = false>
    <#else>
        <#assign userRole = true, adminRole = user.isAdmin()>
    </#if>
    <@navbar.navbar 1,userRole,adminRole></@navbar.navbar>
    <div class="row">
        <div class="col">
            <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
            <div class="alert alert-danger" role="alert" id="ErrorInfo" style="display: none"></div>
            <div class="card text-center">
                <div class="card-header">
                    <h1>Поиск авиабилетов</h1>
                </div>
                <div class="card-body">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select1">Аэропорт вылета</label>
                        </div>
                        <select class="custom-select" id="select1">
                            <#list airports as airport>
                            <option value="${airport.id}">${airport.name} (${airport.shortName})</option>
                            </#list>
                        </select>
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select2">Аэророрт назначения</label>
                        </div>
                        <select class="custom-select" id="select2">
                            <#list airports as airport>
                                <option value="${airport.id}">${airport.name} (${airport.shortName})</option>
                            </#list>
                        </select>
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select3">Багаж</label>
                        </div>
                        <select class="custom-select" id="select3">
                            <option selected value="-1">Не имеет значения</option>
                            <#list luggage as lug>
                                <option value="${lug.id}">${lug.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">С пересадками?</label>
                        </div>
                        <select class="custom-select" id="select4">
                            <option selected value="false">Без пересадок</option>
                            <option value="true">С пересадками</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="DateMain">Дата вылета</label>
                        <input name="airlineName" value="${dateNow}" type="date" class="form-control"
                               aria-describedby="AirLineName" placeholder="Адрес" id="DateMain">
                    </div>
                    <button type="button" class="btn btn-success" onclick="searchTickets();">
                        <i class="fas fa-search" style="padding-right: 10px"></i>Поиск</button>
                </div>
                <div class="card-footer text-muted">
                </div>
            </div>
        </div>
        <div class="col" style="padding: 100px 100px; display: none;" id="ticketsMain">
        </div>
    </div>
    <script src="public/MainPage.js"></script>
</@page.page>