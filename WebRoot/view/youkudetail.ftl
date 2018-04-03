<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>优酷爬虫收视率数据分析系统</title>
    <link rel="stylesheet" type="text/css" href="${cssRoot}beta.css">
    <script type="text/javascript"  src="${scriptRoot}jQuery.js"></script>
	<script type="text/javascript" src="${scriptRoot}jquery-1.7.1.min.js"></script>
	<script type="text/javascript"  src="${scriptRoot}highcharts.js"></script>
	<script>       		
	$(function () {		
			var tvId = $("#tvId").val();
			//var tvname=$("#tvname").val();
			var number = "daynumber";
			filterCondition(tvId,number);	
			$(".js-list").change(function(){
				var number = $(".js-list").find("option:selected").val();
				var tvId = $("#tvId").val();
				filterCondition(tvId,number);
			})
	}); 	
	
	
	function filterCondition(param1,param2){
			//加载数据
			$.ajax({
				url : "${webRoot}tv/info.do?tvId="+param1+"&number="+param2,
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
	}

	function drawlines(p1,p2){
		$('#chart1').highcharts({
        title: {
            text: '优酷视频数据指数分析',
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
    <div class="content">
         <div class="header">
            <div class="h-left float-left">优酷网络爬虫前台分析系统</div>
        </div>
        <div class="c-info">
            <div class="form-line4">
                <!--
                暂时只显示优酷系统，别的系统待完善
                <div class="company-btns">
                    <button type="button" class="js-company-btn company-btn"> </button>
                    <button type="button" class="js-company-btn company-btn active">优酷</button>
                    <button type="button" class="js-company-btn company-btn"></button>
                </div>

                -->

                <p class="info-title">
                     <span>${tvname} 走势图</span>
                    <span>总播放：<span class="no-bold">${allnumber}</span></span>
                    <span class="float-right">
                        <select class="js-list" id="selectNum">
                            <option value="allnumber">总播放量</option>
                            <option value="collectnumber">收藏数</option>
                            <option value="commentnumber">评论数</option>
                            <option value="supportnumber">赞</option>
                            <option value="againstnumber">踩</option>
                            <option value="daynumber" selected>今日新增</option>
                        </select>
                    </span>
                </p>
            </div>
            <input id="tvId" type="hidden" name="tvId" value="${tvId}">
            <div class="chart-container" id="chart1"></div>
        </div>
    </div>
    <script>
        (function(){
            // 点击搜索按钮执行
            $("#search-btn").click(function(){

            })
            // 点击全网等三个按钮执行
            $(".js-company-btn").click(function(){
                $(this).addClass("active").siblings().removeClass("active")
            })
            // 下拉列表变化执行
            $(".js-list").change(function(){

            })
        })()
    </script>
</body>
</html>