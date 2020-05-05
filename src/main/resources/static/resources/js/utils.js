function pageLoaded(pageName) {
    console.log("Loaded fragment page: " + pageName);
}

function loadWeighingsChart(pageName, weighings) {
    if (pageName !== "allWeighings") {
        return;
    }
    console.log("Showing weighings chart");

    let dataPoints = [];

    for (let i = 0; i < weighings.length; i++) {
        let weighing = weighings[i];
        dataPoints.push({
            x: new Date(
                parseInt(weighing.date.split("-")[0]),
                parseInt(weighing.date.split("-")[1]) - 1,
                parseInt(weighing.date.split("-")[2])
            ),
            y: parseFloat(weighing.weight)
        });
    }

    var chart = new CanvasJS.Chart("weighingsChartContainer", {
        animationEnabled: true,
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        axisX: {
            interval: 1,
            valueFormatString: "MMM"
        },
        axisY: {
            title: "Weight [kg]"
        },
        toolTip: {
            content: "Date: {x}<br /><strong>Weight: {y} kg</strong>"
        },
        data: [{
            type: "line",
            yValueFormatString: "##0.00",
            dataPoints: dataPoints
        }]
    });

    chart.render();
}