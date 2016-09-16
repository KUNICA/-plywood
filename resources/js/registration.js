/**
 * Created by user on 15.09.2016.
 */
$(document).on('click', '#reg', function(e) {
    var urlObjects = "/registration/set";

    var fullName = document.getElementById("fullNameInput");
    var labelFullName = document.getElementById("labelFullName");

    var lastName = document.getElementById("lastNameInput");
    var labelLastName = document.getElementById("labelLastName");

    var companyName = document.getElementById("companyNameInput");
    var labelCompanyName = document.getElementById("labelCompanyName");

    var address = document.getElementById("addressInput");
    var labelAdress = document.getElementById("labelAdress");

    var town = document.getElementById("townInput");
    var labelTown = document.getElementById("labelTown");

    var zip = document.getElementById("zipInput");
    var labelZip = document.getElementById("labelZip");

    var email = document.getElementById("emailInput");
    var labelEmail = document.getElementById("labelEmail");

    var phone = document.getElementById("phoneInput");
    var labelPhone = document.getElementById("labelPhone");

    var registration = document.getElementById("registrationInput");

    var error = false;
    if(fullName.value == undefined || fullName.value==""){
        labelFullName.style.color = 'red';
        error = true;
    }
    else{
        labelFullName.style.color = 'black';
    }
    if(lastName.value == undefined || lastName.value==""){
        labelLastName.style.color = 'red';
        error = true;
    }
    else{
        labelLastName.style.color = 'black';
    }
    if(address.value == undefined || address.value==""){
        labelAdress.style.color = 'red';
        error = true;
    }
    else{
        labelAdress.style.color = 'black';
    }
    if(zip.value == undefined || zip.value==""){
        labelZip.style.color = 'red';
        error = true;
    }
    else{
        labelZip.style.color = 'black';
    }
    if(email.value == undefined || email.value==""){
        labelEmail.style.color = 'red';
        error = true;
    }
    else{
        labelEmail.style.color = 'black';
    }
    if(phone.value == undefined || phone.value==""){
        labelPhone.style.color = 'red';
        error = true;
    }
    else{
        labelPhone.style.color = 'black';
    }

/*

    var  data = {
        "fullName": fullName.value,
        "lastName": lastName.value,
        "companyName": companyName.value,
        "address": address.value,
        "town": town.value,
        "zip": zip.value,
        "email": email.value,
        "phone": phone.value,
        "regFlag": registration.checked
    };
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlObjects,
        dataType:'json',
        data:  $.toJSON(data),
        async: false
    }).done(function( count ) {

    });
    */
});