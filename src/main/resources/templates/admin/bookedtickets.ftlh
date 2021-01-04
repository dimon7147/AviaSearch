<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 5></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "билета">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="ticketBAdd">Билет</label>
                <select class="selectpicker" data-live-search="true" id="ticketBAdd">
                    <#list tickets as ticket>
                        <option value="${ticket.id}">${ticket.departureAirport.name} -> ${ticket.destinationAirport.name} прибытие ${ticket.arrivalTime}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="userBAdd">Пассажир</label>
                <select class="selectpicker" data-live-search="true" id="userBAdd">
                    <#list users as user>
                        <option value="${user.id}">${user.fullName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="priceBAdd">Цена билета</label>
                <input name="id" type="number" class="form-control" id="priceBAdd" aria-describedby="idHelp" placeholder="Цена билета">
                <small id="idHelp" class="form-text text-muted">Цена, за которую пользователь приобрел билет.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addBTicket()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "билета">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID билета позволяет идентифицировать его в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="ticketBEdit">Билет</label>
                <select class="selectpicker" data-live-search="true" id="ticketBEdit">
                    <#list tickets as ticket>
                        <option value="${ticket.id}">${ticket.departureAirport.name} -> ${ticket.destinationAirport.name} прибытие ${ticket.arrivalTime}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="userBEdit">Пассажир</label>
                <select class="selectpicker" data-live-search="true" id="userBEdit">
                    <#list users as user>
                        <option value="${user.id}">${user.fullName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="priceBEdit">Цена билета</label>
                <input name="id" type="number" class="form-control" id="priceBEdit" aria-describedby="idHelp" placeholder="Цена билета">
                <small id="idHelp" class="form-text text-muted">Цена, за которую пользователь приобрел билет.</small>
            </div>
            <div class="form-group">
                <label for="boughtTimeB">Дата покупки</label>
                <input name="id" type="datetime-local" class="form-control" id="boughtTimeB" aria-describedby="idHelp" placeholder="Дата покупки билета" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">Дата покупки билета, менять нельзя.</small>
            </div>
        </div>
        <@modal.modalFooter "saveBTicket()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Билеты.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getBTickets();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "билетов"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Билет</th>
                    <th scope="col">Пассажир</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Дата покупки</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="BTicketsList">
                <#list bookedtickets as bookedticket>
                    <tr>
                        <th scope="row">${bookedticket.id}</th>
                        <td>${bookedticket.ticket.departureAirport.name} -> ${bookedticket.ticket.destinationAirport.name} прибытие ${bookedticket.ticket.arrivalTime}</td>
                        <td>${bookedticket.user.fullName}</td>
                        <td>${bookedticket.price}</td>
                        <td>${bookedticket.time}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editBTicket('${bookedticket.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeBTicket('${bookedticket.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>