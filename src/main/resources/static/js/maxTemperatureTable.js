$(document).ready(function()  {
    let e = document.getElementById("timeinterval");
    let hours = e.options[e.selectedIndex].value;
    showMaxTemp(hours);
});

function showMaxTemp(hours) {
    console.log("showMaxTemp hours: " + hours);
    $('#MaxTemperatureTableContainer').jtable({
        title: 'Максимальна Температура:',
        actions: {
            listAction: '/statistic/max?hours=' + hours
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