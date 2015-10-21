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
		title :  $('#title').val(),
		description :  $('#description').val(),
		picurl : $('#picurl').val(),	
		url : $('#linkurl').val(),
		id : $('#id').val()
	};
	
	if ($('#mid').val()) {
		
		$.post(serverurl + 'servlet/PicMeg', posttele, function(result) {
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
			title :  $('#titles').val(),
			description :  $('#descriptions').val(),
			picurl : $('#picurls').val(),	
			url : $('#linkurls').val()
		};
	
	if ($('#mid').val()) {
		$.post(serverurl + 'servlet/PicMeg', posttele, function(result) {
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
        


