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
        activateFilter();
    })
})

function activateFilter(){
    $("#loading").show()
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
}

function searchCompleteCallback(data){
    console.log("Search complete")
    console.log(data.responseJSON.payload)
    newCars = data.responseJSON.payload
    //make an element to hold all of the new cars
    cars = document.createElement("div")
    cars.className="row"
    cars.id="cars"
    for (newCar in newCars){
        console.log(newCar)
        //This is the element that is going to be a new car card eventually
        card = document.createElement("div")
        card.className = "col-10 mx-auto my-3 col-md-6 col-lg-4"

        //The card consists of 3 main elements (img, body, footer)
        innerCard = document.createElement("div")
        innerCard.className = "card car-card"
        cardBody = document.createElement("div")
        cardBody.className = "card-body"
        cardFooter = document.createElement("div")
        cardFooter.className = "card-footer text-capitalized d-flex justify-content-between"

        //Setting up our image
        img = document.createElement("img")
        img.src="/images/cars/" + newCars[newCar].car_id + ".jpg"

        //Setting up our body
        innerCardBody = document.createElement("div")
        innerCardBody.className = "car-info d-flex justify-content-between"

        //The card body consists of the left text, the middle text and the right button, as well as the hidden modal
        innerBodyLeft = document.createElement("div")
        innerBodyLeft.className = "card-text text-uppercase"

        //setup the make text, and add it to innerbodyleft
        carMakeText = document.createElement("h6")
        carMakeText.className = "font-weight-bold"
        carMakeText.innerHTML = newCars[newCar].make
        innerBodyLeft.appendChild(carMakeText)

        //then we make innderBodyMid
        innerBodyMid = document.createElement("h5")
        innerBodyMid.className = "car-value align-self-center py-2 px-3"
        innerBodyMid.innerHTML = "$" + newCars[newCar].cost.toFixed(2) + "/hour"

        //Then we make the button to show up on the Right
        innerBodyRight = document.createElement("button")
        innerBodyRight.className="btn btn-success"
        innerBodyRight.type = "button"
        innerBodyRight.setAttribute("data-bs-toggle","modal")
        innerBodyRight.setAttribute("data-bs-target","#exampleModal" + newCars[newCar].car_id)
        innerBodyRight.innerHTML="Rent"

        //Then we have to setup our modal
        modal = document.createElement("div")
        modal.className = "modal fade"
        modal.setAttribute("id", "exampleModal" + newCars[newCar].car_id)
        modal.setAttribute("tab-index", "-1")
        modal.setAttribute("aria-labelledby", "exampleModalLabel")
        modal.setAttribute("aria-hidden", "true")

        //the modal has an inner element
        innerModal = document.createElement("div")
        innerModal.className="modal-dialog"
        modal.appendChild(innerModal)
        //Which has its own inner element
        innerInnerModal = document.createElement("div")
        innerInnerModal.className="modal-content"
        innerModal.appendChild(innerInnerModal)
        //Which has a header/footer/body
        modalHeader = document.createElement("div")
        modalBody = document.createElement("div")
        modalFooter = document.createElement("div")
        modalHeader.className = "modal-header"
        modalBody.className = "modal-body"
        modalFooter.className = "modal-footer"
        innerInnerModal.appendChild(modalHeader)
        innerInnerModal.appendChild(modalBody)
        innerInnerModal.appendChild(modalFooter)
        //The header contains some text
        modalTitle = document.createElement("h5")
        modalTitle.className = "modal-title"
        modalTitle.setAttribute("id","exampleModalLabel")
        modalTitle.innerHTML = "Rental Agreement for " + newCars[newCar].make
        modalHeader.appendChild(modalTitle)
        //And an exit button
        modalQuitButton = document.createElement("button")
        modalQuitButton.className="btn-close"
        modalQuitButton.setAttribute("type","button")
        modalQuitButton.setAttribute("data-bs-dismiss", "modal")
        modalQuitButton.setAttribute("aria-label","Close")
        modalHeader.appendChild(modalQuitButton)

        //Next we do the modal body
        modalBodyText = document.createElement("p")
        modalBodyText.innerHTML = "Please agree to our terms and conditions"
        modalBody.appendChild(modalBodyText)

        //Next we do our footer
        //Which consissts of a close button
        modalCloseButton = document.createElement("button")
        modalCloseButton.className = "btn btn-secondary"
        modalCloseButton.setAttribute("type","button")
        modalCloseButton.setAttribute("data-bs-dismiss","modal")
        modalCloseButton.innerHTML="Close"
        modalFooter.appendChild(modalCloseButton)
        //As well as a confirm booking button
        modalConfirmButton = document.createElement("button")
        modalConfirmButton.className = " btn btn-success"
        modalConfirmButton.setAttribute("type","button")
        modalConfirmButton.setAttribute("onClick","makeBooking( " + newCars[newCar].car_id + ")")
        modalConfirmButton.innerHTML="Confirm Booking"
        modalFooter.appendChild(modalConfirmButton)

        //Then we have to setup our footer
        leftFooter = document.createElement("p")
        leftFooter.innerHTML = newCars[newCar].year
        midFooter = document.createElement("p") //for formatting reasons there is an empty p
        rightFooter = document.createElement("p")
        rightFooter.innerHTML = newCars[newCar].color
        cardFooter.appendChild(leftFooter)
        cardFooter.appendChild(midFooter)
        cardFooter.appendChild(rightFooter)
        
        innerCardBody.appendChild(innerBodyLeft)
        innerCardBody.appendChild(innerBodyMid)
        innerCardBody.appendChild(innerBodyRight)
        innerCardBody.appendChild(modal)
        cardBody.appendChild(innerCardBody)
        innerCard.appendChild(img)
        innerCard.appendChild(cardBody)
        innerCard.appendChild(cardFooter)
        card.appendChild(innerCard)
        cars.appendChild(card)
    }

    
    oldCars = $("#cars")[0]
    $("#loading").hide()
    oldCars.parentNode.replaceChild(cars,oldCars)
}

