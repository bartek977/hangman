const REGISTER_FORM = 0;
const LOGIN_FORM = 1;

const number1 = Math.floor(Math.random() * 10);
const number2 = Math.floor(Math.random() * 10);

function change_form(numberForm) {
    var form = document.getElementById("login-form");
    var link = document.getElementById("link-change-form");
    if(numberForm==LOGIN_FORM) {
        form.innerHTML = `
        <form name="myForm" method="post" action="/game" onsubmit="return validateForm(1);">
        <table id="table-form"><tr>Logowanie</tr>

                    <tr>
                        <td>Login:</td>
                        <td><input name="username" required /></td>
                    </tr>
                    <tr>
                        <td>Hasło:</td>
                        <td><input type="password" name="password" required /></td>
                        <td><input type="submit" value="Zaloguj!"></td>
                    </tr>

                </table>
                </form>`;
        link.text = "Rejestracja";
        link.setAttribute('onclick','change_form(0);return false;')
    }
    else if(numberForm==REGISTER_FORM) {
        form.innerHTML = `
        <form name="myForm" method="post" action="/register" onsubmit="return validateForm(0);">
        <table id="table-form"><tr>Rejestracja</tr>

                    <tr>
                        <td>Login:</td>
                        <td><input name="username" required /></td>
                    </tr>
                    <tr>
                        <td>Hasło:</td>
                        <td><input type="password" name="password" required /></td>
                    </tr>
                    <tr>
                        <td>${number1}+${number2}=</td>
                        <td><input type="number" name="result" required /></td>
                        <td><input type="submit" value="Zarejestruj!"></td>
                    </tr>

                </table>
                </form>`;
        link.text = "Logowanie";
        link.setAttribute('onclick','change_form(1);return false;')
    }
}

function validateForm(numberForm) {
    console.log("jestem tutaj");
    if(numberForm == LOGIN_FORM) {
        //check if login:pass is in db
    }
    else if(numberForm == REGISTER_FORM) {
        var result = document.forms["myForm"]["result"].value;
        //check if result is correct and login is free
        //dowiedziec sie czy bezpieczne jest sprawdzanie loginu/hasla po api
        if(!(result == (number1+number2))) {
            alert("Błędny wynik!");
            return false;
        }
    }
}