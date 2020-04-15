$(document).ready(function () {
    $('#SpecDayContainer').jtable({
        title: 'Налагодження періоду (відпустка)',
        paging: true, //Enable paging
        actions: {
            listAction: '/specday-setup',
             createAction: '/specday-setup/create',
             updateAction: '/specday-setup/update',
             deleteAction: '/specday-setup/delete'
        },
        fields: {
            id: {
                key: true,
                list: false
            },
            dateFrom: {
                title: 'Дата з',
                width: '20%'
            },
            dateTo: {
                title: 'Дата по',
                width: '20%'
            },
            timeZoneName: {
                options: '/time-zone',
                title: 'Період дії',
                width: '30%'
            },
            temperature: {
                title: 'Температура',
                width: '30%',
                create: false,
                edit: false
            }
        }
    }).jtable('load');
});