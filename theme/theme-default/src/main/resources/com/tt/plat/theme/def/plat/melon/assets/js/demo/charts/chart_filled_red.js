"use strict";
$(document).ready(function () {
    var b = [
        [1262304000000, 2700],
        [1264982400000, 2300],
        [1267401600000, 2600],
        [1270080000000, 2300],
        [1272672000000, 2000],
        [1275350400000, 1900],
        [1277942400000, 1500],
        [1280620800000, 1000],
        [1283299200000, 1300],
        [1285891200000, 1000],
        [1288569600000, 900],
        [1291161600000, 500]
    ];
    var a = [
        {label: "Total clicks", data: b, color: App.getLayoutColorCode("red")}
    ];
    $.plot("#chart_filled_red", a, $.extend(true, {}, Plugins.getFlotDefaults(), {xaxis: {min: (new Date(2009, 12, 1)).getTime(), max: (new Date(2010, 11, 2)).getTime(), mode: "time", tickSize: [1, "month"], monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"], tickLength: 0}, series: {lines: {fill: true, lineWidth: 1.5}, points: {show: true, radius: 2.5, lineWidth: 1.1}}, grid: {hoverable: true, clickable: true}, tooltip: true, tooltipOpts: {content: "%s: %y"}}))
});