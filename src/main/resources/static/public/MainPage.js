function searchTickets() {
    $("#ticketsMain").html("    <div class=\"modal-body\" id=\"tableLoad\">\n" +
        "        <div class=\"d-flex justify-content-center\">\n" +
        "            Идет загрузка данных, пожалуйста, ожидайте...\n" +
        "            <div class=\"spinner-border\" role=\"status\">\n" +
        "                <span class=\"sr-only\">Загрузка...</span>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>").show();
    var date = $('#DateMain').val();
    var from = $('#select1').val();
    var to = $('#select2').val();
    var luggage = $('#select3').val();
    var transit = $('#select4').val();
    var url = 'http://localhost:8080/api/public/tickets?date=' +
        date + "&depart_airport=" + from + "&dest_airport=" +
        to + "&luggage_type=" + luggage + "&transfer_allowed=" + transit;
    console.log(url)
    if (transit === "false") {
        var html = "";
        $.ajax({
            url: url,
            dataType: "json",
            data: null,
            type: "GET",
            success: function(data) {
                for (var t in data) {
                    html +="            <article class=\"card\">\n" +
                        "                <section class=\"date\">\n" +
                        "                    <time datetime=\"23th feb\">\n" +
                        "                        <span>Билет</span>\n" +
                        "                        <span> <i class=\"fas fa-ruble-sign\"></i> " + data[t].price + "</span>\n" +
                        "                        <button onclick='buyTicket(" + data[t].id +")'" +
                        " type=\"button\" class=\"btn btn-success\"><i class=\"fas fa-shopping-cart\"></i> Купить</button>\n" +
                        "                    </time>\n" +
                        "                </section>\n" +
                        "                <section class=\"card-cont\">\n";
                    html+= "<small>Вылет из аэропорта</small>\n" +
                        "<h2><i class=\"fas fa-plane-departure\" style=\"padding-right: 5px\"></i>"
                        + data[t].departureAirport.name + "</h2>";
                    html+= "<small>Прибытие в аэропорт</small>\n" +
                        "<h2><i class=\"fas fa-plane-arrival\" style=\"padding-right: 5px\"></i>"
                        + data[t].destinationAirport.name + "</h2>";
                    html+= "                    <div class=\"even-info\">\n" +
                        "                        <i class=\"fas fa-calendar\"></i>\n" +
                        "                        <p>Вылет:</p>\n" +
                        "                        <h2>" + data[t].departureTime + "</h2>\n" +
                        "                        <i class=\"fas fa-calendar-check\"></i>\n" +
                        "                        <p>Прибытие:</p>\n" +
                        "                        <h2>" + data[t].arrivalTime + "</h2>\n" +
                        "                    </div>";
                    html+= "                    <div class=\"even-info\">\n" +
                        "                        <i class=\"fas fa-plane\"></i>\n" +
                        "                        <p>Самолет</p>\n" +
                        "                        <h2>" + data[t].plane.name + "</h2>\n" +
                        "                        <i class=\"fas fa-suitcase-rolling\"></i>\n" +
                        "                        <p>Тип багажа</p>\n" +
                        "                        <h2>" + data[t].luggage.name + "</h2>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"even-info\">\n" +
                        "                        <i class=\"fas fa-ruble-sign\"></i>\n" +
                        "                        <p>Цена:</p>\n" +
                        "                        <h2>" + data[t].price + "</h2>\n" +
                        "                        <i class=\"fas fa-building\"></i>\n" +
                        "                        <p>Авиакомпания</p>\n" +
                        "                        <h2>" + data[t].airline.name + "</h2>\n" +
                        "                    </div>";
                    html+= "                    </section>\n" +
                        "            </article><br><br>";

                }
                $('#ticketsMain').html(html).show();
                if (data.length === 0) {
                    $('#successInfo').text("Билетов не найдено =(").show().delay(5000).fadeOut();
                } else {
                    $('#successInfo').text("Билеты найдены!").show().delay(5000).fadeOut();
                }
            },
            error: function (data) {
                $('#ErrorInfo').text("Ошибка. Проверьте, указали ли вы все поля?").show().delay(5000).fadeOut();
            },
        });
    } else {
        $.ajax({
            url: url,
            dataType: "json",
            data: null,
            type: "GET",
            success: function (data) {
                //Найденные результаты (Аэропорты + Билеты)
                var html = "";
                var lastel = 0;
                var lastt = 0;
                var transfer_amount = 0;
                for (var el in data) {
                    lastel = el;
                    transfer_amount = data[el].amount;
                    html += "            <article class=\"card\">\n" +
                        "                <section class=\"date\">\n" +
                        "                    <time datetime=\"23th feb\">\n" +
                        "                        <span>Билет</span>\n" +
                        "                        <span>Пересадок: " + (transfer_amount - 2) + "</span>\n" +
                        "                        <button id=\"buyButton" + el + "\" onclick='buyTicket1(" + el + ")' " +
                        "type=\"button\" class=\"btn btn-success\"><i class=\"fas fa-shopping-cart\"></i> Купить</button>\n" +
                        "                    </time>\n" +
                        "                </section>\n" +
                        "                <section class=\"card-cont\">\n";
                    var tickets = data[el].tickets;
                    var ids = "";
                    for (var t in tickets){
                        ids += tickets[t].id + " ";
                        lastt = t;
                        html += "<small>Вылет из аэропорта</small>\n" +
                            "<h2><i class=\"fas fa-plane-departure\" style=\"padding-right: 5px\"></i>"
                            + tickets[t].departureAirport.name + "</h2>";
                        html += "<small>Прибытие в аэропорт</small>\n" +
                            "<h2><i class=\"fas fa-plane-arrival\" style=\"padding-right: 5px\"></i>"
                            + tickets[t].destinationAirport.name + "</h2>";
                        html += "                    <div class=\"even-info\">\n" +
                            "                        <i class=\"fas fa-calendar\"></i>\n" +
                            "                        <p>Вылет:</p>\n" +
                            "                        <h2>" + tickets[t].departureTime + "</h2>\n" +
                            "                        <i class=\"fas fa-calendar-check\"></i>\n" +
                            "                        <p>Прибытие:</p>\n" +
                            "                        <h2>" + tickets[t].arrivalTime + "</h2>\n" +
                            "                    </div>";
                        html += "                    <div class=\"even-info\">\n" +
                            "                        <i class=\"fas fa-plane\"></i>\n" +
                            "                        <p>Самолет</p>\n" +
                            "                        <h2>" + tickets[t].plane.name + "</h2>\n" +
                            "                        <i class=\"fas fa-suitcase-rolling\"></i>\n" +
                            "                        <p>Тип багажа</p>\n" +
                            "                        <h2>" + tickets[t].luggage.name + "</h2>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"even-info\">\n" +
                            "                        <i class=\"fas fa-ruble-sign\"></i>\n" +
                            "                        <p>Цена:</p>\n" +
                            "                        <h2>" + tickets[t].price + "</h2>\n" +
                            "                        <i class=\"fas fa-building\"></i>\n" +
                            "                        <p>Авиакомпания</p>\n" +
                            "                        <h2>" + tickets[t].airline.name + "</h2>\n" +
                            "                    </div>";
                    }
                    html += "                    </section><input id='valueForBuy" + el
                        + "' value='" + ids + "' style='display: none'></input>\n" +
                        "            </article><br><br>";
                }
                $('#ticketsMain').html(html).show();
                if (data.length === 0) {
                    $('#successInfo').text("Билетов не найдено =(").show().delay(5000).fadeOut();
                } else {
                    $('#successInfo').text("Билеты найдены!").show().delay(5000).fadeOut();
                }
            },
            error: function (data) {
                $('#ErrorInfo').text("Ошибка. Проверьте, указали ли вы все поля?").show().delay(5000).fadeOut();
            },
        });
    }
}

function buyTicket1(value) {
    var text = $('#valueForBuy' + value).val().trim();
    var array = text.split(' ');
    var url = 'http://localhost:8080/api/public/tickets/buys?ids='+array;
    console.log(url)
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#successInfo').text("Билеты успешно куплены!").show().delay(5000).fadeOut();
        },
        error: function (data) {
            $('#ErrorInfo').text("Для покупки билетов нужна авторизация!").show().delay(5000).fadeOut();
        },
    });
}
function buyTicket(id) {
    var url = 'http://localhost:8080/api/public/tickets/buy?id='+id;
    console.log(url)
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#successInfo').text("Билеты успешно куплены!").show().delay(5000).fadeOut();
        },
        error: function (data) {
            $('#ErrorInfo').text("Для покупки билетов нужна авторизация!").show().delay(5000).fadeOut();
        },
    });
}