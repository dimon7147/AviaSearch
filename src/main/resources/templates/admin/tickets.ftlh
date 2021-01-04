<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 10></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "билета">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="departureAirAdd">Аэропорт вылета</label>
                <select class="selectpicker" data-live-search="true" id="departureAirAdd">
                        <#list airports as airport>
                        <option value="${airport.id}">${airport.shortName}</option>
                        </#list>
                </select>
            </div>
                <div class="form-group">
                    <label for="destinationAirAdd">Аэропорт назначения</label>
                    <select class="selectpicker" data-live-search="true" id="destinationAirAdd">
                        <#list airports as airport>
                            <option value="${airport.id}">${airport.shortName}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="plainTAdd">Самолет</label>
                    <select class="selectpicker" data-live-search="true" id="plainTAdd">
                        <#list planes as plane>
                            <option value="${plane.id}">${plane.name}</option>
                        </#list>
                    </select>
                </div>
            <div class="form-group">
                <label for="AirCompanyTAdd">Авиакомпания</label>
                <select class="selectpicker" data-live-search="true" id="AirCompanyTAdd">
                    <#list airlines as airline>
                        <option value="${airline.id}">${airline.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="LuggageTAdd">Тип багажа</label>
                <select class="selectpicker" data-live-search="true" id="LuggageTAdd">
                    <#list luggage as lug>
                        <option value="${lug.id}">${lug.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="FromTimeTAdd">Время отбытия</label>
                <input name="id" type="datetime-local" class="form-control" id="FromTimeTAdd" aria-describedby="idHelp" placeholder="Время вылета">
                <small id="idHelp" class="form-text text-muted">Запланированное время вылета.</small>
            </div>
            <div class="form-group">
                <label for="ToTimeTAdd">Время прибытия</label>
                <input name="id" type="datetime-local" class="form-control" id="ToTimeTAdd" aria-describedby="idHelp" placeholder="Время прилета">
                <small id="idHelp" class="form-text text-muted">Запланированное время прилета.</small>
            </div>
            <div class="form-group">
                <label for="TicketPriceAdd">Цена билета</label>
                <input name="id" type="number" class="form-control" id="TicketPriceAdd" aria-describedby="idHelp" placeholder="Цена в рублях">
                <small id="idHelp" class="form-text text-muted">Цена билета.</small>
            </div>
            <div class="form-group">
                <label for="AmountOfTAdd">Количество билетов</label>
                <input name="id" type="number" class="form-control" id="AmountOfTAdd" aria-describedby="idHelp" placeholder="Количество билетов">
                <small id="idHelp" class="form-text text-muted">Максимальное количество билетов для продажи.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addTicket()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "билета">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID билета позволяет идентифицировать его в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="departureAirEdit">Аэропорт вылета</label>
                <select class="selectpicker" data-live-search="true" id="departureAirEdit">
                    <#list airports as airport>
                        <option value="${airport.id}">${airport.shortName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="destinationAirEdit">Аэропорт назначения</label>
                <select class="selectpicker" data-live-search="true" id="destinationAirEdit">
                    <#list airports as airport>
                        <option value="${airport.id}">${airport.shortName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="plainTEdit">Самолет</label>
                <select class="selectpicker" data-live-search="true" id="plainTEdit">
                    <#list planes as plane>
                        <option value="${plane.id}">${plane.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="AirCompanyTEdit">Авиакомпания</label>
                <select class="selectpicker" data-live-search="true" id="AirCompanyTEdit">
                    <#list airlines as airline>
                        <option value="${airline.id}">${airline.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="LuggageTEdit">Тип багажа</label>
                <select class="selectpicker" data-live-search="true" id="LuggageTEdit">
                    <#list luggage as lug>
                        <option value="${lug.id}">${lug.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="FromTimeTEdit">Время отбытия</label>
                <input name="id" type="datetime-local" class="form-control" id="FromTimeTEdit" aria-describedby="idHelp" placeholder="Запланированное время вылета">
                <small id="idHelp" class="form-text text-muted">Запланированное время вылета.</small>
            </div>
            <div class="form-group">
                <label for="ToTimeTEdit">Время прибытия</label>
                <input name="id" type="datetime-local" class="form-control" id="ToTimeTEdit" aria-describedby="idHelp" placeholder="Запланированное время прилета">
                <small id="idHelp" class="form-text text-muted">Запланированное время прилета.</small>
            </div>
            <div class="form-group">
                <label for="TicketPriceEdit">Цена билета</label>
                <input name="id" type="number" class="form-control" id="TicketPriceEdit" aria-describedby="idHelp" placeholder="Цена в рублях">
                <small id="idHelp" class="form-text text-muted">Цена билета.</small>
            </div>
            <div class="form-group">
                <label for="AmountOfTEdit">Количество билетов</label>
                <input name="id" type="number" class="form-control" id="AmountOfTEdit" aria-describedby="idHelp" placeholder="Количество билетов">
                <small id="idHelp" class="form-text text-muted">Максимальное количество предметов для продажи.</small>
            </div>
        </div>
        <@modal.modalFooter "saveTicket()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Билеты.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getTickets();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "билетов"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Аэропорт вылета</th>
                    <th scope="col">Аэропорт назначения</th>
                    <th scope="col">Самолет</th>
                    <th scope="col">Авиакомпания</th>
                    <th scope="col">Тип багажа</th>
                    <th scope="col">Время отбытия</th>
                    <th scope="col">Время прибытия</th>
                    <th scope="col">Цена билета</th>
                    <th scope="col">Количество билетов</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="TicketsList">
                <#list tickets as ticket>
                    <tr>
                        <th scope="row">${ticket.id}</th>
                        <td>${ticket.departureAirport.name}</td>
                        <td>${ticket.destinationAirport.name}</td>
                        <td>${ticket.plane.name}</td>
                        <td>${ticket.airline.name}</td>
                        <td>${ticket.luggage.name}</td>
                        <td>${ticket.arrivalTime}</td>
                        <td>${ticket.departureTime}</td>
                        <td>${ticket.price}</td>
                        <td>${ticket.ticketsAmount}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editTicket('${ticket.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeTicket('${ticket.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>