var items = document.getElementsByClassName("bulb");

for(let i = 0; i<items.length; i++){
  if(items[i].innerHTML === "ERROR"){
    items[i].style.backgroundColor = "red";
  }
  else if(items[i].innerHTML === "PROCESANDO"){
    items[i].style.backgroundColor = "yellow";
  }
  else if(items[i].innerHTML === "OK"){
      items[i].style.backgroundColor = "green";
    }
}