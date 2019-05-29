var graph;
var xPadding = 30;
var yPadding = 30;
// let x = [5, 5, 7.5, 5, 2.5]
// let y = [0, 10, 15, 10, 15]
// let z = [0, 120, 150, 100, 250]
// let a = ["test", "hello", "world", "happy", "notReally"]

// class and constructor -- more usefull?
function setData(x, y, z, a) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.a = a;

}
// warper for calling the function
function drawLineGraph(x, y, z, a) {
    resetDrawingArea();
    setData(x, y, z, a);
    createPureCanvas();
    graph = myCanvas;
    drawGraph();
}

// Returns the max Y value in our data list
function getMaxY() {
    return getMaxScaled(y);
}

function getMaxX() {
    return getMaxScaled(x);

}

// get max of value completed to 10, since 10 is the scale of our graph
function getMaxScaled(data) {
    var max = 0;

    for (var i = 0; i < data.length; i++) {
        if (data[i] > max) {
            max = data[i];
        }
    }

    max += 10 - max % 10;
    return max;
}
// get the colour of specific value, based on 3 colour heat map (Red,yellow,Green)
function heatMapColorforValue(value, max) {
    //Read about HSL
    var h = (1.0 - value / max) * 120
    return "hsl(" + h + ", 100%, 50%)";
}
// modify the code for arrays.
function getAxisesMax(array) {
    let max = Math.max(...array)
    max += 10 - max % 10;
    return max;
}

// Return the x pixel for a graph point
function getXPixel(val) {
    return ((graph.width - xPadding) / getMaxX()) * val + (xPadding) // * 1.5);
}

// Return the y pixel for a graph point
function getYPixel(val) {
    return graph.height - (((graph.height - yPadding) / getMaxY()) * val) - yPadding;
}

// Draw the axises
function axises(graph, ctx) {
    ctx.beginPath();
    ctx.moveTo(xPadding, 0);
    ctx.lineTo(xPadding, graph.height - yPadding);
    ctx.lineTo(graph.width, graph.height - yPadding);
    ctx.stroke();
}

function drawGraph() {
    // graph = document.getElementById("");
    var c = graph.getContext('2d');

    c.lineWidth = 2;
    c.strokeStyle = '#333';
    c.font = 'italic 8pt sans-serif';
    c.textAlign = "center";


    axises(graph, c);

    // // Draw the X value texts
    // for (var i = 0; i < x.length; i++) {
    //     c.fillText(x[i], getXPixel(i), graph.height - yPadding + 20);
    // }

    // Draw the X value texts
    for (var i = 0; i < getMaxX(); i += 10) {
        c.fillText(i, getXPixel(i), graph.height - yPadding + 20);
    }

    // Draw the test besides the dots
    for (var i = 0; i < a.length; i++) {
        c.fillText(a[i], getXPixel(x[i]) + 30, getYPixel(y[i]));
    }

    // Draw the Y value texts
    c.textAlign = "right"
    c.textBaseline = "middle";

    for (var i = 0; i < getMaxY(); i += 10) {
        c.fillText(i, xPadding - 10, getYPixel(i));
    }

    c.strokeStyle = '#f00';

    // Draw the line graph
    c.beginPath();
    c.moveTo(getXPixel(x[0]), getYPixel(y[0]));
    for (var i = 1; i < y.length; i++) {
        c.lineTo(getXPixel(x[i]), getYPixel(y[i]));
    }
    c.stroke();

    // Draw the dots
    // c.fillStyle = '#333';

    for (var i = 0; i < y.length; i++) {
        c.beginPath();
        c.fillStyle = heatMapColorforValue(z[i], 255);
        c.arc(getXPixel(x[i]), getYPixel(y[i]), 4, 0, Math.PI * 2, true);
        c.fill();
    }
}

/**
 * f =""    
for (var i = 0; i < a.length; i++) {
               f += x[i]+","+y[i]+","+z[i]+","+a[i]+",\n"
            }
console.log(f)
 */

/**
 * TODO:
 * check if there is the same data on the same point, and get the last one isntead of drawing both of them,
 * check the text and how to use it,
 * write a tutorial and develop the code. 
 * 
 */