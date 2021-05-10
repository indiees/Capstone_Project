function show(div){
    $(".sectionDiv").hide();
    $("#" + div).show();
};


$(document).on("click", ".adminNav button", function(data){
    show(data.target.getAttribute("tg"))
});