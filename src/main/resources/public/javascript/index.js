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

