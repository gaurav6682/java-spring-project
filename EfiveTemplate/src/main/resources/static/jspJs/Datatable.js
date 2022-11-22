$(function($) {
    var table = $("#paginatedTable").DataTable({
        "processing": true,
        "serverSide": true,
        "destroy": true,
        "lengthMenu": [[5,7,10], [5,7,10]],
        "displayLength": 5,
        "order": [[0, "asc"]],
        "StateSave": false,
        "searchable": true,
        "ajax": {
            "url": "./getData",
            "data": function(d) {
                d.extraMsg = "Hello Message";
            },
            "dataSrc": "userList"
        },
        "columns":[
            {
                "data": "0", "name": "productid", "title": "PRODUCT ID"
            },
            {
                "data": "1", "name": "product_name", "title": "PRODUCT NAME"
            },
            {
                "data": "2", "name": "DESCRIPTION", "title": "DESCRIPTION"
            },
            {
                "data": "3", "name": "CATEGORY", "title": "CATEGORY"
            },
            {
                "data": "4", "name": "PRICE", "title": "PRICE"
            },
            {
                "data": "5", "name": "DISCOUNT", "title": "DISCOUNT"
            },
            {
                "data": "6", "name": "IMAGE", "title": "IMAGE"
            },
            {
                "data": "7", "name": "ACTIVE", "title": "ACTIVE"
            },
            {
                "data":null, "className":"center",
            }
    
        ],
       columnDefs:[
        	{
    			'targets' : [ -1 ],
    			'orderable' : false
    		}, {
    			'className' : 'text-left',
    			'targets' : [ 1, 2 ]
    		}, {
    			targets : 6,
    			'orderable' : false,
    			render : function(data) {
    				return '<img src="' + data + '">';
    			}
    		},
        	{
        		targets: 8,
        		className:"text-center",
        		render : function(full) {
    				var editBtn = $("<a>").addClass("btn btn-icons btn-success dtbtn-padding edit-product").attr("href", "edit/" + full[0]).append($("<i>").addClass("fa fa-pencil").attr("aria-hidden", true));
    				var delBtn = $("<button>").addClass("btn btn-icons btn-danger mx-1 dtbtn-padding delete-product").attr("productid", full[0]).append($("<i>").addClass("fa fa-trash").attr("aria-hidden", true));
    				return editBtn.get(0).outerHTML + delBtn.get(0).outerHTML;											
        		}
        	}
        ]
    });
    
    $(document).on("click", ".delete-product", function() {
		var productid = $(this).attr("productid");
		swal({
			  title: "Are you sure?",
			  text: "Product data will be deleted permanently.!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  deleteProduct(productid);
				  $(this).closest("tr").remove();
			  }
			});
	});
    
    function deleteProduct(productid) {
    	$.ajax({
    		url : "/efive/delete/"+productid,
    		method : "post",
    		success : function(response) {
    			if(response != null && response != undefined && response != "") {
    				const serverResponse = response;
    				if(serverResponse.status == "200") {
    					swal("Success! Product has been deleted!", {
      				      icon: "success",
      				    });
    					table.ajax.reload();
    				}
    			}
    		},
    		error : function(reponse) {
    			console.log("Error: " + reponse);
    		}

    	});
    }
});

