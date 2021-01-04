<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 1></@navbar.navbar>
    <div class="grey-bg container-fluid">
        <section id="minimal-statistics">
            <div class="row">
                <div class="col-12 mt-3 mb-1">
                    <h4 class="text-uppercase">Основная статистика сайта курсового проекта по WEB-программированию от Максимова Д.И.</h4>
                    <p>Статистика в наглядных карточках.</p>
                    <button type="button" class="btn btn-success" onclick="getGeneralInfo();"><i class="fas fa-sync-alt"></i></button>
                    <@modal.updatePanel "стран"></@modal.updatePanel>
                    <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                    <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-globe primary font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="countries">${info.countries}</h3>
                                        <span>Стран</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-plane warning font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="planes">${info.planes}</h3>
                                        <span>Самолетов</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-building success font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="airlines">${info.airlines}</h3>
                                        <span>Авиакомпаний</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-receipt font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="bookedTickets">${info.bookedTickets}</h3>
                                        <span>Забронированных билетов</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="danger" id="luggage">${info.luggage}</h3>
                                        <span>Видов багажа</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-suitcase-rolling danger font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="success" id="roles">${info.roles}</h3>
                                        <span>Ролей</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-user-tag success font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="warning" id="users">${info.users}</h3>
                                        <span>Пользователей</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-users warning font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="primary" id="tickets">${info.tickets}</h3>
                                        <span>Билетов на продаже</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-ticket-alt primary font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-plane-arrival primary font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="airports">${info.airports}</h3>
                                        <span>Аэропортов</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script src="adminPanel.js"></script>
</@page.page>