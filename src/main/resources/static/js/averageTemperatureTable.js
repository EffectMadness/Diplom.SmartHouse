$(document).ready(function () {
    $('#AverageTemperatureTableContainer').jtable({
        title: 'Середня Температура',
        action: {
            listAction: '/11111'
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
            temperature: {
                title: 'Температура',
                width: '30%',
                create: false,
                edit: false
            },
        }
    }).jtable('load');
});