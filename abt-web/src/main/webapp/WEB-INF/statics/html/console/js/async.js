
var _CHARTS = []; var timeTicket;

var _ITEM;
var _ITEM_INDX;

function initChart() {
    $.ajax({
        type: "GET",
        url: BASE_PATH + '/console/async/tasks',
        dataType:'json',
        success:function(result) {
            createChart(result);
        },
        error:function() { }
    });
}

function createChart(data) {

    for (var i = 0; i < data.length; i++) {
        $('#mainContainer').append('<div class="col-sm-4"><p class="bg-info">' + pinfo(data[i]) + '</p>'
            + '<div style="height:200px;" id="c-' + i + '"></div></div>');
        $('#info-' + data[i].id).popover({content: toinfo(data[i]), html:true });
        makeChart(data[i], i);
    }
    loadLogData();

    clearInterval(timeTicket);
    timeTicket = setInterval(function () {
        requestData();
    }, 5000);
}

function toinfo(data) {
    var t = '';
    for (p in data) {
        t += p + ":" + data[p] + "<br/>";
    }
    return t;
}

function pinfo(data) {
    var t = '<button id="info-' + data.id + '" type="button" class="btn btn-success btn-xs" aria-label="Left Align" ' +
        'data-toggle="popover" data-trigger="click" title="任务详情"> '
        + '<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>'
        + '</button> ' + data.id;
    return t;
}

function makeChart(data, i) {
    var chart = echarts.init($('#c-' + i)[0]);

    // 指定图表的配置项和数据
    var option = {
        //legend: {
        //    data:[_ITEM]//['activeCount', 'poolSize', 'queueSize', 'completedTaskCount']
        //},
        title: {show: false}, legend: {show: false},
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                return params.value[0] + ', 值：' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        grid: {top:10},
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            min: 0,
            type: 'value',
            precision: 0,
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            showSymbol: false,
            hoverAnimation: false,
            smooth:true,
            name: _ITEM,
            type: 'line',
            data: [['', 0]]
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    chart.setOption(option);
    _CHARTS[i] = {};
    _CHARTS[i]['chart'] = chart;
    _CHARTS[i]['data'] = [];
}



function loadLogData() {
    $.ajax({
        type: "GET",
        url: BASE_PATH + '/console/async/tasks/status/log',
        dataType:'json',
        success:function(result) {
            updateDataLog(result);
        },
        error:function()
        {
        }
    });
}


function updateDataLog(objWrap) {
    for (var i = 0; i < objWrap.length; i++) {
        var t = objWrap[i];
        updateData(t);
    }
    updateChartView();
}

function updateChartView() {
    for (var i = 0; i < _CHARTS.length; i++) {
        _CHARTS[i].chart.setOption({
            series: [{
                data: _CHARTS[i].data
            }]
        });
    }
}

function updateData(objWrap) {
    for (var p in objWrap) {
        var date; var obj;
        date = p;
        obj = objWrap[p];

        for (var j = 0; j < obj.length; j++) {
            var data = _CHARTS[j].data;
            data.push({name:date, value: [date, obj[j][_ITEM_INDX]] });
            if (data.length > 100) {
                data.shift();
            }
        }
    }
}


function reloadChart(item, idx) {
    // $('title-div').text(_ITEM);
    _CHARTS = []; app = {};
    $('#mainContainer').empty();
    _ITEM = item;
    _ITEM_INDX = idx;
    initChart();
}


function requestData() {
    $.ajax({
        type: "GET",
        url: BASE_PATH + '/console/async/tasks/status',
        dataType:'json',
        success:function(result) {
            updateData(result);
            updateChartView();
        },
        error:function()
        {
        }
    });
}

function formatdate(date) {
    return date.getFullYear() + '-'
        + (date.getMonth() + 1) + '-'
        + date.getDate() + ' '
        + date.getHours() + ':'
        + date.getMinutes() + ':'
        + date.getSeconds();
}


$(function() {
    $('[data-toggle="popover"]').popover();

    $('#selector button').click(function() {
        $('#selector button').addClass('active').not(this).removeClass('active');
        var name = $(this).attr('ac-key');
        var idx = $(this).attr('ac-idx');
        reloadChart(name, idx);
    });

    reloadChart('activeCount', 0);


});
