$(document).ready(function()  {
    let e = document.getElementById("timeinterval");
    let hours = e.options[e.selectedIndex].value;
    showMinTemp(hours);
});

function showMinTemp(hours) {
    console.log("showMinTemp hours: " + hours);
    $('#MinTemperatureTableContainer').jtable({
        title: 'Мінімальна Температура:',
        actions: {
            listAction: '/statistic/min'
        },
        fields: {
            sensorId: {
                key: true,
                list: false
            },
            sensorName: {
                title: 'Сенсор',
                width: '10%',
                create: false,
                edit: false
            },
            temperature: {
                title: 'Температура',
                width: '10%',
                create: false,
                edit: false
            },
            dataTime:{
                title: 'Дата/Час',
                create: false,
                edit: false
            }
        }
    }).jtable('load');
}