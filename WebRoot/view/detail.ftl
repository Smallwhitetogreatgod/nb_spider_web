<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>收视率统计系统</title>
    <link rel="stylesheet" type="text/css" href="${cssRoot}rate.css">
    <link rel="stylesheet" type="text/css" href="${cssRoot}jcDate.css">
    <script type="text/javascript"  src="${scriptRoot}jQuery.js"></script>
	<script type="text/javascript" src="${scriptRoot}jquery-1.7.1.min.js"></script>
	<link type="text/css" href="${cssRoot}jquery-ui-1.8.17.custom.css" rel="stylesheet" />
    <link type="text/css" href="${cssRoot}jquery-ui-timepicker-addon.css" rel="stylesheet" />
	<script type="text/javascript" src="${scriptRoot}jquery-ui-1.8.17.custom.min.js"></script>
	<script type="text/javascript" src="${scriptRoot}jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="${scriptRoot}jquery-ui-timepicker-zh-CN.js"></script>
	<script type="text/javascript"  src="${scriptRoot}highcharts.js"></script>
    <script>       		
	$(function () {
			var tvId = $("#tvId").val();
			var number = "commentnumber";
			//加载数据
			$.ajax({
				url : "${webRoot}tv/info.do?tvId="+tvId+"&number="+number,
				dataType : 'json',
				success : function(data) {
					var series = data[0].series;
					var categories = data[0].categories;
					drawlines(categories,series);
				},
				error : function(data) {
					alert("数据记载失败!");
				}
	
			});
	}); 

	function drawlines(p1,p2){
		$('#chart1').highcharts({
        title: {
            text: '中国网络视频指数',
            x: -20 //center
        },
        subtitle: {
            text: '来源: 优酷',
            x: -20
        },
        xAxis: {
            categories: p1
        },
        yAxis: {
            title: {
                text: ''//y轴说明
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '(人次)'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: p2
    	});
	}
    </script>
</head>
<body>
    <div class="header">
        <div class="h-left float-left">大讲台Hadoop实战项目——收视率统计系统V 1.0</div>
        <div class="h-right float-right">
            <div class="h-user-wrap">
               <img src="${imagesRoot}rateUser.png">
                <span>阳光冰雪</span>
            </div>
            <a class="h-quit"><b>退出</b></a>
        </div>
    </div>
    <div class="content">
        <div class="c-info">
            <div class="form-line3">
                <p class="info-title">走势图</p>
                <div class="line-photo-time">
                		<span>时间粒度</span>
                        <a id="d3"class="forbid" onclick="changeDate(3)">分</a>
                        <a id="d2"onclick="changeDate(2)">时</a>
                        <a id="d1" class="active" onclick="changeDate(1)">日</a>
                        
                              <select id="s1">
                   
				                </select>
				                对比
				                <select id="s0">
				                
				                </select>
                    </div>
          
                <div class="line-photo-wrap">
                	<input id="tvId" type="hidden" name="tvId" value="${tvId}">
                	<div id="chart1"  ></div>
                    
                </div>
            </div>
        </div>
    </div>
</body>
</html>