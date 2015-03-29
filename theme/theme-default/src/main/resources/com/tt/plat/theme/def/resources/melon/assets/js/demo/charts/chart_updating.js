"use strict";
$(document).ready(function () {
    var a = [], d = 200;

    function c() {
        if (a.length > 0) {
            a = a.slice(1)
        }
        while (a.length < d) {
            var h = a.length > 0 ? a[a.length - 1] : 50;
            var j = h + Math.random() * 10 - 5;
            if (j < 0) {
                j = 0
            }
            if (j > 100) {
                j = 100
            }
            a.push(j)
        }
        var g = [];
        for (var f = 0; f < a.length; ++f) {
            g.push([f, a[f]])
        }
        return g
    }

    var b = $.plot("#chart_updating", [c()], $.extend(true, {}, Plugins.getFlotDefaults(), {yaxis: {min: 0, max: 100}, xaxis: {min: 0, max: 100}, series: {lines: {lineWidth: 1.5, fill: true}}}));

    function e() {

        b.setData([c()]);
        b.draw();
        setTimeout(e, 250)
    }

    e()
});