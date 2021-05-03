baseURL = "http://localhost:7000/api/"
function makeBooking(car_id){
    console.log("Trying to make booking for car: " + car_id + " with user " + localStorage.getItem("email"))
    formData = {
                    email:      localStorage.getItem("email"), 
                    password:   localStorage.getItem("password"),
                    car_id:     car_id
                }
    $.ajax({
        type: 'POST',
        url: baseURL + 'booking/create',
        data: formData,
        complete: makeBookingCallback
    });
}

function makeBookingCallback(data){
    console.log(data);
    window.location.replace("/bookings");
}

$(function() {
    $('#search').click(function() {
        //Get all of the fields and store in an easy to access dict
        data = {
            cost_min:   $("#min-cost").val(),
            cost_max:   $("#max-cost").val(),
            year_min:   $("#min-year").val(),
            year_max:   $("#max-year").val(),
            make:       $("#make").val(),
            color:      $("#color").val(),
            location:   $("#location").val()
        }
        //Format the dict into a string ready to be a param
        query=""
        for (key in data){
            if (data[key]!=""){
                query+=key+"="+data[key]+"&"
            }
        }
        query=query.substring(0,query.length-1)
        console.log(query)
        //send out the request
        $.ajax({
            type: 'GET',
            url: baseURL + 'car/search?' + query,
            complete: searchCompleteCallback
        });
    })
})

function searchCompleteCallback(data){
    console.log("Search complete")
    console.log(data.responseJSON.payload)
}