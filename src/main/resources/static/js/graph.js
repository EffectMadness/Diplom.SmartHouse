var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
        labels: ['10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00'],
        datasets: [{
            label: 'Sensor1',
            backgroundColor: 'rgb(0, 0, 100)',
            borderColor: 'rgb(0, 0, 0)',
            fill: false,
            data: [16.5, 18, 12, 20, 24, 15, 18, 10],
        },{
            label: 'Sensor2',
            backgroundColor: 'rgb(100, 300, 100)',
            borderColor: 'rgb(0, 0, 0)',
            fill: false,
            data: [11, 20, 30, 14, 18, 19, 7, 20],
            }
        ]
    },

    // Configuration options go here
    options: {}
});