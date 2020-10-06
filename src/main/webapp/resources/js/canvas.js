const CANVAS_WIDTH = 200;
const CANVAS_HEIGHT = 200;
const CENTER = {
    x: CANVAS_WIDTH / 2,
    y: CANVAS_HEIGHT / 2
};
const R = Math.min(CANVAS_HEIGHT, CANVAS_WIDTH) / 2.5;
const AXES_MARGIN = R / 10;
const AXES_WIDTH = 2;
const ENTRY_RADIUS = 3;

// colors
const COLOR_BACKGROUND = "rgb(255,255,255)";
const COLOR_FUNCTION = "rgb(36,66,173)";
const COLOR_AXES = "rgb(0,0,0)";
const COLOR_ENTRY_SUCCESS = "rgb(0,255,0)";
const COLOR_ENTRY_FAIL = "rgb(255,0,0)";
const COLOR_CURRENT_ENTRY = "rgb(158,116,99)";

document.addEventListener("DOMContentLoaded", function () {
    let canvas = $("canvas");
    canvas.width = CANVAS_WIDTH;
    canvas.height = CANVAS_HEIGHT;
    let c2d = canvas.getContext("2d");
    canvas.addEventListener("click", function (e) {
        let click = getCursorPosition(canvas, e);
        console.log(click);
        let rFromInput = getRValue();
        $("x-input").value = (click.x - CENTER.x) * rFromInput / R;
        $("y-input").value = (CENTER.y - click.y) * rFromInput / R;
        draw(c2d);
    });
    $('x-input').addEventListener('input', function (e) {
        draw(c2d);
    })
    $('y-input').addEventListener('input', function (e) {
        draw(c2d);
    })
    let r_group = document.getElementsByClassName("form-radio");
    for (let i = 0; i < r_group.length; i++) {
        let radioButton = r_group.item(i).childNodes.item(1);
        radioButton.addEventListener('click', function (e) {
            checkInput();
            draw(c2d);
        });

    }

    draw(c2d);
})

function draw(c2d) {
    console.log("Drawing");
    clear(c2d);
    drawFunction(c2d);
    drawAxes(c2d);
    drawEntries(c2d);
}

function clear(c2d) {
    c2d.fillStyle = COLOR_BACKGROUND;
    c2d.fillRect(0, 0, c2d.canvas.width, c2d.canvas.height);
}

function drawFunction(c2d) {
    c2d.fillStyle = COLOR_FUNCTION;
    drawCircle(c2d, CENTER.x, CENTER.y, R);

    c2d.fillStyle = COLOR_BACKGROUND;
    c2d.fillRect(CENTER.x - R, CENTER.y - R, R, 2 * R);
    c2d.fillRect(CENTER.x - R, CENTER.y, 2 * R, R);

    c2d.fillStyle = COLOR_FUNCTION;
    c2d.fillRect(CENTER.x - R / 2, CENTER.y, R / 2, R);

    c2d.beginPath();
    c2d.moveTo(CENTER.x - R, CENTER.y);
    c2d.lineTo(CENTER.x, CENTER.y - R / 2);
    c2d.lineTo(CENTER.x, CENTER.y);
    c2d.closePath();
    c2d.fill();
}

function drawAxes(c2d) {
    c2d.strokeStyle = COLOR_AXES;
    c2d.lineWidth = AXES_WIDTH;

    c2d.beginPath();
    c2d.moveTo(CENTER.x, c2d.canvas.height - AXES_MARGIN);
    c2d.lineTo(CENTER.x, AXES_MARGIN);
    c2d.stroke();
    c2d.closePath();

    c2d.beginPath();
    c2d.moveTo(AXES_MARGIN, CENTER.y);
    c2d.lineTo(c2d.canvas.width - AXES_MARGIN, CENTER.y);
    c2d.stroke();
    c2d.closePath();

}

function drawEntries(c2d) {
    let entries = getEntries();
    if (checkXInput() && checkYInput()) {
        entries.push({
            x: $('x-input').value,
            y: $('y-input').value,
            r: getRValue(),
            result: null
        });
    }
    entries.forEach(function (entry) {
        if (entry.result === "true") {
            c2d.fillStyle = COLOR_ENTRY_SUCCESS;
        } else if (entry.result === "false") {
            c2d.fillStyle = COLOR_ENTRY_FAIL;
        } else {
            c2d.fillStyle = COLOR_CURRENT_ENTRY;
        }
        let x = CENTER.x + (entry.x * R) / entry.r;
        let y = CENTER.y - (entry.y * R) / entry.r;
        drawCircle(c2d, x, y, ENTRY_RADIUS);
    })
}

function getEntries() {
    let res = [];
    let entriesNodes = $("result-table").childNodes[1].firstChild.childNodes;
    for (let i = 1; i < entriesNodes.length; i++) {
        let values = entriesNodes[i].childNodes;
        res.push({
            x: values[2].innerText,
            y: values[4].innerText,
            r: values[6].innerText,
            result: values[8].innerText
        })
    }
    return res;
}

function drawCircle(c2d, x, y, r) {
    c2d.beginPath();
    c2d.arc(x, y, r, 0, 2 * Math.PI, false);
    c2d.fill();
    c2d.lineWidth = 1;
    // c2d.stroke();
    c2d.closePath();
}

function getCursorPosition(canv, event) {
    const rect = canv.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    }
}