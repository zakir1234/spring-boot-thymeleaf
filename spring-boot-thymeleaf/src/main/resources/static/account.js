$(function() {

			$('#bankId').change(function() {
				
				var bankId = $('#bankId').val();

				$.ajax({
					url : '/branches/by/' + bankId,
					success : function(data) {
						   $('#branchId').empty();			   
						   
				            $.each(data, function (key, value) {
				            					         				            	
				                $('#branchId').append('<option value="' + value.id + '">' + value.branchName + '</option>');
				                				                
				            });
					}
				});

			});

		});