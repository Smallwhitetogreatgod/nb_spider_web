/***
 *	作者：DevilJie 
 *	创建时间：2014-11-05
 *	个人网站：http://www.w3210.com
 **/
var j = {jqplot :{}};
var z = {jqplot :{}};
var s2 = [[4,3,2,1],[1,2,3,4],[10,10,10,10],[15,15,15,15]];

j.jqplot.diagram = {
	/**
	 * document: 输出图形的位置id
	 * s：柱状图数据 例如：[[1,2,3,4]]单柱状图  [[1,2,3,4],[2,3,4,5]] 双柱状图 以此类推
	 * title：每一个柱状对应的名称 ["参加人数","中奖人数"]
	 * ticks:x轴显示数据例如[1,2,3,4,5,6,7,8]
	 * x_label:x轴名称
	 * y_label:y轴名称
	 * t: 1：曲线图 2：柱状图
	 */
	base : function(document, s, xtitle, title, ticks, x_label, y_label, max, t){
		var renderer;
		if(t == 1) renderer = $.jqplot.BlockRenderer ;
		else if(t == 2) renderer = $.jqplot.BarRenderer ;
        var plot3 = $.jqplot(document, s, {
        	title: title,
        	
			legend:{ renderer: $.jqplot.EnhancedLegendRenderer,show:true,labels: xtitle },
			animate:true,
			seriesDefaults: {  
               renderer: renderer, // 利用渲染器（BarRenderer）渲染现有图表  
               showMarker: true,//是否显示节点
               
               pointLabels: { show: false //数据点标签
            	     
               },
			 
            },
//            cursor: {
//                show: true,
//                showTooltip: true,    // 是否显示提示信息栏  
//                followMouse: true,     //光标的提示信息栏是否随光标（鼠标）一起移动  
//                zoom:true, 
//            },
            highlighter: {
                show: true,
                sizeAdjust: 5,  // 当鼠标移动到数据点上时，数据点扩大的增量
                fadeTooltip: true,// 设置提示信息栏出现和消失的方式（是否淡入淡出）  
                //lineWidthAdjust: 2.5,   //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度
                tooltipOffset: 2,       // 提示信息栏据被高亮显示的数据点的偏移位置，以像素计
                tooltipAxes: 'y', //x ,y,both
                //tooltipLocation: 'nw' // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw.  
            },
           
			axes:{
				yaxis:{
            		label: y_label==null?"":y_label,
            		max:max,
            		min:0,
            		
				},
				
				xaxis:{
					renderer: $.jqplot.CategoryAxisRenderer, // 设置横（纵）轴上数据加载的渲染器,
					ticks: ticks,
                	label: x_label==null?"":x_label
				}
			},
			series:[{color:'#5FAB78'}] ,
		
			
        });
	}
};
z.jqplot.diagram = {
		/**
		 * document: 输出图形的位置id
		 * s：柱状图数据 例如：[[1,2,3,4]]单柱状图  [[1,2,3,4],[2,3,4,5]] 双柱状图 以此类推
		 * title：每一个柱状对应的名称 ["参加人数","中奖人数"]
		 * ticks:x轴显示数据例如[1,2,3,4,5,6,7,8]
		 * x_label:x轴名称
		 * y_label:y轴名称
		 * t: 1：曲线图 2：柱状图
		 */
		base : function(document, s, xtitle, title, ticks, x_label, y_label,y2_label, max,max2, t){
			var renderer;
			if(t == 1) renderer = $.jqplot.BlockRenderer ;
			else if(t == 2) renderer = $.jqplot.BarRenderer ;
	        var plot3 = $.jqplot(document, s, {
	        	title: title,
	        	legend:{ renderer: $.jqplot.EnhancedLegendRenderer,show:true,labels: xtitle },
				animate:true,
				seriesDefaults: {  
	               renderer: renderer, // 利用渲染器（BarRenderer）渲染现有图表  
	               pointLabels: { show: false }  
	            },
	          
				axes:{
					yaxis:{
	            		label: y_label==null?"":y_label,
	            		max:max,
	            		min:0
					},
					y2axis:{
	            		label: y_label==null?"":y2_label,
	            		max:max2,
	            		min:0
					},
					xaxis:{
						renderer: $.jqplot.CategoryAxisRenderer, // 设置横（纵）轴上数据加载的渲染器,
						ticks: ticks,
	                	label: x_label==null?"":x_label
					}
				}, 
				 highlighter: {
		                show: true,
		                sizeAdjust: 5,  // 当鼠标移动到数据点上时，数据点扩大的增量
		                fadeTooltip: true,// 设置提示信息栏出现和消失的方式（是否淡入淡出）  
		                //lineWidthAdjust: 2.5,   //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度
		                tooltipOffset: 2,       // 提示信息栏据被高亮显示的数据点的偏移位置，以像素计
		                tooltipAxes: 'y', //x ,y,both
		                //tooltipLocation: 'nw' // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw.  
		            },
		            cursor: {
		                show: false,
		                showTooltip: false,    // 是否显示提示信息栏  
		                followMouse: true,     //光标的提示信息栏是否随光标（鼠标）一起移动  
		               
		              
		                //提示信息栏相对于光标的位置。否则，为光标提示信息栏在图标中的位置  
		                // 该属性可选值： n, ne, e, se, etc. 
		                tooltipOffset: 6,     //提示信息栏距鼠标(followMouse=true)或坐标轴（followMouse=false）的位置  
		            },
				series:[{color:'#5FAB78'},
		                {   yaxis: 'y2axis'},  
		                {   yaxis: 'yaxis'}, 
		                {   yaxis: 'y2axis'},   
		                {   yaxis: 'yaxis'}, 
		                {   yaxis: 'y2axis'}, 
		                {   yaxis: 'yaxis'},  
		                {   yaxis: 'y2axis'},
		                {   yaxis: 'yaxis'},  
		                {   yaxis: 'y2axis'}, 
				        ] 
	        });
		}
	};