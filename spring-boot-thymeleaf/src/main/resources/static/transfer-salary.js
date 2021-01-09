/**
 * Created by User on 08/01/2021.
 */
$(document).ready(function () {

    $("#accountId").change(function () {
        //id_courses
        var accId= $('#accountId').val();
       if(accId!='') {
           $.ajax({
               url: 'get-list',
               data: {instId: $("#accountId").val()},
               success: function (data) {
                   console.log(data);
                   $('#currency').val('Tk');
                   $('#avBalance').val(data.availableAmt);
                   $('#clientAccName').val(data.accountName);

               }
           });
       }else{
           $('#currency').val('');
           $('#avBalance').val('');
           $('#clientAccName').val('');
        }

    });

});