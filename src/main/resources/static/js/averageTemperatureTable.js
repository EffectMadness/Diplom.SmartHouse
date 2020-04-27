$(document).ready(function () {
    $('#AverageTemperatureTableContainer').jtable({
        title: 'Температура',
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
        }
    }).jtable('load');
});