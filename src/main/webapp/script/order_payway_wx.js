var monitor;
$(function(){
     monitor = setInterval("monitorOrder();", 2000);
});

function monitorOrder(){
	 $.ajax({
         type: "POST",
         url: "/order/monitor/wx",
         data: {
    	 	orderId : $("#orderId").val()
     	 },
         dataType: "json",
         success: function(data){
     		 var isSuccess = data.isSuccess;
     		 if(isSuccess){
     			clearInterval(monitor);
     			//跳转页面
     			window.location.href = "/order/paysult?orderId=" + $("#orderId").val();
     		 }
         }
     });
}
