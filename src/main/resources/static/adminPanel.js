$(function() {
    console.log( "ready!" );
    $('#successInfo').hide();
    //getGeneralInfo();
});
function getGeneralInfo() {
    var url = 'http://localhost:8080/api/admin/info';
    $('#countries').text("Загрузка..");
    $('#planes').text("Загрузка..");
    $('#airlines').text("Загрузка..");
    $('#bookedTickets').text("Загрузка..");
    $('#luggage').text("Загрузка..");
    $('#roles').text("Загрузка..");
    $('#users').text("Загрузка..");
    $('#tickets').text("Загрузка..");
    $('#airports').text("Загрузка..");
    console.log(url)
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#countries').text(data.countries);
            $('#planes').text(data.planes);
            $('#airlines').text(data.airlines);
            $('#bookedTickets').text(data.bookedTickets);
            $('#luggage').text(data.luggage);
            $('#roles').text(data.roles);
            $('#users').text(data.users);
            $('#tickets').text(data.tickets);
            $('#airports').text(data.airports);
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getUsers() {
    $('#UsersList').text("")
    $('#tableLoad').show();
    //$('#UsersList').text("Куку");
    var url = 'http://localhost:8080/api/admin/users';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var user in data) {
                table += "<th scope=\"row\">" + data[user].id + "</th>";
                table += "<td>" + data[user].login + "</td>";
                table += "<td>" + data[user].fullName + "</td>";
                table += "<td>" + data[user].email + "</td>";
                table += "<td>" + data[user].passport + "</td>";
                table += "<td>" + data[user].dateOfBirth + "</td>";
                table += "<td>" + data[user].registerTime + "</td>";
                if (data[user].active === true) {
                    table += "<td>Да</td>";
                } else {
                    table += "<td>Нет</td>";
                }
                var roles = data[user].roles;
                table += "<td>";
                for (var role in roles) {
                    table += roles[role].role + " ";
                }
                table += "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editUser(" + data[user].id + ");\"><i class=\"fas fa-user-edit\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeUser(" + data[user].id + ");\"><i class=\"fas fa-user-slash\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#UsersList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getAirlines() {
    $('#AirlinesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/airlines';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editAvia(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeAvia(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#AirlinesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getAirports() {
    $('#AirportsList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/airports';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>" + data[field].shortName + "</td>";
                table += "<td>" + data[field].address + "</td>";
                table += "<td>" + data[field].country.name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editAirport(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeAirport(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#AirportsList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getBTickets() {
    $('#BTicketsList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/bookedtickets';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].ticket.departureAirport.name + " -> " + data[field].ticket.destinationAirport.name + " прибытие " + data[field].ticket.arrivalTime + "</td>";
                table += "<td>" + data[field].user.fullName + "</td>";
                table += "<td>" + data[field].price + "</td>";
                table += "<td>" + data[field].time + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editBTicket(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeBTicket(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#BTicketsList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getCountries() {
    $('#CountriesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/countries';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editCountry(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeCountry(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#CountriesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getLuggage() {
    $('#LuggageList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/luggage';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editLuggage(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeLuggage(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#LuggageList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getPlanes() {
    $('#PlanesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/planes';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editPlane(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removePlane(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#PlanesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getRoles() {
    $('#rolesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/roles';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].role + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editRole(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeRole(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#rolesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function getTickets() {
    $('#TicketsList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/tickets';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].departureAirport.name + "</td>";
                table += "<td>" + data[field].destinationAirport.name + "</td>";
                table += "<td>" + data[field].plane.name + "</td>";
                table += "<td>" + data[field].airline.name + "</td>";
                table += "<td>" + data[field].luggage.name + "</td>";
                table += "<td>" + data[field].departureTime + "</td>";
                table += "<td>" + data[field].arrivalTime + "</td>";
                table += "<td>" + data[field].price + "</td>";
                table += "<td>" + data[field].ticketsAmount + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editTicket(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeTicket(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#TicketsList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function editUser(id) {
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#userEdit').modal();
    var url = 'http://localhost:8080/api/admin/users/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#loginEdit').val(data.login);
            $('#fullNameEdit').val(data.fullName);
            $('#emailEdit').val(data.email);
            $('#passportEdit').val(data.passport);
            $('#bDateEdit').val(data.dateOfBirth);
            $('#rDateEdit').val(data.registerTime);
            if (data.active === true) {
                $('#activeEdit').val("1").change();
            } else {
                $('#activeEdit').val("0").change();
            }
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function saveUser() {
    var act;
    act = $('#activeEdit').val() === "1";
    var rolesq = [];
    var arr =  $('#rolesEditU').val();
    for (var role in arr){
        var trueRole = {
            id: arr[role]
        }
        rolesq.push(trueRole);
    }
    var user = {
        id: $('#idEdit').val(),
        login: $('#loginEdit').val(),
        fullName: $('#fullNameEdit').val(),
        email: $('#emailEdit').val(),
        passport: $('#passportEdit').val(),
        dateOfBirth: $('#bDateEdit').val(),
        registerTime: $('#rDateEdit').val(),
        roles: rolesq,
        active: act,
        password: "nope"
    }
    var url = 'http://localhost:8080/api/admin/users/' + user.id;
    console.log(user);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            getUsers();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(user),
    });
}

function removeUser(id) {
    var url = 'http://localhost:8080/api/admin/users/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getUsers();
            $('#successInfo').show().text(data.message);
        }
    });
}


function addDialog() {
    $('#readyToAdd').show();
    $('#waitingToEdit').hide();
    $('#modalAdd').modal();
}
function addUser() {
    var act = $('#activeEdit').val() === "1";
    var rolesq = [];
    var arr =  $('#rolesAddU').val();
    for (var role in arr){
        var trueRole = {
            id: arr[role]
        }
        rolesq.push(trueRole);
    }
    var user = {
        login: $('#loginAdd').val(),
        fullName: $('#fullNameAdd').val(),
        email: $('#emailAdd').val(),
        passport: $('#passportAdd').val(),
        dateOfBirth: $('#bDateAdd').val(),
        roles: rolesq,
        active: act,
        password: $('#passwordUAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/users/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getUsers();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(user),
    });
}

function addAvia() {
    var avia = {
        name: $('#AirLineNameAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/airlines/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getAirlines();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(avia),
    });
}
function editAvia(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/airlines/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#AirLineNameEdit').val(data.name);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveAvia() {
    var Avia = {
        id: $('#idEdit').val(),
        name: $('#AirLineNameEdit').val()
    }
    var url = 'http://localhost:8080/api/admin/airlines/' + Avia.id;
    console.log(Avia);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            getAirlines();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Avia),
    });
}
function removeAvia(id) {
    var url = 'http://localhost:8080/api/admin/airlines/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getAirlines();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}


function addRole() {
    var role = {
        role: $('#roleAddName').val()
    }
    var url = 'http://localhost:8080/api/admin/roles/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(role),
    });
}
function editRole(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/roles/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#roleEditName').val(data.role);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveRole() {
    var Role = {
        id: $('#idEdit').val(),
        role: $('#roleEditName').val()
    }
    var url = 'http://localhost:8080/api/admin/roles/' + Role.id;
    console.log(Role);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Role),
    });
}
function removeRole(id) {
    var url = 'http://localhost:8080/api/admin/roles/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}


function addCountry() {
    var country = {
        name: $('#CountryNameAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/countries/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getCountries();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(country),
    });
}
function editCountry(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/countries/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#CountryNameEdit').val(data.name);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveCountry() {
    var Country = {
        id: $('#idEdit').val(),
        name: $('#CountryNameEdit').val()
    }
    var url = 'http://localhost:8080/api/admin/countries/' + Country.id;
    console.log(Country);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getCountries();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Country),
    });
}
function removeCountry(id) {
    var url = 'http://localhost:8080/api/admin/countries/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getCountries();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addLuggage() {
    var luggage = {
        name: $('#LuggageNameAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/luggage/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getLuggage();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(luggage),
    });
}
function editLuggage(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/luggage/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#LuggageNameEdit').val(data.name);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveLuggage() {
    var Luggage = {
        id: $('#idEdit').val(),
        name: $('#LuggageNameEdit').val()
    }
    var url = 'http://localhost:8080/api/admin/luggage/' + Luggage.id;
    console.log(Luggage);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getLuggage();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Luggage),
    });
}
function removeLuggage(id) {
    var url = 'http://localhost:8080/api/admin/luggage/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getLuggage();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addPlane() {
    var plane = {
        name: $('#AirplaneNameAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/planes/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getPlanes();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(plane),
    });
}
function editPlane(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/planes/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#AirplaneNameEdit').val(data.name);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function savePlane() {
    var Plane = {
        id: $('#idEdit').val(),
        name: $('#AirplaneNameEdit').val()
    }
    var url = 'http://localhost:8080/api/admin/planes/' + Plane.id;
    console.log(Plane);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getPlanes();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
        $('#ErrorInfo').show().text(data.message);
    },
        data: JSON.stringify(Plane),
    });
}
function removePlane(id) {
    var url = 'http://localhost:8080/api/admin/planes/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getPlanes();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addAirport() {
    var country = {
        id: $('#AirportCountry').val()
    }
    console.log(country.id);
    var airport = {
        name: $('#AirportNameAdd').val(),
        address: $('#AirportAddressAdd').val(),
        shortName: $('#AirportSNameAdd').val(),
        country: country
    }
    var url = 'http://localhost:8080/api/admin/airports/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getAirports();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(airport),
    });
}
function editAirport(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/airports/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#AirportNameEdit').val(data.name);
            $('#AirportSNameEdit').val(data.shortName);
            $('#AirportAddressEdit').val(data.address);
            $('#AirportCountry').val(data.country.id);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveAirport() {
    var country = {
        id: $('#AirportCountryEdit').val()
    }
    var airport = {
        id: $('#idEdit').val(),
        name: $('#AirportNameEdit').val(),
        address: $('#AirportAddressEdit').val(),
        shortName: $('#AirportSNameEdit').val(),
        country: country
    }
    var url = 'http://localhost:8080/api/admin/airports/' + airport.id;
    console.log(airport);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getAirports();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(airport),
    });
}
function removeAirport(id) {
    var url = 'http://localhost:8080/api/admin/airports/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getAirports();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addTicket() {
    var departure_airport = {
        id: $('#departureAirAdd').val()
    }
    var destination_airport = {
        id: $('#destinationAirAdd').val()
    }
    var luggage_type = {
        id: $('#LuggageTAdd').val()
    }
    var plane = {
        id: $('#plainTAdd').val()
    }
    var airline = {
        id: $('#AirCompanyTAdd').val()
    }
    var ticket = {
        airline: airline,
        plane: plane,
        departureAirport: departure_airport,
        destinationAirport: destination_airport,
        luggage: luggage_type,
        price: $('#TicketPriceAdd').val(),
        departureTime: $('#FromTimeTAdd').val(),
        arrivalTime: $('#ToTimeTAdd').val(),
        ticketsAmount: $('#AmountOfTAdd').val()
    }
    console.log(ticket);
    var url = 'http://localhost:8080/api/admin/tickets/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(ticket),
    });
}
function editTicket(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/tickets/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#departureAirEdit').val(data.departureAirport.id).text(data.departureAirport.name);
            $('#destinationAirEdit').val(data.destinationAirport.id).text(data.destinationAirport.name);
            $('#LuggageTEdit').val(data.luggage.id).text(data.luggage.name);
            $('#plainTEdit').val(data.plane.id).text(data.plane.name);
            $('#AirCompanyTEdit').val(data.airline.id).text(data.airline.name);
            $('#TicketPriceEdit').val(data.price);
            $('#FromTimeTEdit').val(data.departureTime);
            $('#ToTimeTEdit').val(data.arrivalTime);
            $('#AmountOfTEdit').val(data.ticketsAmount);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveTicket() {
    var departure_airport = {
        id: $('#departureAirEdit').val()
    }
    var destination_airport = {
        id: $('#destinationAirEdit').val()
    }
    var luggage_type = {
        id: $('#LuggageTEdit').val()
    }
    var plane = {
        id: $('#plainTEdit').val()
    }
    var airline = {
        id: $('#AirCompanyTEdit').val()
    }
    var ticket = {
        id: $('#idEdit').val(),
        airline: airline,
        plane: plane,
        departureAirport: departure_airport,
        destinationAirport: destination_airport,
        luggage: luggage_type,
        price: $('#TicketPriceEdit').val(),
        departureTime: $('#FromTimeTEdit').val(),
        arrivalTime: $('#ToTimeTEdit').val(),
        ticketsAmount: $('#AmountOfTEdit').val()
    }

    var url = 'http://localhost:8080/api/admin/tickets/' + ticket.id;
    console.log(ticket);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(ticket),
    });
}
function removeTicket(id) {
    var url = 'http://localhost:8080/api/admin/tickets/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addBTicket() {
    var user = {
        id: $('#userBAdd').val()
    }
    var ticket = {
        id: $('#ticketBAdd').val()
    }
    var Bticket = {
        user: user,
        ticket: ticket,
        price: $('#priceBAdd').val()
    }
    console.log(Bticket);
    var url = 'http://localhost:8080/api/admin/bookedtickets/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getBTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Bticket),
    });
}
function editBTicket(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/bookedtickets/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#ticketBEdit').val(data.ticket.id);
            $('#userBEdit').val(data.user.id);
            $('#priceBEdit').val(data.price);
            $('#boughtTimeB').val(data.time);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}
function saveBTicket() {
    var user = {
        id: $('#userBEdit').val()
    }
    var ticket = {
        id: $('#ticketBEdit').val()
    }
    var Bticket = {
        id: $('#idEdit').val(),
        user: user,
        ticket: ticket,
        price: $('#priceBEdit').val()
    }

    var url = 'http://localhost:8080/api/admin/bookedtickets/' + Bticket.id;
    console.log(Bticket);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            getBTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Bticket),
    });
}
function removeBTicket(id) {
    var url = 'http://localhost:8080/api/admin/bookedtickets/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getBTickets();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}