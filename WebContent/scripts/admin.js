var admin = {
		'userTree' : '#userTree',
		'userDetails': {
			'form' : '#userForm',
			'userId': '#userId',
			'userName' : '#userName',
			'password' : '#password',
			'parentId' : '#parentId',
			'saveBtn'  : '#userDetailsSave',
			'Opt' : ["Y","N"],
			'adminPriv' : '#admin',
			'resourcePriv':'#resource'
		}
		
}

//initialization
$(admin.userDetails.saveBtn).jqxButton();
$(admin.userDetails.userName).jqxInput();
$(admin.userDetails.userId).jqxInput();
$(admin.userDetails.password).jqxInput();
$(admin.userDetails.parentId).jqxInput();
$(admin.userDetails.adminPriv).jqxDropDownList({ source:  admin.userDetails.Opt, placeHolder: "Select AdminPrivilege:",selectedIndex: 1, width:'100%', height: '23'});
$(admin.userDetails.resourcePriv).jqxDropDownList({ source:  admin.userDetails.Opt, placeHolder: "Select ResourcePriv:",selectedIndex: 0, width:'100%', height: '23'});

//clicks

$( admin.userDetails.form).on('submit', function (event) {
	event.preventDefault();
	var request = {};
	request['userid'] = $(admin.userDetails.userId).val();
	request['username'] = $(admin.userDetails.userName).val();
	request['parentid'] = $(admin.userDetails.parentId).val();
	if($( admin.userTree).jqxTreeGrid('getSelection')[0] != undefined){
		var selection = $( admin.userTree).jqxTreeGrid('getSelection');
		//console.log(selection);
		if(selection[0].Password != $(admin.userDetails.password).val()){
			request['newpassword'] = $(admin.userDetails.password).val();
		}else{
			request['oldpassword'] = $(admin.userDetails.password).val();
		}
	}
	else{
		request['password'] = $(admin.userDetails.password).val();
	}
	var adminItem = $(admin.userDetails.adminPriv).jqxDropDownList('getSelectedItem'); 
	request['admin'] = adminItem.value;
	var resourceItem = $(admin.userDetails.resourcePriv).jqxDropDownList('getSelectedItem'); 
	request['resource'] = resourceItem.value;
	console.log(request);
	
	if($( admin.userTree).jqxTreeGrid('getSelection')[0] != undefined){
	$.get('/TalentSearch/jsp/data/update-user.jsp',request).done(function(){
		$( admin.userTree).jqxTreeGrid('updateBoundData');
	});
	}else{
		$.get('/TalentSearch/jsp/data/insert-user.jsp',request).done(function(){
			$( admin.userTree).jqxTreeGrid('updateBoundData');
		});
	}
	
	$( admin.userDetails.form)[0].reset();
	
	
});

//userTree
function loadUsers(){
var userSource =
{
    dataType: "json",
    dataFields: [
        { name: 'UserId', type: 'Number' },
        { name: 'ParentId', type: 'Number' },
        { name: 'UserName', type: 'String' },
        { name: 'Password', type: 'String'},
        { name: 'Admin', type: 'String'},
        { name: 'Resource', type: 'String'},
        
    ],
    hierarchy:
    {
        keyDataField: { name: 'UserId' },
        parentDataField: { name: 'ParentId' }
    },
    id: 'UserId',
    
   url: '/TalentSearch/jsp/data/get-userdata.jsp'
};

var DataAdapter = new $.jqx.dataAdapter(userSource);

$( admin.userTree).jqxTreeGrid(
	    {
	        width:'100%',
	        source: DataAdapter,
	        icons: function (rowKey, dataRow) {
	                return '../images/user.png';  
	        },
	        
	        columns: [
	          { text: 'UserName', dataField: 'UserName'}
	        ],
	        
	        showToolbar: true,
	        renderToolbar: function(toolBar)
	        {
	            var container = $("<div style='overflow: hidden; position: relative; height: 100%; width: 100%;'></div>");
	            var buttonTemplate = "<div style='float: left; padding: 3px; margin: 2px;'><div style='margin: 4px; width: 16px; height: 16px;'></div></div>";
	            var addButton = $(buttonTemplate);
	            var deleteButton = $(buttonTemplate);
	            container.append(addButton);
	            container.append(deleteButton);
	            toolBar.append(container);
	            addButton.jqxButton({cursor: "pointer", enableDefault: false, disabled: false, height: 25, width: 25 });
	            addButton.find('div:first').addClass(('jqx-icon-plus'));
	            addButton.jqxTooltip({ position: 'bottom', content: "Add User"});
	            deleteButton.jqxButton({ cursor: "pointer", disabled: false, enableDefault: false,  height: 25, width: 25 });
	            deleteButton.find('div:first').addClass(('jqx-icon-delete'));
	            deleteButton.jqxTooltip({ position: 'bottom', content: "Remove User"});

	            $( admin.userTree).on('rowSelect', function (event) {

	            	$(admin.userDetails.userName).val(event.args.row.UserName);
	            	$(admin.userDetails.password).val(event.args.row.Password);
	            	$(admin.userDetails.userId).val(event.args.row.UserId);
	            	$(admin.userDetails.parentId).val(event.args.row.ParentId);
	            	$(admin.userDetails.adminPriv).jqxDropDownList('selectItem', event.args.row.Admin);
	            	$(admin.userDetails.resourcePriv).jqxDropDownList('selectItem', event.args.row.Resource);
	            });
	            
	            addButton.click(function (event) {
	            	
	            	$( admin.userTree).jqxTreeGrid('clearSelection');
	            	$( admin.userDetails.form)[0].reset();
	            });
	           
	            //not implemented
	            deleteButton.click(function () {
	            	
	            	var item = $( admin.userTree).jqxTreeGrid('getSelection');
	            	$.get('/TalentSearch/jsp/data/delete-user.jsp?userid='+item[0].UserId).done(function(){
	        			$( admin.userTree).jqxTreeGrid('updateBoundData');
	        			
	        			$( admin.userDetails.form)[0].reset();
	        		});
	        });

	        }
	    });

}


$(document).ready(function () {
	
	loadUsers();
});
