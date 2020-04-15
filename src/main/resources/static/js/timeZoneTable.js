$(document).ready(function () {
    $('#TimeZoneContainer').jtable({
        title: 'Налагодження часових періодів',
        paging: true, //Enable paging
        sorting: true,
        defaultSorting: 'day ASC',
        actions: {
            listAction: '/day-setup',
            createAction: '/day-setup/create',
            updateAction: '/day-setup/update',
            deleteAction: '/day-setup/delete'
        },
        fields: {
            id: {
                key: true,
                list: false
            },
            day: {
                title: 'День',
                width: '40%',
                options: {
                    'Sunday': 'Sunday',
                    'Monday': 'Monday',
                    'Tuesday': 'Tuesday',
                    'Wednesday': 'Wednesday',
                    'Thursday': 'Thursday',
                    'Friday': 'Friday',
                    'Saturday': 'Saturday'
                },
                sort: false
            },
            period: {
                title: 'Проміжок часу',
                width: '20%',
                create: false,
                edit: false,
                sorting: false
            },
            timeZoneName: {
                title: 'TimeZone',
                list: false,
                options: '/time-zone',
                create: true,
                edit: true,
                sorting: false
            },
            temperature: {
                title: 'Температура',
                width: '30%',
                create: false,
                edit: false,
                sorting: false
            }
        }
    }).jtable('load');
});