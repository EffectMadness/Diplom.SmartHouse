$(document).ready(function () {
    $('#LastHourTemperatureTableContainer').jtable({
        title: 'За останню годину',
        action: {
            listAction: '/statistic/1111'
        },
        fields: {
            sensorId: {
                title: 'Сенсор',
                key: true,
                list: false
            },
            sensorName: {
                title: 'Сенсор',
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