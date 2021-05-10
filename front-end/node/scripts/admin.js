function show(div){
    $(".sectionDiv").hide();
    $("#" + div).show();
};

$(document).on("click", ".adminNav button", function(data){
    show(data.target.getAttribute("tg"))
});

$(document).on("click", "#carBtn", function(data){
    console.log("refreshing cars page")
    $("#carLoading").show();/*
    formData = {email:      $("#loginEmail").val(), 
                password:   $("#loginPassword").val()}
    $.ajax({
        type: 'POST',
        url: baseURL + 'checkLogin',
        data: formData,
        complete: loginCallback
    });*/
});

$(document).on("click", "#userBtn", function(data){
    console.log("refreshing user page")
    $("#userLoading").show();/*
    formData = {email:      $("#loginEmail").val(), 
                password:   $("#loginPassword").val()}
    $.ajax({
        type: 'POST',
        url: baseURL + 'checkLogin',
        data: formData,
        complete: loginCallback
    });*/
});


$(document).on("click", "#bayBtn", function(data){
    console.log("refreshing bay page")
    $("#bayLoading").show();/*
    formData = {email:      $("#loginEmail").val(), 
                password:   $("#loginPassword").val()}
    $.ajax({
        type: 'POST',
        url: baseURL + 'checkLogin',
        data: formData,
        complete: loginCallback
    });*/
});

