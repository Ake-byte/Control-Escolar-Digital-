document.querySelectorAll('.doc_status').forEach(i => {
  i.textContent.indexOf("DOCUMENTO VALIDADO") !== -1 ?
    i.classList.add('green') :
    i.innerText.indexOf("DOCUMENTO EN PROCESO DE VALIDACION") !== -1 ?
    i.classList.add('yellow') :
    i.innerText.indexOf("DOCUMENTO NO VALIDADO") !== -1 ?
    i.classList.add('red') :
    null;
});