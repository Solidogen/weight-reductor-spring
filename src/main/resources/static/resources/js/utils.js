function pageLoaded(pageName) {
    if (pageName === "allWeighings") {
        loadWeighingChart();
    }
}

function loadWeighingChart() {
    var chart = new CanvasJS.Chart("weighingsChartContainer", {
        theme: "light1", // "light2", "dark1", "dark2"
        animationEnabled: false, // change to true
/*        title: {
            text: "Basic Column Chart"
        },*/
        data: [
            {
                type: "column",
                dataPoints: [
                    {label: "apple", y: 10},
                    {label: "orange", y: 15},
                    {label: "banana", y: 25},
                    {label: "mango", y: 30},
                    {label: "grape", y: 28}
                ]
            }
        ]
    });
    chart.render();
}