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