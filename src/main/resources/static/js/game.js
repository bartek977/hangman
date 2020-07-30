const ALPHABET = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŻŹ";
const API_URL = 'http://localhost:8081/api';
const PASSWORD_API_URL = `${API_URL}/passwords`;
const GAME_API_URL = `${API_URL}/games`;

var password;
var passwordId;
var passwordGame="";
var passwordLength;
var counter = 0;

async function getPassword() {
    const response = await fetch(PASSWORD_API_URL)
                           .then(res => res.json())
                           .then(data => {
                           password = data.password;
                           passwordId = data.id;
                           });
}

async function addPoints() {
    const response = await fetch(`${GAME_API_URL}?passwordId=${passwordId}`);
}

function write_password() {
    document.getElementById("password").innerHTML = passwordGame;
}

window.onload = start;

async function start() {

    await getPassword();
    password = password.toUpperCase();
    passwordLength = password.length;

    for(i=0; i<passwordLength; i++) {
        if(password.charAt(i)==' ') {
            passwordGame += " ";
        }
        else {
            passwordGame += "-";
        }
    }

    var letters = "";

    for(i=0; i<35; i++) {
        var element = "letter" + i;
        letters += '<div class="letter" onclick="check('+i+')" id="' + element + '">' + ALPHABET.charAt(i) + '</div>';
        if((i+1)%7==0) {
            letters += '<div style="clear:both;"></div>';
        }
    }
    document.getElementById("alphabet").innerHTML = letters;

    write_password();
}

String.prototype.replaceAt = function(index, replacement) {
    return this.substr(0, index) + replacement + this.substr(index + replacement.length);
}

function check(nr) {

    var isLetterInPassword = false;

    for(i=0; i<passwordLength; i++) {
        if(password.charAt(i)==ALPHABET.charAt(nr)) {
            passwordGame = passwordGame.replaceAt(i, ALPHABET.charAt(nr));
            isLetterInPassword = true;

        }
    }

    if(isLetterInPassword) {
        write_password();
        var element = "letter" + nr;
        document.getElementById(element).style.background = "#003300";
        document.getElementById(element).style.color = "#00C000";
        document.getElementById(element).style.border = "3px solid #00C000";
        document.getElementById(element).style.cursor = "default";

    }
    else {
        counter++;
        document.getElementById("game").innerHTML = '<img src="img/' + counter + '.jpg" />'
        var element = "letter" + nr;
        document.getElementById(element).style.background = "#330000";
        document.getElementById(element).style.color = "#C00000";
        document.getElementById(element).style.border = "3px solid #C00000";
        document.getElementById(element).style.cursor = "default";
        document.getElementById(element).setAttribute("onclick", ";");
    }

    if(counter>8) {
        document.getElementById("alphabet").innerHTML  = "Przegrana! Prawidłowe hasło: "+password+'<br /><br /><span class="reset" onclick="location.reload()">JESZCZE RAZ?</span>';
    }
    if(passwordGame == password) {
        document.getElementById("alphabet").innerHTML  = "Tak jest! Podano prawidłowe hasło: "+password+'<br /><br /><span class="reset" onclick="location.reload()">JESZCZE RAZ?</span>';
        addPoints();
        }
}