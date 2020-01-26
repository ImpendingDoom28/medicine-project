function searchForDiseases() {
    var result = $("#res");
    if ($("#disease-name").val().length >= 0) {
        $.ajax({
            url: "/dosearch",
            data: {
                "query": $("#disease-name").val()
            },
            dataType: "json",
            success: function (msg) {
                result.html("");
                if (msg.objects.length > 0) {
                    result.html("");
                    for (var i = 0; i < msg.objects.length; i++) {
                        result.append(searchHtmlString(msg.objects[i]));
                    }
                }
            }
        });
    } else {
        result.html("Type something in search!")
    }
}

function searchHtmlString(disease) {
    return String(
        "<div class=\"container\" style=\"border: #28a745 1.5vh solid;\">" +
                "<div class=\"row\">" +
                    "<div class=\"col-4\">" +
                        "<img src=\"" + disease.imagePath + "\" height=\"256\" width=\"256\" class=\"img-fluid\" alt=\"new\">" +
                    "</div>" +
                    "<div class=\"col-6\">" +
                        "<h1 class=\"display-4\">" + disease.name + "</h1>" +
                        "<span class=\"join\">" +
                            "<a href=\"/diseases/id" + disease.id + "\">Подробнее...</a>" +
                        "</span>" +
                    "</div>" +
                "</div>" +
              "</div>");
}