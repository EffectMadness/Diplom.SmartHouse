$(document).ready(function () {
    $('#LastHourTemperatureTableContainer').jtable({
        title: 'Мінімальна Температура',
        fields: {
            sensorId: {
                title: 'Сенсор',
                key: true,
                list: false
            },
            dataTime:{
                title: 'Дата/Час',
                create: false,
                edit: false
            }
        }
    }).jtable('load');
});