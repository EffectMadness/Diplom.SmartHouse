function updateData(){
    let e = document.getElementById("timeinterval");
    let hours = e.options[e.selectedIndex].value;
    console.log("cur val: " + hours);
    $('#MaxTemperatureTableContainer').jtable('destroy');
    $('#MinTemperatureTableContainer').jtable('destroy');
    showMaxTemp(hours);
    showMinTemp(hours);
}