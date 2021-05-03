if (localStorage.getItem("email")!=null){
    $("#login").hide()
}
else{
    $("#logout").hide()
}
$(function() {
    $('#logoutbtn').click(function() {
        localStorage.clear("email")
        localStorage.clear("password")
        window.location.replace("/cars");
    })})