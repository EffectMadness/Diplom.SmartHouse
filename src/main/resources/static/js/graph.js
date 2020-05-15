$(document).ready(function()  {
    let e = document.getElementById("timeinterval");
    let hours = e.options[e.selectedIndex].value;
    showChart(hours);
});

let chart;
let ctx = document.getElementById('myChart').getContext('2d');

function showChart(hours) {
    let url = '/graph/graphData?hours=' + hours;

    $.ajax({
        type: 'GET',
        dataType: 'text',
        url: url,
        success: function (response) {
            renderChart(JSON.parse(response))
        },
        error: function (error) {
            console.log(JSON.parse(error.responseText).message);
        }
    });
}

function renderChart(response) {
    if (chart != null) {
        chart.destroy();
    }

    chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: response.labels,
            datasets: response.data,
        },
        options: {
            // scales: {
            //     xAxes: [{
            //         time: {
            //             unit: 'date'
            //         },
            //         gridLines: {
            //             display: false
            //         },
            //         ticks: {
            //             maxTicksLimit: 20
            //         }
            //     }],
            //     yAxes: [{
            //         ticks: {
            //             maxTicksLimit: 10
            //         },
            //         gridLines: {
            //             color: "rgba(0, 0, 0, .125)",
            //         }
            //     }],
            // },
            // legend: {
            //     display: false
            // }
        }
    });
}
