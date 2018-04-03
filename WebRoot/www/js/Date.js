
var labelData=["请选择","收视人数","平均收视人数","收视率","市场份额","平均到达人数","到达率","平均收视时长"];
var labelData2=["请选择","平均收视人数","收视率","市场份额","平均到达人数","到达率"];
var maxpage=0;
var nowpage=1;
var nowState;
var nowX;
var nowTime=0;
var nowCTime=0;
var nowDate;
var nowFlag=1;
var nowY_label;
var nowY2_label;
var ActionX;
var AWidth;
var toggle=1;
var ADateState=1;
var XDateState=3;
var px=1;
function Init(url,page,val,px)
{
	if(page<=0)
		return ;
	
	//alert(page);
	var  data="name="+val+"&page="+page+"&px="+px;
	 $.ajax({
         type: "POST",
         url: url+"init.do",
         dataType:"text",
         data:data, 
         async:false,  
         success:function(data,status){
        	 	InitDateAnalysis(data);
 			},
			error : function(data){
				alert("error:" + data);
			}
     });
}
function getDTime(DateState)
{
	var startDate=document.getElementById("startDate").value;
	var endDate=document.getElementById("endDate").value;
	//var DateState=ADateState;//document.getElementById("sDate").value;
	startDate= new Date(startDate.replace(/-/g, "/"));
	endDate= new Date(endDate.replace(/-/g, "/"));
	var days =endDate.getTime() - startDate.getTime();
	switch(parseInt(DateState))
	{
	case 1: return  parseInt(days / (1000 * 60 * 60 * 24));break;
	case 2: return  parseInt(days / (1000 * 60 * 60 ));break;
	case 3: return  parseInt(days / (1000 * 60 ));break;
		
	}
	return DateState;
}
function creatSelect(num)
{
	var s1=document.getElementById('s1');
	var s0=document.getElementById('s0');
	
	s1.innerHTML = "";
	s0.innerHTML = "";
	if(num==1)
	{
		for(var i=0;i<labelData.length;i++)
			{
				if(i!=0)
					s1.options.add(new Option(labelData[i],i)); 
				s0.options.add(new Option(labelData[i],i)); 
			}
	}
	else
	{
		for(var i=0;i<labelData2.length;i++)
		{
			if(i!=0)
				s1.options.add(new Option(labelData2[i],i)); 
			s0.options.add(new Option(labelData2[i],i)); 
		}
	}
}
function channelDate(value)
{
	switch(value)
	{
	case 1: creatSelect(1);  break;
	case 2:
	case 3: creatSelect(2);  break;
	}
}
function InitDateAnalysis(Rdata)
{
	var SDate=Rdata.split("&&");
	var str=SDate[0].substring(1,SDate[0].length-1);
	var info=SDate[1].split(",");
	var page=info[0]/10;
	if(page!=parseInt(page))
		page=parseInt(page)+1;
	maxpage=page;
	nowpage=info[1];
	document.getElementById("info").innerHTML="共"+info[0]+"条记录，当前第"+info[1]+"/"+page+"页，每页显示10条记录";
	
	var temp=str.split(", ");
	
	try{
	for(var i=0;i<10;i++)
	{
		 $("#c"+(i+1)).hide();   
		 $("#t"+(i+1)).hide();   
//			for(var s=0;s<7;s++)
//			{
//				document.getElementById("tt").rows[i+1].cells[s+1].innerHTML="";
//			}
	}
	for(var i=0;i<temp.length;i++)
	{
		temp[i]=temp[i].substring(1,temp[i].length-1);
		//alert(temp[i]);
		var data=temp[i].split(",");
		 $("#c"+(i+1)).show();
		 $("#t"+(i+1)).show(); 
		 document.getElementById("tt").rows[i+1].cells[1].innerHTML=data[1];
		 document.getElementById("tt").rows[i+1].cells[2].innerHTML=parseFloat(data[2]).toFixed(2);
		 document.getElementById("tt").rows[i+1].cells[3].innerHTML=parseFloat(data[4]).toFixed(4)+"%";
		 document.getElementById("tt").rows[i+1].cells[4].innerHTML=parseFloat(data[6]).toFixed(4)+"%";
		 document.getElementById("tt").rows[i+1].cells[5].innerHTML=parseFloat(data[3]).toFixed(2);
		 document.getElementById("tt").rows[i+1].cells[6].innerHTML=parseFloat(data[5]).toFixed(4)+"%";
		 document.getElementById("tt").rows[i+1].cells[7].innerHTML=StrToDate(parseInt(data[7]));
//		for(var s=0;s<7;s++)
//		{
//			if(s>0)
//			{
//				if(s==1)
//					data[s+1]=(parseInt(data[s+1]));
//				if(s==4)
//					data[s+1]=parseFloat(data[s+1]).toFixed(4)
//				if(s==2||s==3||s==5)
//					data[s+1]=parseFloat(data[s+1]).toFixed(4)+"%";
//				if(s==6)
//					data[s+1]=StrToDate(parseInt(data[s+1]));
//					//StrToDate(3666);
//			}
//				
//			document.getElementById("tt").rows[i+1].cells[s+1].innerHTML=data[s+1];
//		}
		
	} }catch(e){
        alert(e.toString());
    }
}
function setCheckedArray()
{
	for(var i=0;i<10;i++)
		document.getElementById("c"+(i+1)).checked=false;
}
function getCheckedArray()
{
	var str=new Array();
	for(var i=0;i<10;i++)
		if(document.getElementById("c"+(i+1)).checked)
			str.push(document.getElementById("tt").rows[i+1].cells[1].innerHTML);
	return str;
}
function getCheckedNum()
{
	var n=0;
	for(var i=0;i<10;i++)
		if(document.getElementById("c"+(i+1)).checked)
			n++;
	return n;	
}
function getChecked()
{
	var str="";
	var n=true;
	for(var i=0;i<10;i++)
		if(document.getElementById("c"+(i+1)).checked)
		{
			if(n)
			{
				str+="&name=";
				n=false;
			}
			else str+=",";
			str+=document.getElementById("tt").rows[i+1].cells[1].innerHTML;
		}
	return str;
}
function getChannel()
{
	return  "&channel="+toggle;
}
function getContrast()
{
	return  "&Contrast="+document.getElementById("s0").value;
}
function getDateState()
{
	return  "&DateState="+ADateState;//document.getElementById("sDate").value;
}
function getDateValue()
{
	return "startDate="+document.getElementById("startDate").value+"&endDate="+document.getElementById("endDate").value+
	"&select="+document.getElementById("s1").value;
}
function getDate(url)
{
	//alert(getDTime());
	if(getDTime(ADateState)*getCheckedNum()>24*60*4)
	{
		alert("选择区域过大请重新选择");
		return ;
	}
	if(document.getElementById("s1").value==0)
		return;
	var ajaxData = getDateValue()+getChecked()+getChannel()+getContrast()+getDateState();
	//alert(ajaxData);
	var flag=0;
	if(document.getElementById("s0").value==0)
	{
		flag=1;
	}
	else
	{
		var num=getCheckedNum();
		if(num==0)
		{
			alert("请选择数据");
			return ;
		}
		else if(num>5)
		{
			alert("最多选择五条数据");
			return ;
		}
		flag=2;	
	}
	$.ajax({
        type: "POST",
        url: url+"date.do",
        dataType: "text",
        data:ajaxData, 
        async:false,  
        success:function(data,status){
       	 //alert(data);
       	 if(data!="no")
       			 DateAnalysis(data,flag);
			},
			error : function(data){
				alert("error:" + data);
			}
    });
	
	//alert(ajaxData);
	
}
//var scrollFunc = function (e) {
//    var direct = 0;
//    ee = e || window.event;
//
//    if (e.wheelDelta) {//IE/Opera/Chrome  
//        direct = e.wheelDelta;
//    } else if (e.detail) {//Firefox  
//        direct = e.detail;
//    }
//    
//    if(nowTime==0)
//    	return ;
//    document.getElementById('chart1').innerHTML = "";
//    var Sdata = new Array();
//    var data_max=0;
//	var data_max2=0;
//    var x=new Array();
////    var cx=parseInt(AWidth/nowCTime);
////    var px=parseInt(ActionX/nowCTime);
//    if(direct<0)
//    {
//    	nowCTime/=2;
//    	if(nowCTime==0)nowCTime=1;
//    }
//    else
//    {
//    	nowCTime*=2;
//    	if(	nowCTime>=nowTime)nowCTime=nowTime;
//    	
//    }
//    for(var i=0;i<nowCTime;i++)
//    {
//    	x.push(nowX[i]);
//    }
//    for(var a=0;a<nowDate.length;a++)
//	{
//		var data=new Array();
//		for(var i=0;i<x.length;i++)
//			data[i]=0;
//		var str=nowDate[a].substring(1,nowDate[a].length-1);
//		var temp=str.split(", ");
//		for(var i=0;i<temp.length;i++)
//			{
//				var t=temp[i].split("=");
//				var date=t[0];
//				var value=t[1];
//				
//				for(var s=0;s<x.length;s++)
//				{
//					if(x[s]==date)
//					{
//						data[s]=parseFloat(value);
//						if(a==0||nowFlag==1)
//						{
//							if(data_max<parseFloat(value))
//								data_max=value;
//						}
//						else
//						{
//							if(data_max2<parseFloat(value))
//								data_max2=value;
//						}
//						break;
//					}
//				}
//			}
//		Sdata[a]=data;
//	}
//	changeX(nowFlag,x,nowTime);
//	if(data_max<10)
//		data_max=parseFloat(data_max*1.2).toFixed(4);
//	else
//		data_max=parseInt(data_max*1.2);
//	if(data_max2<10)
//		data_max2=parseFloat(data_max2*1.2).toFixed(4);
//	else
//		data_max2=parseInt(data_max2*1.2);
//	var x_label = ""; //X轴标题
//	//var line_title = ["总趋势图"]; //曲线名称
//	if(data_max==0)
//		data_max=1;
//	//alert(Sdata);
//	if(nowFlag==1)
//		j.jqplot.diagram.base("chart1", Sdata, getCheckedArray(), "收视率走势图(数据来源：某广电公司)", x, x_label, y_label, data_max, 1);
//	else if(nowFlag==2)
//		z.jqplot.diagram.base("chart1", Sdata, [nowY_label,nowY2_label], "收视率走势图(数据来源：某广电公司)", x, x_label, nowY_label,nowY2_label, data_max, data_max2, 1);
//}
function getNowDate()
{
	
	var myDate = new Date();
	var startDate= document.getElementById("startDate");
	var endDate= document.getElementById("endDate");
	startDate.value=DateToStr(new Date(new Date().getTime()-1000 * 60 * 60 * 24*7),1);
	endDate.value=DateToStr(new Date(new Date().getTime()-1000 * 60 * 60 * 24),1);
	
}
function DateAnalysis(Rdata,Rflag)
{
	
	var data_max=0;
	var data_max2=0;
	//alert(Rdata);
	document.getElementById('chart1').innerHTML = "";
	
	var startDate= document.getElementById("startDate").value;
	var endDate= document.getElementById("endDate").value;
	var DateState=ADateState;//document.getElementById("sDate").value;
	
	var x=new Array();
	var time;
	
	switch(DateState)
	{
	case 1:startDate=startDate.split(" ");
			startDate=startDate[0];
			endDate=endDate.split(" ");
			endDate=endDate[0];
			startDate= new Date(startDate.replace(/-/g, "/"));
			endDate= new Date(endDate.replace(/-/g, "/"));
			var days =endDate.getTime() - startDate.getTime();
			time = parseInt(days / (1000 * 60 * 60 * 24));
			for(var i=0;i<=time;i++)
				x.push(DateToStr(new Date(startDate.getTime()+i*(1000 * 60 * 60 * 24)),1));
			break;
	case 2:startDate= new Date(startDate.replace(/-/g, "/"));
			endDate= new Date(endDate.replace(/-/g, "/"));
			var days =endDate.getTime() - startDate.getTime();
			time = parseInt(days / (1000 * 60 * 60 ));
			for(var i=0;i<=time;i++)
				x.push(DateToStr(new Date(startDate.getTime()+i*(1000 * 60 * 60 )),2));
			break;
	case 3:startDate= new Date(startDate.replace(/-/g, "/"));
			endDate= new Date(endDate.replace(/-/g, "/"));
			var days =endDate.getTime() - startDate.getTime();
			time = parseInt(days / (1000 * 60 ));
			for(var i=0;i<=time;i++)
				x.push(DateToStr(new Date(startDate.getTime()+i*(1000 * 60)),3));
			 break;
	}
	nowState=nowState;
	nowTime=time+1;
	nowCTime=time+1;
	nowX=x;
	nowFlag=Rflag;
	if(DateState==1)
	{
		 y_label =labelData[document.getElementById("s1").value]; //Y轴标题
		 y2_label =labelData[document.getElementById("s0").value]; 
	}
	else
	{
		 y_label =labelData2[document.getElementById("s1").value]; //Y轴标题
		 y2_label =labelData2[document.getElementById("s0").value]; 
	}
	nowY_label=y_label;
	nowY2_label=y2_label;
	var Sdata = new Array();
	
	var S_P=Rdata.split("&&");
	//nowDate=S_P;
	
	for(var a=0;a<S_P.length;a++)
	{
		var data=new Array();
		for(var i=0;i<x.length;i++)
			data[i]=0;
		var str=S_P[a].substring(1,S_P[a].length-1);
		var temp=str.split(", ");
		for(var i=0;i<temp.length;i++)
			{
				var t=temp[i].split("=");
				var date=t[0];
				var value=t[1];
				if(a<S_P.length/2||Rflag==1)
				{
					if(data_max<parseFloat(value))
						data_max=value;
				}
				else
				{
					if(data_max2<parseFloat(value))
						data_max2=value;
				}
				for(var s=0;s<x.length;s++)
				{
					if(x[s]==date)
					{
						data[s]=parseFloat(value);
						break;
					}
				}
			}
		Sdata[a]=data;
		
	}
	
	changeX(DateState,x,time);
	if(data_max<10)
		data_max=parseFloat(data_max*1.2).toFixed(4);
	else
		data_max=parseInt(data_max*1.2);
	if(data_max2<10)
		data_max2=parseFloat(data_max2*1.2).toFixed(4);
	else
		data_max2=parseInt(data_max2*1.2);
	
	
	var x_label = ""; //X轴标题
	//var line_title = ["总趋势图"]; //曲线名称
	if(data_max==0)
		data_max=1;
	var checked=getCheckedArray();
	var Y_label = new Array();
	for(var i=0;i<checked.length;i++)
	{
		Y_label[i*2]=checked[i]+":"+y_label;
		Y_label[i*2+1]=checked[i]+":"+y2_label;
	}
	//alert(Sdata);
	if(Rflag==1)
		j.jqplot.diagram.base("chart1", Sdata, checked, "收视率走势图(数据来源：某广电公司)", x, x_label, y_label, data_max, 1);
	else if(Rflag==2)
	{
		var Ddata=new Array();
		for(i=0;i<Sdata.length/2;i++)
		{
			Ddata[i*2]=Sdata[i];
			Ddata[i*2+1]=Sdata[Sdata.length/2+i];
		}
		//alert(Ddata);	
		z.jqplot.diagram.base("chart1", Ddata, Y_label, "收视率走势图(数据来源：某广电公司)", x, x_label, y_label,y2_label, data_max, data_max2, 1);
	}
		
}
function changeInputDate()
{
	try{
	var startDate=document.getElementById('startDate');
	var endDate=document.getElementById('endDate');

	if(getDTime(3)<1440)
	{
		$("#d1").removeClass("active");
		$("#d2").removeClass("active");
		$("#d3").removeClass("forbid");
		$("#d1").addClass("forbid");
		$("#d3").addClass("active");
		ADateState=3;
		XDateState=1;
	}
	else
	{
		if(ADateState==3)
		{
			$("#d3").removeClass("active");
			$("#d2").removeClass("active");
			$("#d1").removeClass("forbid");
			$("#d3").addClass("forbid");
			$("#d1").addClass("active");
			ADateState=1;
			XDateState=3;
		}
	
	}
}catch(e){
    alert(e.toString());
}
}
function changeDate(num)
{
	if(XDateState==num)return 0;
	if(ADateState==num)return 0;
	if(num==1)
	{
		$("#d2").removeClass("active");
		$("#d1").addClass("active");
		channelDate(1);
	}else if(num==2)
	{
		$("#d1").removeClass("active");
		$("#d3").removeClass("active");
		$("#d2").addClass("active");
		
		channelDate(2);
	}else if(num==3)
	{
		$("#d2").removeClass("active");
		$("#d3").addClass("active");
		channelDate(3);
	}
	ADateState=num;

	
}
function changeX(DateState,x,time)
{
	var s=0;
	var max=parseInt(time/15);

	for(var i=0;i<x.length;i++)
		{
			if(!s)
			{
				if(DateState==2)
					x[i]=x[i].substring(5,x[i].length);
				else if(DateState==3)
					x[i]=x[i].substring(8,x[i].length);
			}	
			else
				x[i]="";
			if(s>=max)
				s=0;
			else
				s++;
		}
}
function getX(obj){ 
	var parObj=obj; 
	var left=obj.offsetLeft; 
	while(parObj=parObj.offsetParent){ 
	left+=parObj.offsetLeft; 
	} 
	return left; 
	} 
	function getY(obj){ 
	var parObj=obj; 
	var top=obj.offsetTop; 
	while(parObj = parObj.offsetParent){ 
	top+=parObj.offsetTop; 
	} 
	return top; 
	} 
function DisplayCoord(event,obj){ 
//	var top=getY(obj); 
//	var left=getX(obj); 
//	var x=event.clientX-left+document.documentElement.scrollLeft ; 
//	var y=event.clientY-top+document.documentElement.scrollTop; 
//	ActionX=x;
} 
function StrToDate(Str)
{
	var HH=parseInt(Str/(60*60));
	var MM=parseInt((Str-HH*60*60)/60);
	var SS=Str-HH*60*60-MM*60;
	if(MM<10)
		MM="0"+MM;
	if(HH<10)
		HH="0"+HH;
	if(SS<10)
		SS="0"+SS;
	return (HH+":"+MM+":"+SS)
}
//日期转Str+HH:MM
function DateToStr(d,num) {
	var mths = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];  
	//var d = new Date(date.getTime());
	var dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate().toString();
	var mmm = mths[d.getMonth()];
	var yyyy = d.getFullYear().toString(); //2011
	var HH=d.getHours();
	var MM=d.getMinutes();
	var SS=d.getSeconds();
	if(MM<10)
		MM="0"+MM;
	if(HH<10)
		HH="0"+HH;
	if(SS<10)
		SS="0"+SS;
	switch(num)
	{
	case 1:return  yyyy+"-"+mmm+"-"+dd;break;
	case 2:return  yyyy+"-"+mmm+"-"+dd+" "+HH+":00";break;
	case 3:return  yyyy+"-"+mmm+"-"+dd+" "+HH+":"+MM;break;
	case 4:return  HH+":"+MM+":"+SS;break;
	}
	return num;
}