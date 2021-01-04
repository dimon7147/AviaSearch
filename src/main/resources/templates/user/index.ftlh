<#import "parts/page.ftlh" as page>
<#import "../parts/navbar.ftlh" as navbar>
<#import "parts/userParts.ftlh" as userP>
<@page.page "Личный кабинет">
    <@navbar.navbar 2,user.id != -1,user.isAdmin()></@navbar.navbar>
    <div class="grey-bg container-fluid">
        <section id="minimal-statistics">
            <div class="row">
                <div class="col-sm">
                    <@userP.editUserPart></@userP.editUserPart></div>
                <div class="col-sm">
                    <div class="modal-body" id="userTickets">
                        <div class="form-group" style="text-align: center">
                            <h1>Ваши билеты:</h1>
                            <#list tickets as ticket>
                            <article class="card">
                                <section class="date">
                                    <time datetime="23th feb">
                                        <span>${ticket.ticket.destinationAirport.country.name}</span>
                                        <span>${ticket.ticket.destinationAirport.name}</span>
                                        <button onclick='sellTicket(${ticket.id})' type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i> Вернуть билет</button>
                                    </time>
                                </section>
                                <section class="card-cont">
                                    <small>Вылет из аэропорта</small>
                                    <h2><i class="fas fa-plane-departure" style="padding-right: 5px"></i>${ticket.ticket.departureAirport.name}</h2>
                                    <small>Прибытие в аэропорт</small>
                                    <h2><i class="fas fa-plane-arrival" style="padding-right: 5px"></i>${ticket.ticket.destinationAirport.name}</h2>
                                    <div class="even-info">
                                        <i class="fas fa-calendar"></i>
                                        <p>Вылет:</p>
                                        <h2>${ticket.ticket.departureTime}</h2>
                                        <i class="fas fa-calendar-check"></i>
                                        <p>Прибытие:</p>
                                        <h2>${ticket.ticket.arrivalTime}</h2>
                                    </div>
                                    <div class="even-info">
                                        <i class="fas fa-plane"></i>
                                        <p>Самолет</p>
                                        <h2>${ticket.ticket.plane.name}</h2>
                                        <i class="fas fa-suitcase-rolling"></i>
                                        <p>Тип багажа</p>
                                        <h2>${ticket.ticket.luggage.name}</h2>
                                    </div>
                                    <div class="even-info">
                                        <i class="fas fa-ruble-sign"></i>
                                        <p>Цена:</p>
                                        <h2>${ticket.price}</h2>
                                        <i class="fas fa-building"></i>
                                        <p>Авиакомпания</p>
                                        <h2>${ticket.ticket.airline.name}</h2>
                                    </div>
                                </section>
                            </article><br><br>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script src="userPanel.js"></script>
</@page.page>