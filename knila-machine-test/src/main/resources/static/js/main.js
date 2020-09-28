$(document).ready(function () {
	$('#viewCart').hide();
	$('#bth-new-search').hide();
	$('#bth-view-cart').hide();
	$('#bth-payment').hide();
	$('#bth-back').hide();
	/////
	var rowCountSearchResulttable = document.getElementById('searchResulttable').rows.length;
	if(rowCountSearchResulttable>1){
		$('#searchResulttable').show();
	}else{
		$('#searchResulttable').hide();
	}
	//////
	var rowCountViewCarttable= document.getElementById('viewCarttable').rows.length;
	if(rowCountSearchResulttable>1){
		$('#viewCarttable').show();
	}else{
		$('#viewCarttable').hide();
	}
	//////
    $("#search-form").submit(function (event) {
        event.preventDefault();
    	$('#bth-search').hide();
    	$('#bth-new-search').show();
        fire_ajax_search_submit();
    });
    /*
    $("#view-cart-form").submit(function (event) {
    	
    }); */
    
});

$(document).on('click','#bth-view-cart',function(){
	$('#billSearch').hide();
  	$('#bth-view-cart').hide();
	$('#viewCart').show();
	$('#bth-payment').show();
	$('#bth-back').show();
    event.preventDefault();
    fire_ajax_view_cart_submit();

});
$(document).on('click','.addToCartButton',function(){
	$('#bth-view-cart').show();
	var id=this.id.slice(-1);
	$('#'+this.id).hide();
	$('#remove'+id).show();
	 var table = document.getElementById('searchResulttable');
	 var objCells = table.rows.item(Number(id) + 1).cells;
		var addtoCart = {}
	 for (var j = 0; j < objCells.length; j++) {
		 switch(j){
		    case 0:
			  addtoCart["payerName"] = objCells.item(j).innerHTML;
			    break;
		    case 1:
				  addtoCart["payerAddress"] = objCells.item(j).innerHTML;
				    break;
		    case 2:
				  addtoCart["dueDate"] = objCells.item(j).innerHTML;
				    break;	
		    case 3:
				  addtoCart["accountNumber"] = objCells.item(j).innerHTML;
				    break;	
		    case 4:
				  addtoCart["amount"] = objCells.item(j).innerHTML;
				    break;		
		    case 6:
				  addtoCart["billId"] = objCells.item(j).innerHTML;
				    break;
			  default:
			   
		 }
       	
     }
		
	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/addCart",
	        data: JSON.stringify(addtoCart),
	        dataType: 'json',
	        timeout: 600000,
	        success: function (data) {
	            console.log("SUCCESS : ", data);	                         	                          
	        },
	        error: function (e) {

	            console.log("ERROR : ", e);

	        }
	    });			
});


$(document).on('click','.remove',function(){
	var id=this.id.slice(-1);
	$('#'+this.id).hide();
	$('#addToCartButton'+id).show();
	 var table = document.getElementById('searchResulttable');
	 var objCells = table.rows.item(Number(id) + 1).cells;
		var billId ;
	 for (var j = 0; j < objCells.length; j++) {
		 switch(j){		
		    case 6:
		    	billId= objCells.item(j).innerHTML;
				    break;
			  default:		   
		 }
       	
     }
	 
	 $.ajax({ 
	        type: "DELETE",	       
	        url: "/removeCart/"+billId,	      
	        timeout: 600000,
	        success: function (data) {
	            console.log("SUCCESS : ", data);	                                                  
	        },
	        error: function (e) {

	            console.log("ERROR : ", e);

	        }
	    });	
	
});



$(document).on('click','.vremove',function(){
	 if (confirm('Are you sure?')) {
	
	var id=this.id.slice(-1);
	$('#'+this.id).hide();
	$('#vaddToCartButton'+id).show();
	 var table = document.getElementById('viewCarttable');
	 var objCells = table.rows.item(Number(id) + 1).cells;
		var billId ;
	 for (var j = 0; j < objCells.length; j++) {
		 switch(j){		
		    case 6:
		    	billId= objCells.item(j).innerHTML;
				    break;
			  default:		   
		 }
       	
     }
	 
	 $.ajax({ 
	        type: "DELETE",	       
	        url: "/removeCart/"+billId,	      
	        timeout: 600000,
	        success: function (data) {
	            console.log("SUCCESS : ", data);	
	            $('#viewCarttable').hide();
	       	 fire_ajax_view_cart_submit();
	        },
	        error: function (e) {
	            console.log("ERROR : ", e);
	        }
	    });	
	
	 }
});



function fire_ajax_search_submit() {

    var search = {}
    search["accountNumber"] = $("#accountNumber").val();
    search["payerAddress"] = $("#payerAddress").val();
    search["payerName"] = $("#payerName").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/searchBill",
        data: JSON.stringify(search),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $("#searchResulttable").find($("tr")).slice(1).remove()
              var responseData = ''; 

            if(data.length==0){
            	$('#searchResulttable').hide();
            	alert("No Data Found") ;
            }else{
            	$('#searchResulttable').show();
            }
                    
            $.each(data, function (key, value) { 

            	responseData += '<tr id=row'+key+'>'; 
            	responseData += '<td id="payerName'+key+'">' +  
                    value.payerName + '</td>'; 

            	responseData += '<td id="payerAddress'+key+'">' + 
                    value.payerAddress + '</td>'; 

            	responseData += '<td id="dueDate'+key+'">' +  
                    value.dueDate + '</td>'; 

            	responseData += '<td id="accountNumber'+key+'">' + 
                    value.accountNumber + '</td>'; 
                
            	responseData += '<td id="amount'+key+'">' + 
                value.amount + '</td>'; 
           	
            	responseData += '<td id="action'+key+'"> <input id="addToCartButton'+key+'" class="addToCartButton" type="submit" value="Add to Cart"/><input id="remove'+key+'" class="remove" type="submit" value="Remove" hidden/></td>'; 
            	
            	responseData += '<td id="billId'+key+'" hidden>' + 
                value.billId + '</td>'; 
  
            	 responseData += '</tr>'; 
                   }); 

            $('#searchResulttable').append(responseData); 
        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });
    
}

function fire_ajax_view_cart_submit() {
    $.ajax({
        type: "GET",
        url: "/viewCart",
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            
            $("#viewCarttable").find($("tr")).slice(1).remove()
            var responseData = ''; 

          if(data.length==0){
          	$('#viewCarttable').hide();
          	alert("No Data Found") ;
          }else{
          	$('#viewCarttable').show();
          }
          var item = 0; 
          var amt=0;
          $.each(data, function (key, value) { 
        	  item++;
        	  amt=amt+Number(value.amount);
          	responseData += '<tr id=row'+key+'>'; 
          	responseData += '<td id="payerName'+key+'">' +  
                  value.payerName + '</td>'; 

          	responseData += '<td id="payerAddress'+key+'">' + 
                  value.payerAddress + '</td>'; 

          	responseData += '<td id="dueDate'+key+'">' +  
                  value.dueDate + '</td>'; 

          	responseData += '<td id="accountNumber'+key+'">' + 
                  value.accountNumber + '</td>'; 
              
          	responseData += '<td id="amount'+key+'">' + 
              value.amount + '</td>'; 
         	
          	responseData += '<td id="action'+key+'"><input id="vremove'+key+'" class="vremove" type="submit" value="Remove" /></td>'; 
          	
          	responseData += '<td id="billId'+key+'" hidden>' + 
              value.billId + '</td>'; 

          	responseData += '</tr>'; 
            
                 }); 
        responseData += '<tr id=rowlast>'; 
      	responseData += '<td id="items">' + 
      	item + ' Items ';  
      	responseData += '<td id="payerName"> </td>' ;
      	responseData += '<td id="payerAddress"> </td>' ; 
      	responseData += '<td id="dueDate"> </td>'  ;
      	responseData += '<td id="amt">' + 
      	amt + ' </td>'; 
      	responseData += '<td id="action"> </td>' ;
       	responseData += '</tr>'; 
          $('#viewCarttable').append(responseData);           
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
    
}
