formData = {
    email:      localStorage.getItem("email"), 
    password:   localStorage.getItem("password"),
}
$.ajax({
type: 'POST',
url: baseURL + 'user/getBookings',
data: formData,
complete: getBookings
});
function getBookings(data){
    $("#loading").hide()
    bookings = data.responseJSON.payload
    console.log(bookings)
    for (booking in bookings){
        row = document.createElement("tr")

        column1 = document.createElement("th")
        column1.innerHTML=bookings[booking].booking_id
        column1.setAttribute("scope","row")
        row.appendChild(column1)

        column1 = document.createElement("td")
        column1.innerHTML=bookings[booking].car_id
        row.appendChild(column1)

        column1 = document.createElement("td")
        column1.innerHTML=bookings[booking].start_bay_id
        row.appendChild(column1)

        column1 = document.createElement("td")
        column1.innerHTML=bookings[booking].end_bay_id
        row.appendChild(column1)

        date = new Date(bookings[booking].date).toLocaleString();
        dateString = date.toLocaleString();

        column1 = document.createElement("td")
        column1.innerHTML=dateString
        row.appendChild(column1)

        endDate = new Date(bookings[booking].date + bookings[booking].duration).toLocaleString();
        dateString = date.toLocaleString();

        column1 = document.createElement("td")
        if (bookings[booking].duration==0){
            column1.innerHTML = "Not completed"
        }
        else{
            column1.innerHTML=endDate
        }
        row.appendChild(column1)

        column1 = document.createElement("td")
        if (bookings[booking].duration==0){
            column1.innerHTML = "Not completed"
        }
        else{
            column1.innerHTML=bookings[booking].duration
        }
        row.appendChild(column1)

        column1 = document.createElement("td")
        column1.innerHTML="$" + bookings[booking].rate.toFixed(2)
        row.appendChild(column1)

        column1 = document.createElement("td")
        button1 = document.createElement("button")
        button1.innerHTML = "Delete Booking"
        button1.className="deletebtn"
        button1.setAttribute("onclick","removeBooking(" + bookings[booking].booking_id + ")")
        column1.appendChild(button1)
        row.appendChild(column1)

        $("#bookingsTable")[0].appendChild(row)
    }
    


    
}