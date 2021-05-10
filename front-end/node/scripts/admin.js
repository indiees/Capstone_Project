function show(div){
    $(".sectionDiv").hide();
    $("#" + div).show();
};

$(document).on("click", ".adminNav button", function(data){
    show(data.target.getAttribute("tg"))
});

const baseURL = "http://localhost:7000/api/";

$(document).on("click", "#carBtn", function(data){
    console.log("refreshing cars page")
    $("#carLoading").show();
    $.ajax({
        type: 'GET',
        url: baseURL + 'car/search',
        complete: carCallback
    });
});

function carCallback(){
    $("#carLoading").hide();
}

$(document).on("click", "#userBtn", function(data){
    console.log("refreshing user page")
    $("#userLoading").show();
    formData = {email:      $("#loginEmail").val(), 
                password:   $("#loginPassword").val()}
    $.ajax({
        type: 'GET',
        url: baseURL + 'car/search',
        data: formData,
        complete: userCallback
    });
});
function userCallback(){
    $("#userLoading").hide();
}

$(document).on("click", "#bayBtn", function(data){
    console.log("refreshing bay page")
    $("#bayLoading").show();
    $.ajax({
        type: 'GET',
        url: baseURL + 'bay/search',
        complete: bayCallback
    });
});

function bayCallback(){
    $("#bayLoading").hide();
}
