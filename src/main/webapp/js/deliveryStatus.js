updateDetails();
setInterval(updateDetails, 500);

function updateDetails() {
  fetch(
    "get-specific-delivery?&orderID=" + document.querySelector("#orderID").value
  )
    .then((response) => response.json())
    .then((data) => {
      var orderStatus = 0;
      switch (data.status) {
        case "Order Received":
          orderStatus = 0;
          break;
        case "Preparing":
          orderStatus = 1;
          break;
        case "Prepared":
          orderStatus = 2;
          break;
        case "Delivering":
          orderStatus = 3;
          break;
        case "Delivered":
          orderStatus = 4;
          break;
        default:
          orderStatus = -1;
      }

      if (orderStatus < 0) {
        document.querySelector("#order-received-img").classList.add("hidden");
      } else {
        document
          .querySelector("#order-received-img")
          .classList.remove("hidden");
      }

      if (orderStatus < 2) {
        document.querySelector("#prepared-img").classList.add("hidden");
      } else {
        document.querySelector("#prepared-img").classList.remove("hidden");
      }

      if (orderStatus < 4) {
        document.querySelector("#delivered-img").classList.add("hidden");
      } else {
        document.querySelector("#delivered-img").classList.remove("hidden");
      }

      document.querySelector("#order-status").innerHTML = data.status;
      document.querySelector("#order-type").innerHTML = data.type;
      document.querySelector("#order-street").innerHTML = data.street;
      document.querySelector("#order-suburb").innerHTML = data.suburb;
      document.querySelector("#order-state").innerHTML = data.state;
      document.querySelector("#order-postal").innerHTML = data.postal;
      document.querySelector("#order-instructions").innerHTML =
        data.instructions;
    });
}
