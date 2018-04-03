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

	<script type="text/javascript"  src="${scriptRoot}jqplot.js"></script>
	<script src="${scriptRoot}Date.js"></script>
    <script src="${scriptRoot}jQuery-jcDate.js"></script>
    <script>

      	$(document).ready(function() {
		//加载数据
		$.ajax({
			url : "${webRoot}tv/list.do",
			dataType : 'json',
			success : function(data) {
				$table = $("#tt");
				for(var i=0;i<data.length;i++){
					$tr = $("<tr></tr>");
					$td1 = $("<td><a href='${webRoot}tv/detail.do?tvId="+data[i].tvId+"' target='_blank'>"+data[i].tvname+"</a></td>");
					$td2 = $("<td>"+data[i].daynumber+"</td>");
					$td3 = $("<td>"+data[i].allnumber+"</td>");
					$td4 = $("<td>"+data[i].commentnumber+"</td>");
					$td5 = $("<td>"+data[i].collectnumber+"</td>");
					$td6 = $("<td>"+data[i].supportnumber+"</td>");
					$td7 = $("<td>"+data[i].againstnumber+"</td>");
					$tr.append($td1);
					$tr.append($td2);
					$tr.append($td3);
					$tr.append($td4);
					$tr.append($td5);
					$tr.append($td6);
					$tr.append($td7);	
					$table.append($tr);				
				}
				
				//$("#dataList").empty().append(data);
			},
			error : function(data) {
				alert("数据记载失败!");
			}

		});

	});
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
            <div class="form-line4">
            	<br>
                <p class="info-title">中国网络视频指数</p>
                <div class="search-wrap">
                    <table id="tt" class="rate-table">
                        <tr>
                            <th>节目名称</th>
                            <th><a>今日新增 <span class="sort-icon sort-down" id="1"></span></a></th>
                            <th>总播放数<span class="sort-icon sort-down" id="2"></th>                       
                            <th>评论数<span class="sort-icon sort-down" id="3"></th>
                            <th>收藏数<span class="sort-icon sort-down" id="4"></th>
                            <th>赞<span class="sort-icon sort-down" id="5"></th>
                            <th>踩<span class="sort-icon sort-down" id="6"></th>
                        </tr>
                    </table>
                    <div class="flip-wrap">
                        <span id="info">
                        </span>
                        <span class="float-right">
                            <a onclick="Init('${webRoot}',1,toggle,px);" class="active">首页</a>
                            <a onclick="Init('${webRoot}',parseInt(nowpage)-1,toggle,px);" class="active">上一页</a>
                            <a onclick="Init('${webRoot}',parseInt(nowpage)+1,toggle,px);" class="active">下一页</a>
                            <a onclick="Init('${webRoot}',maxpage,toggle,px);"class="active">尾页</a>
                            <span>
                                 跳转到第<input id="tz" type="text" maxlength="2" style="width: 20px">页
                                <a class="green-btn" onclick="Init('${webRoot}',document.getElementById('tz').value,toggle,px);">跳转</a>
                            </span>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
	</script>
</body>
</html>