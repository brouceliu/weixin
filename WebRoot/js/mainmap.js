var serverurl="http://wx.wushang.com/Txwx/";

function addPicMeg() {
	
	
		$("#Add").dialog("open").dialog("setTitle", "添加");
		
		
}



function updateUser() {
	var row = $('#dg').datagrid('getSelected');
	
	if (row) {
		$("#dlg").dialog("open").dialog("setTitle", "编辑");
		$("#fm").form("load", row);
		}

}


function editeDate() {
	var posttele = {
		action : 'update',
		mid : $('#mid').val(),
		context : $('#context').val(),
		id : $('#id').val()
	};
	
	if ($('#id').val()) {
		
		$.post(serverurl + 'servlet/PicMap', posttele, function(result) {
			if (result.msg == "success") {
				$.messager.show({ // show error message
					title : 'success',
					msg : '修改成功'
				});
				$("#dlg").dialog("close");        
				$('#dg').datagrid('reload');// reload the user data
			} else {
				$.messager.show({ // show error message
					title : 'Error',
					msg : '错误'
				});
			}
		}, 'json');
	}

}


function addDate() {
	var posttele = {
			action : 'add',
			mid : $('#mids').val(),
			context :  $('#contexts').val(),
			
		};
	
	if ($('#mids').val()) {
		$.post(serverurl + 'servlet/PicMap', posttele, function(result) {
			if (result.msg == "success") {
				$.messager.show({ // show error message
					title : 'success',
					msg : '增加成功'
				});
				$("#Add").dialog("close");        
				$('#dg').datagrid('reload');// reload the user data
			} else {
				$.messager.show({ // show error message
					title : 'Error',
					msg : '错误'
				});
			}
		}, 'json');
	}

}
        


