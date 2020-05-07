$(document).ready(function () {
    $('#MaxTemperatureTableContainer').jtable({
        title: 'Максимальна Температура',
        action: {
            listAction: '/statistic'
        },
        fields: {
            sensorId: {
                key: true,
                list: false
            },
            sensorName: {
                title: 'Сенсор',
                width: '30%',
                create: false,
                edit: false
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