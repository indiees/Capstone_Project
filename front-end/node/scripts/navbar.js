if (localStorage.getItem("email")!=null){
    $("#login").hide()
}
else{
    $("#logout").hide()
}
if (localStorage.getItem("account_type")>1){
    //If they are an admin
    $("#admin").show()
}
else{
    $("#admin").hide()
}
$(function() {
    $('#logoutbtn').click(function() {
        localStorage.clear("email")
        localStorage.clear("password")
        localStorage.clear("account_type")
        window.location.replace("/cars");
    })})

$(document).on("click", "#profile", function(data){
    console.log("clicked profile button");
    $("#email")[0].setAttribute("disabled", "disabled")
    $.ajax({
        type: 'POST',
        data: {
            email: localStorage.getItem("email"),
            password: localStorage.getItem("password"),
        },
        url: baseURL + 'checkLogin',
        complete: profileCallback
    });
})
function profileCallback(data){
    data=data.responseJSON.payload
    $("#email")[0].value = data.email
    $("#user_id")[0].value = data.user_id
    $("#first_name")[0].value = data.first_name
    $("#last_name")[0].value = data.last_name
    console.log(data)
}

$(document).on("click", "#save", function(data){
    $.ajax({
        type: 'POST',
        data: {
            loginEmail: localStorage.getItem("email"),
            loginPassword: localStorage.getItem("password"),
            user_id:        $("#user_id")[0].value,
            first_name:     $("#first_name")[0].value,
            last_name:      $("#last_name")[0].value
        },
        url: baseURL + 'user/update',
        complete: updateCallback
    });
})

function updateCallback(data){
    $("#close")[0].click()
    console.log(data.responseJSON)
}