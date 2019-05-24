var myCanvas;
var myLegend;


function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function createCanvas() {


    let newCanvas = document.createElement("CANVAS");
    let drawingArea = document.getElementById("drawingArea");
    var ctx = newCanvas.getContext("2d");
    newCanvas.width = 300;
    newCanvas.height = 300;
    // set id as myLegend, much easier tho.
    var newDiv = document.createElement("div");
    drawingArea.appendChild(newCanvas);
    drawingArea.appendChild(newDiv);
    this.myCanvas = newCanvas;
    this.myLegend = newDiv;
}

function resetDrawingArea() {
    let drawingArea = document.getElementById("drawingArea");
    drawingArea.innerHTML = "";
}

function drawLine(ctx, startX, startY, endX, endY) {
    ctx.beginPath();
    ctx.moveTo(startX, startY);
    ctx.lineTo(endX, endY);
    ctx.stroke();
}


function drawArc(ctx, centerX, centerY, radius, startAngle, endAngle) {
    ctx.beginPath();
    ctx.arc(centerX, centerY, radius, startAngle, endAngle);
    ctx.stroke();
}

function drawPieSlice(ctx, centerX, centerY, radius, startAngle, endAngle, color) {
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.arc(centerX, centerY, radius, startAngle, endAngle);
    ctx.closePath();
    ctx.fill();
}

var Piechart = function (options) {

}

function drawPichart(data, category, doughnutHoleSize) {
    createCanvas()
    this.canvas = myCanvas;
    this.ctx = this.canvas.getContext("2d");
    this.data = data.slice(0);
    this.category = category
    this.colors = []
    this.data.forEach(element => {
        this.colors.push(getRandomColor());
    });


    // this.draw = function () {
    var total_value = 0;
    var color_index = 0;
    for (var val in this.data) {
        total_value += this.data[val];
    }

    var start_angle = 0;
    for (val in this.data) {
        val = this.data[val]
        var slice_angle = 2 * Math.PI * val / total_value;

        drawPieSlice(
            this.ctx,
            this.canvas.width / 2,
            this.canvas.height / 2,
            Math.min(this.canvas.width / 2, this.canvas.height / 2),
            start_angle,
            start_angle + slice_angle,
            this.colors[color_index % this.colors.length]
        );

        start_angle += slice_angle;
        color_index++;
    }

    //drawing a white circle over the chart
    //to create the doughnut chart
    if (doughnutHoleSize) {
        drawPieSlice(
            this.ctx,
            this.canvas.width / 2,
            this.canvas.height / 2,
            doughnutHoleSize * Math.min(this.canvas.width / 2, this.canvas.height / 2),
            0,
            2 * Math.PI,
            "#ffffff"
        );
    }
    start_angle = 0;
    for (vale in this.data) {
        val = this.data[vale]
        slice_angle = 2 * Math.PI * val / total_value;
        var pieRadius = Math.min(this.canvas.width / 2, this.canvas.height / 2);
        var labelX = this.canvas.width / 2 + (pieRadius / 2) * Math.cos(start_angle + slice_angle / 2);
        var labelY = this.canvas.height / 2 + (pieRadius / 2) * Math.sin(start_angle + slice_angle / 2);

        if (doughnutHoleSize) {
            var offset = (pieRadius * doughnutHoleSize) / 2;
            labelX = this.canvas.width / 2 + (offset + pieRadius / 2) * Math.cos(start_angle + slice_angle / 2);
            labelY = this.canvas.height / 2 + (offset + pieRadius / 2) * Math.sin(start_angle + slice_angle / 2);
        }

        var labelText = Math.round(100 * val / total_value);
        this.ctx.fillStyle = "white";
        this.ctx.font = "bold 20px Arial";
        this.ctx.fillText(labelText + "%", labelX, labelY);
        start_angle += slice_angle;
    }
    // if (this.options.legend) {
    color_index = 0;
    var legendHTML = "";
    for (categ in this.category) {
        legendHTML += "<div><span style='display:inline-block;width:20px;background-color:" + this.colors[color_index++] + ";'>&nbsp;</span> " + this.category[categ] + "</div>";
    }
    myLegend.innerHTML = legendHTML;
    // }

    // }

}



// data will be two array, instead of one object, and colours have to be created from the array length as internal array
// var Piechart = new Piechart(
//     {
//         data: [10, 90, 150, 230, 500, 10],
//         category: [10, 90, 150, 230, 20, 10],
//         doughnutHoleSize: 0.5
//     }
// );
// drawPichart([10, 90, 150, 230, 500, 10],
//     [10, 90, 150, 230, 20, 10],
//     0.5);
// drawPichart.draw();
// Piechart.draw();
// var Piechart = new Piechart(
//     {
//         data: [10, 90, 150, 230, 500, 10],
//         category: [10, 90, 150, 230, 20, 10],
//         doughnutHoleSize: 0.5
//     }
// );
// Piechart.draw();





// source : https://stackoverflow.com/questions/1484506/random-color-generator 
// source : https://code.tutsplus.com/tutorials/how-to-draw-a-pie-chart-and-doughnut-chart-using-javascript-and-html5-canvas--cms-27197