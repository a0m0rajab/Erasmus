"use strict";
let source = [];
let colNumber = 0;
let textShowed = 0;
let colAdded = false;
let headerDataShowed = false;
let seprator = ",";
let exampleFiles = ["examples/chartData.csv", "examples/GraphLine.csv"];
// local file reader warper.
function localCSVloader() {
    readFile();
    // CSVtable();
}

// create CSV table and destroy the old one.
function CSVtable() {
    createTable(source);
    destroyDataTables();
    createTable(source);
    dataTables();
}
// external file reader warper 
function externalFile(url) {
    readText(url);
}

// load example function to show a specific example from external file
function LoadExample() {
    switch (document.getElementById('ExamlpesDropDown').selectedIndex) {
        case 0:

            externalFile(exampleFiles[0]);

            break;
        case 1:
            externalFile(exampleFiles[1]);


            break;

    }
}

let local = []
let remote;
// written for more than one file - optimizable
function fileSelect(evt) {
    local = []
    var files = evt.files; // FileList object
    for (let file of files) {
        let reader = new FileReader();
        reader.onload = function() {
            // out.innerText +=reader.result +"\n";
            local.push({ data: reader.result, name: file.name, type: file.type });
            localCSVloader();
        }
        reader.readAsText(file);
    }
    reset();
    showElemants();
}
// file reader 
function readFile() {
    let text = "";

    local.forEach(file => {
        // console.log(file.data)
        dataToArray(file.data)
    })
}

// parse CSV text Data to array
function dataToArray(textData) {
    source = [];
    let lines = textData.split("\n")
    lines.forEach(line => {
        let rows = line.split(seprator);
        source.push(rows)
        if (rows.length > colNumber) {
            colNumber = rows.length;
        }
    })
    CSVtable();
}
// Read from external link.
function readText(url) {
    fetch(url)
        .then(r => r.text())
        //response of fetch() is r
        .then(t => dataToArray(t));

    //response of text() is t
}


// add extra text area to use it for data input.
function addFeild() {
    // appendDataColumn();
    createTextFeild(colNumber);
    colNumber += 1;
}
// change headers 
function setHeader() {

    if (textShowed == 0) {
        textInputsCreator(source[0]);
        headerDataShowed = true;
    } else if (headerDataShowed == false) {
        showHeaderData();
    } else {
        destroyDataTables();
        source[0] = getUserInputs();
        clearUserInputs();
        createTable(source);
        dataTables();
    }

}

function showHeaderData() {
    headerDataShowed = true;
    for (let index = 0; index < colNumber; index++) {
        document.getElementById("userInput" + index).value = source[0][index]
    }
}
// export file as CSV.
function exportFile() {
    //https://stackoverflow.com/questions/14964035/how-to-export-javascript-array-info-to-csv-on-client-side
    let csvContent = "data:text/csv;charset=utf-8," + source.map(e => e.join(",")).join("\n");
    var encodedUri = encodeURI(csvContent);
    var link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "my_data.csv");
    document.body.appendChild(link); // Required for FF
    link.click();
}
//add datat to table or create the text input
// used for pure HTML without row.add function
function addData() {
    if (textShowed == 0) {
        textInputsCreator();
    } else {
        destroyDataTables();
        userDataUpdate();
        clearUserInputs();
        createTable(source);
        dataTables();
    }
}
// get all user data from text feild and add it to data source
function getUserInputs() {
    let userData = [];
    for (let index = 0; index < colNumber; index++) {
        userData.push(document.getElementById("userInput" + index).value);
    }
    return userData;
}
// add user data to the source
function userDataUpdate() {
    source.push(getUserInputs());
}
// create all text feild for user input 
function textInputsCreator(text = "") {
    document.getElementById("header1").style.display = "";
    document.getElementById("inputs").innerHTML = ""

    for (let index = 0; index < colNumber; index++) {
        createTextFeild(index, text[index]);
    }
    textShowed = 1;
}
// create text input
function createTextFeild(id, text = "") {
    //https://www.w3schools.com/jsref/dom_obj_text.asp
    // inputs.innerHTML = "";
    var textFeild = document.createElement("INPUT");
    textFeild.setAttribute("type", "text");
    textFeild.setAttribute("value", text);
    textFeild.setAttribute("id", "userInput" + id)
    inputs.appendChild(textFeild);
}


// destroyed at this point xD 

// create table and add data on it.
function createTable(tableData) {
    var table = document.getElementById('table');
    table.innerHTML = "";
    createTableElemants(tableData.slice(0, 1), "thead");
    createTableElemants(tableData.slice(1), "tbody");
    // dataTables();

}
// create HTML elemants for data.
function createTableElemants(tableData, name) {
    var table = document.getElementById('table');
    var elemants;
    if (!name) {
        elemants = table.getElementsByTagName("tbody");
    } else {
        elemants = document.createElement(name);
    }


    tableData.forEach(function(rowData) {
        var row = document.createElement('tr');

        rowData.forEach(function(cellData) {
            var cell = document.createElement('td');
            cell.appendChild(document.createTextNode(cellData));
            row.appendChild(cell);
        });

        elemants.appendChild(row);
    });

    table.appendChild(elemants);
    //document.body.appendChild(table);
    console.log(name + " created")

}


// destroy table.  // did not work went with other version 
function clearTable() {
    textShowed = 0;
    var table = $('#table').DataTable();
    //clear datatable
    table.clear().draw();
    //destroy datatable
    table.destroy();
}

// hide exporter , data adder and feild adder.
function hideElemants() {
    document.getElementById("tableEditor").style.display = "none";
    // document.getElementById("exporter").style.display = "none";
    // document.getElementById("header1").style.display = "none";
    // document.getElementById("feildplus").style.display = "none";


}
// remove all user input feild.
function resetAdd() {

    textShowed = 0;
    colNumber = 0;
    inputs.innerHTML = "";
    document.getElementById("header1").style.display = "none";


}
// idk why do i have it,even tho its useless... if you got the idea of this function it would be amazing to explain it to me :) 
function updateTable() {
    source = []
    source.push(source)
}
// show exporter , data adder and feild adder.
function showElemants() {
    document.getElementById("tableEditor").style.display = "";
    // document.getElementById("adder").style.display = "";
    // document.getElementById("feildplus").style.display = "";
}
// clear input text feild of user 
function clearUserInputs() {
    headerDataShowed = false;
    for (let index = 0; index < colNumber; index++) {
        document.getElementById("userInput" + index).value = "";
    }

}
// getter for seprator value
function changeSeprator() {
    seprator = document.getElementById("sepratorVal").value;
}
// set seprater to comma
function resetSeprator() {
    seprator = document.getElementById("sepratorVal").value = ",";
}
// wrapper for resteAdd and reset Seprator
function reset() {


    resetAdd();
    resetSeprator();
}
// create DIV element and append to the table cell
function createCell(cell, text, style) {
    var div = document.createElement('div'), // create DIV element
        txt = document.createTextNode(text); // create text node
    div.appendChild(txt); // append text node to the DIV
    div.setAttribute('class', style); // set DIV class attribute
    div.setAttribute('className', style); // set DIV class attribute for IE (?!)
    cell.appendChild(div); // append DIV to the table cell
}
// append column to the HTML table
function appendColumn() {
    var tbl = document.getElementById('table'), // table reference
        i;
    // open loop for each row and append cell
    for (i = 0; i < tbl.rows.length; i++) {
        createCell(tbl.rows[i].insertCell(tbl.rows[i].cells.length), i, 'col');
    }
}
// warper for the add data thingy.
function appendDataColumn() {
    if (textShowed != 0) {
        addFeild();
    } else {
        colNumber++;
    }
    destroyDataTables();
    addColumn();
    createTable(source);
    dataTables();
}
// add row to data which immediatly add row when Createtable again - create table use the table delete the old and add new one.
function addColumn() {
    // source.forEach(row => {
    //     row.push(" ");
    // });
    // createTable(source);
    let colName = prompt("Please enter the Column name", "Any name you desire");
    if (colName == null || colName == "") {
        console.log("cancelled")
    } else {
        colAdded = true;

        // add empty data for ery record and for the first one add the column name.
        source.forEach(
            function(row, i) {
                if (i == 0) {
                    row.push(colName)
                } else {
                    row.push(" ");
                }

            }
        );

    }
}
// update source data with new data tables data 

// enable data tables function for extra power.
function dataTables() {

    // /
    //     $(document).ready(function () {

    // });

    // $('#table').DataTable();
    $(document).ready(function() {
        let table = $('#table').DataTable({
            "lengthMenu": [
                [10, 25, 50, 75, 100, -1],
                [10, 25, 50, 75, 100, "All"]
            ],
            select: {
                blurable: true
            },
            // data:source,
            dom: 'BSlfrtip',
            buttons: [
                'copyHtml5',
                'excelHtml5',
                'csvHtml5',
                'pdfHtml5',
                {
                    text: 'Add Data',
                    action: function(e, dt, node, config) {
                        if (textShowed == 0 || colAdded) {
                            textInputsCreator();
                            colAdded = false;
                        } else {
                            let inputs = getUserInputs();
                            table.row.add(
                                inputs
                            ).draw(false);
                            source.push(inputs);
                            clearUserInputs();

                        }


                        // alert(
                        //     // 'Row data: ' +
                        //     // JSON.stringify(dt.row({ selected: true }).data())
                        // );
                    },
                    enabled: true
                },
                {
                    text: 'Delete rows',
                    action: function(e, dt, node, config) {
                        let count = dt.rows({ selected: true }).count();
                        //  for testing, finding an array that get all the data at it, to use for drawing later.
                        // console.log(dt.rows({ selected: true }).data());
                        table
                            .rows('.selected')
                            .remove()
                            .draw();
                        alert(count + " Rows deleted");
                        updateSource();
                    },
                    // should be false.
                    enabled: true
                },
                {
                    text: 'set header',
                    action: function(e, dt, node, config) {
                        setHeader();
                    },
                    enabled: true
                },
                {
                    text: 'add column',
                    action: function(e, dt, node, config) {
                        appendDataColumn()
                    },
                    enabled: true
                }, {

                    text: 'Draw Pie Chart',
                    action: function(e, dt, node, config) {

                        let dataArray = dt.rows({ selected: true }).data().toArray();
                        let category = source[0];
                        resetDrawingArea()
                        for (const key in dataArray) {
                            // data Tables returned the array as String, so i had to parse it into integer before using it.
                            // StackOver flow used.
                            let x = dataArray[key].map(Number);
                            // let y = [1,2,3,4,5];
                            drawPichart(x, category, 0.5);
                        }
                        // dataArray.forEach(element => {
                        //     elements = element

                        // });
                    },
                    // should be false.
                    enabled: true
                }, {

                    text: 'Graph Line',
                    action: function(e, dt, node, config) {
                        // use this method or the method of getting the rows then change the graph line js, its the same but you can use the other one at the same time
                        // so you can have a less code here but more there, it depends on your need. both of them works.

                        // get the selected rows indexes, then get the numbers of the specific coloums 
                        // https://datatables.net/forums/discussion/43066/getting-values-from-a-single-column-for-all-selected-rows

                        let x = getElementsOfRow(dt, 0, 1);
                        let y = getElementsOfRow(dt, 1, 1);
                        let z = getElementsOfRow(dt, 2, 1);
                        let a = getElementsOfRow(dt, 3);
                        drawLineGraph(x, y, z, a);


                        // console.log(x);
                        // console.log(y);
                        // console.log(z);
                        // console.log(a);


                    },
                    // should be false.
                    enabled: true
                }

            ]
        });
        table.buttons().container()
            .appendTo('#table_wrapper .col-md-6:eq(0)');

        function getElementsOfRow(dt, colNumber, number) {
            let rows = dt.rows({ selected: true }).indexes();
            let array = dt.cells(rows, colNumber).data().toArray();
            if (number) {
                return array.map(Number);
            }
            return array;
        }

        // there is a bug at set header -- need to check. 
        // does the rows.data() return the header? if not then you have to add manuallly , and probably thats the bug 
        // 
        function updateSource() {
            source = source.slice(0, 1);
            let data = table.rows().data().toArray();
            // console.log()
            // console.log("table array")
            // console.log(data)
            // console.log("ddone")
            // source.concat(data)
            data.forEach(e => { source.push(e) })

        }

        function removeRowSource() {
            let rows = table.rows('.selected').indexes().toArray();
            // console.log("rows information");
            rows.forEach(e => {
                source.splice(e, 0);
            });
        }

        function myCallbackFunction(updatedCell, updatedRow, oldValue) {
            console.log("The new value for the cell is: " + updatedCell.data());
            console.log("The values for each cell in that row are: " + updatedRow.data());
        }


        table.on('select deselect', function() {
            var selectedRows = table.rows({ selected: true }).count();

            // console.log(selectedRows);
            // table.button(4).enable(selectedRows === 1);
            //table.button(5).enable(selectedRows > 0);
        });
        table.MakeCellsEditable({
            "onUpdate": updateSource
        });

    });
}

function destroyDataTables() {
    if ($.fn.DataTable.isDataTable('#table')) {
        $('#table').DataTable().destroy();
        // $('#table').empty();
    };
}

/**
 * TODO: 
 * read from external FIle 
 * clean the code 
 * add examples for each function 
 * redesgin the GUI
 * check botstrap 
 * remove the unneccesarly libraries.
 * if file loaded in null it brings the source code of the webPage...
 */