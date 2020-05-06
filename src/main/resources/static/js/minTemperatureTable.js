$(document).ready(function () {
    $('#MinTemperatureTableContainer').jtable({
        title: 'Мінімальна Температура',
        fields: {
            sensorId: {
                title: 'Сенсор',
                key: true,
                list: false
            },
            temperature: {
                title: 'Температура',
                width: '30%',
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
});