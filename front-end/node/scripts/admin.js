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
    $(".content-table").hide();
    $.ajax({
        type: 'GET',
        url: baseURL + 'car/search',
        complete: carCallback
    });
});

function carCallback(data){
    
    data=data.responseJSON
    rawCars = data.payload

    //First we make header
    thead = document.createElement("thead");
    thead.id="carsHeader"
    theadr = document.createElement("tr");
    thead.appendChild(theadr);
    for (var field in rawCars[0]){
        theadh = document.createElement("th");
        theadh.innerHTML = field;
        theadr.appendChild(theadh);
    }
    oldHeader = $("#carsHeader")[0];
    oldHeader.parentNode.replaceChild(thead, oldHeader)

    //Then we fill in table
    tbody = document.createElement("tbody");
    tbody.id = "carsBody"
    for (var i in rawCars){
        tr = document.createElement("tr");
        for (var j in rawCars[i]){
            td = document.createElement("td");
            td.innerHTML = rawCars[i][j];
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    oldBody = $("#carsBody")[0];
    oldBody.parentNode.replaceChild(tbody, oldBody)
    $("#carLoading").hide();
    $(".content-table").show();
}

$(document).on("click", "#userBtn", function(data){
    console.log("refreshing user page")
    $("#userLoading").show();
    $(".content-table").hide();
    formData = {email:      $("#loginEmail").val(), 
                password:   $("#loginPassword").val()}
    $.ajax({
        type: 'GET',
        url: baseURL + 'car/search',
        data: formData,
        complete: userCallback
    });
});
function userCallback(data){
    data=data.responseJSON
    rawUsers = data.payload

    //First we make header
    thead = document.createElement("thead");
    thead.id="usersHeader"
    theadr = document.createElement("tr");
    thead.appendChild(theadr);
    for (var field in rawUsers[0]){
        theadh = document.createElement("th");
        theadh.innerHTML = field;
        theadr.appendChild(theadh);
    }
    oldHeader = $("#usersHeader")[0];
    oldHeader.parentNode.replaceChild(thead, oldHeader)

    //Then we fill in table
    tbody = document.createElement("tbody");
    tbody.id = "usersBody"
    for (var i in rawUsers){
        tr = document.createElement("tr");
        for (var j in rawUsers[i]){
            td = document.createElement("td");
            td.innerHTML = rawUsers[i][j];
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
    oldBody = $("#usersBody")[0];
    oldBody.parentNode.replaceChild(tbody, oldBody)
    $("#userLoading").hide();
    $(".content-table").show();
}

$(document).on("click", "#bayBtn", function(data){
    console.log("refreshing bay page")
    $("#bayLoading").show();
    $(".content-table").hide();
    $.ajax({
        type: 'GET',
        url: baseURL + 'bay/search',
        complete: bayCallback
    });
});

function bayCallback(data){
    data=data.responseJSON
    rawBays = data.payload

    //First we make header
    thead = document.createElement("thead");
    thead.id="baysHeader"
    theadr = document.createElement("tr");
    thead.appendChild(theadr);
    for (var field in rawBays[0]){
        theadh = document.createElement("th");
        theadh.innerHTML = field;
        theadr.appendChild(theadh);
    }
    theadh = document.createElement("th");
    theadr.appendChild(theadh);
    oldHeader = $("#baysHeader")[0];
    oldHeader.parentNode.replaceChild(thead, oldHeader)

    //Then we fill in table
    tbody = document.createElement("tbody");
    tbody.id = "baysBody"
    for (var i in rawBays){
        tr = document.createElement("tr");
        for (var j in rawBays[i]){
            td = document.createElement("td");
            td.innerHTML = rawBays[i][j];
            tr.appendChild(td);
        }
        td = document.createElement("td");
        editBtn = document.createElement("button")
        editBtn.innerHTML="Edit";
        editBtn.className="editBayBtn";
        editBtn.setAttribute("tg",rawBays[i].bay_id)
        td.appendChild(editBtn);
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
    oldBody = $("#baysBody")[0];
    oldBody.parentNode.replaceChild(tbody, oldBody)
    $("#bayLoading").hide();
    $(".content-table").show();
}

$(document).on("click", ".editBayBtn", function(data){
    console.log("Loading bay Modal")
    id = data.target.getAttribute("tg")
    console.log("ID is: " + id)
    showModal(id)
});

$(document).on("click", "#addBay", function(data){
    console.log("Loading bay Modal")
    showModal(0)
});

function showModal(id){
    
    if (id==0){
        $("#heading")[0].innerHTML = "New Bay"
        $("#submitBay")[0].innerHTML = "Create new Bay"
        $("#bay_id")[0].value = "";
        $("#bay_id")[0].placeholder = "Automatic";
        $("#location")[0].value=""
        $("#max_capacity")[0].value=""
        $("#bayModal")[0].style.display="block";
        $("#submitBay")[0].setAttribute("tg","create");
    }
    else{
        $("#heading")[0].innerHTML = "Editing Bay " + id
        $("#submitBay")[0].innerHTML = "Update Existing Bay"
        $("#submitBay")[0].setAttribute("tg","update");
        $("#bay_id")[0].value = id;
        $.ajax({
            type: 'GET',
            url: baseURL + 'bay/search',
            complete: singleBayCallback
        });
    }
}

function singleBayCallback(data){
    id = $("#bay_id")[0].value
    data=data.responseJSON.payload
    index=0
    for (var i in data){
        if (data[i].bay_id == id){
            index = i
        }
    }
    data=data[index]
    $("#location")[0].value=data.location
    $("#max_capacity")[0].value=data.max_capacity
    $("#bayModal")[0].style.display="block";
}

$(document).on("click", "#submitBay", function(data){
    console.log("Submitting bay")
    opp = data.target.getAttribute("tg")
    console.log("Operation: " + opp)
    formData = {
        email:      localStorage.getItem("email"), 
        password:   localStorage.getItem("password"),
        bay_id:     $("#bay_id")[0].value,
        location:   $("#location")[0].value,
        max_capacity:$("#max_capacity")[0].value
        
    }
    console.log(formData)

    
});

$(document).on("click", ".close", function(data){
    console.log("Loading bay Modal")
    hideModals();
});
window.onclick = function(event) {
    modals = $(".modals");
    for (var i=0;i<modals.length;i++){
        if (event.target==modals[i]){
            hideModals();
        }
    }
  }
function hideModals(){
    modals = $(".modals");
    for (var i=0;i<modals.length;i++){
        modals[i].style.display="none"
    }
}