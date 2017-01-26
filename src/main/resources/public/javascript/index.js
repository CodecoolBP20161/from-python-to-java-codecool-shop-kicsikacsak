/**
 * Created by svindler on 15.12.2016.
 */

var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var ck_username = /^[A-Za-z0-9_]{1,20}$/;
var ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;


function checkUser() {
    var username = document.getElementById("usr");
    if (!ck_username.test(username.value)) {
        if(!document.getElementById("usererror").hasChildNodes()) {
            var div = document.createElement("div");
            var textnode = document.createTextNode("Invalid Username !");
            div.appendChild(textnode);
            document.getElementById("usererror").appendChild(div);
        }
    } else if(document.getElementById("usererror").hasChildNodes()) {
        error = document.getElementById("usererror");
        error.removeChild(error.childNodes[0]);
    }
}

function checkEmail() {
    var email = document.getElementById("email");
    if (!ck_email.test(email.value)) {
        if(!document.getElementById("emailerror").hasChildNodes()) {
            var div = document.createElement("div");
            var textnode = document.createTextNode("Invalid E-mail Adress !");
            div.appendChild(textnode);
            document.getElementById("emailerror").appendChild(div);
        }
    } else if(document.getElementById("emailerror").hasChildNodes()) {
        error = document.getElementById("emailerror");
        error.removeChild(error.childNodes[0]);
    }
}

function checkPassword() {
    var password = document.getElementById("pwd");
    if (!ck_password.test(password.value)) {
        if(!document.getElementById("passworderror").hasChildNodes()) {
            var div = document.createElement("div");
            var textnode = document.createTextNode("Invalid Password !");
            div.appendChild(textnode);
            document.getElementById("passworderror").appendChild(div);
        }
    } else if(document.getElementById("passworderror").hasChildNodes()) {
        error = document.getElementById("passworderror");
        error.removeChild(error.childNodes[0]);
    }
}

$(document).on("click", ".open-modal", function () {
    var myProductId = $(this).data('id');
    setIdForModal(myProductId);
    // As pointed out in comments,
    // it is superfluous to have to manually call the modal.
    $('#' + myProductId).modal('show');
});

$(document).on("click", ".closemodal", function () {
    $("#video_for_product").empty();

});

$(".modal").on("hidden.bs.modal", function(){
    $(".productVideo").html("");
});

function setIdForModal(productId) {
    var modal =  document.getElementsByClassName("video_for_product");
    $(modal).attr('id', productId);
    getData(productId);
    return true;
}

function fillData(json) {
    var productName = document.getElementsByClassName("productName");
    var video = document.getElementsByClassName("productVideo");
    $(video).append(json.videourl);
    $(productName).append(json.name);

}

function getData (productId) {
    $.ajax({
        type: "POST",
        url: '/getdata',
        data: JSON.stringify({"productid": productId, "status":"done"}),
        async: false,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (json) {
            data = json;
            fillData(data)
        }
    });
}

