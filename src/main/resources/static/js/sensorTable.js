$(document).ready(function () {
    $('#SensorTableContainer').jtable({
        title: 'Датчики',
        actions: {
            listAction: '/sensor',
            createAction: '/sensor/create',
            updateAction: '/sensor/update',
            deleteAction: '/sensor/delete'
        },
        fields: {
            sensorId: {
                key: true,
                list: false
            },
            sensorName: {
                title: 'Кімната',
                width: '40%'
            },
            delta: {
                title: 'Похибка',
                width: '20%'
            },
            sensorUid: {
                title: 'Адреса',
                width: '30%'
            }
        }
    }).jtable('load');
});