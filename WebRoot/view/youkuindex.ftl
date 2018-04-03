<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>优酷视频爬虫分析</title>
    <link rel="stylesheet" type="text/css" href="${cssRoot}beta.css">
    <script type="text/javascript" src="${scriptRoot}jquery-1.7.1.min.js"></script>
    <script>   
    	$(document).ready(function() {
	    	$("#search-btn").click(function(){
	    		var skey = $("#search").val();
	    		var sort = "desc";
	    		var field = "daynumber";
	    		//search(skey,sort,field);
	    		//list();
	    		toPage(1)
	    	})
	    	list();
		
	});
	
	function toPage(page){
		$("input[name=pageIndex]").val(page);
		$("input[name=pageSize]").val($("select[name=pageSize] option:selected").val());
		list();
	}
	    //加载数据
		function list(){
		$.ajax({
			url : "${webRoot}tv/list.do",
			type : 'POST',

		 	dataType : 'json',
			data : $("form").serialize(),
			success : function(data) {
				$table = $("#tt");
				$("#tt").html("");
				var list = data.list;
				for(var i=0;i<list.length;i++){
					$tr = $("<tr></tr>");
					$td1 = $("<td><a href='${webRoot}tv/detail.do?tvId="+list[i].tvId+"&"+"tvname="+list[i].tvname+"&"+"allnumber="+list[i].allnumber+"' target='_blank'>"+list[i].tvname+"</a></td>");
					$td2 = $("<td>"+list[i].daynumber+"</td>");
					$td3 = $("<td>"+list[i].allnumber+"</td>");
					$td4 = $("<td>"+list[i].commentnumber+"</td>");
					$td5 = $("<td>"+list[i].collectnumber+"</td>");
					$td6 = $("<td>"+list[i].supportnumber+"</td>");
					$td7 = $("<td>"+list[i].againstnumber+"</td>");
					$tr.append($td1);
					$tr.append($td2);
					$tr.append($td3);
					$tr.append($td4);
					$tr.append($td5);
					$tr.append($td6);
					$tr.append($td7);	
					$table.append($tr);				
				}
				$(".flip-wrap").html(data.bar);
				//$("#dataList").empty().append(data);
			},
			error : function(data) {
				alert("数据记载失败!");
			}

		});
	}
    </script>
</head>
<body>
    <div class="content">
         <div class="header">
            <div class="h-left float-left">优酷爬虫前台分析系统->更新中 ...</div>
            <div class="float-right">
                <form>
                	<input type="hidden" name="pageSize" />
                	<input type="hidden" name="pageIndex"/>
                    <input type="text" name="search" id="search" placeholder="请输入节目名称">
                    <button type="button" class="green-btn" id="search-btn">搜索</button>
                </form>
            </div>
        </div>
        <div class="c-info">
            <div class="form-line4">
                <p class="info-title">详细数据<span class="title-tip-text-999">（点击节目名称查看指标走势图）</span></p>
                <div class="search-wrap">
                    <table class="rate-table">
                        <thead>
                            <tr>
                                <th>节目名称</th>
                                <th><a class="js-sort">今日新增<span class="sort-icon sort-down"></span></a></th>
                                <th><a class="js-sort">总播放数<span class="sort-icon sort-down"></span></a></th>
                                <th>评论数</th>
                                <th>收藏数</th>
                                <th>赞</th>
                                <th>踩</th>
                            </tr>
                        </thead>
                        <tbody id="tt">

                        </tbody>
                    </table>
                    <div class="flip-wrap">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${scriptRoot}jquery-1.11.1.min.js"></script>
    <script>
        (function(){
            $(".js-sort").click(function(){
                // 点击的排序图标后的状态
                var sortStare = changeSortIcon($(this));
                //todo： 按照排序规则显示。 是当页显示。还是全局显示
            })
            // 排序图标切换，参数是切换按钮的jquery对象
            // 返回"up"为朝上状态,"down"朝下状态,false错误
            function changeSortIcon($dom){
                var $icon = $dom.find(".sort-icon");
                if ($icon.hasClass("sort-down")) {
                    $icon.removeClass("sort-down").addClass("sort-up");
                    return "up"
                }else if($icon.hasClass("sort-up")){
                    $icon.removeClass("sort-up").addClass("sort-down");
                    return "down"
                }else{
                    return false;
                }
            }
        })()
    </script>
</body>
</html>