var profile = {
		'details': {
			'form' : '#userForm',
			'id' : '#userId',
			'name' : '#userName',
			'designation' : '#designation',
			'email' : '#emailId',
			'mobile' : '#mobileNo',
			'experience' : '#experience',
			'userDetailsSave' : '#userDetailsSave',
			'assignBtn' : '#assignTag',
			'removeBtn' : '#removeTag',
			'assignedTags' : '#assignedTags',
			'availableTags' : '#availableTags',
			'addTagBtn'  : '#addTag',
			'editTagBtn' :'#editTag',
			'addPopUpWin' : '#addPopUp',
			'editPopUpWin' : '#editPopUp',
			'addPopUp' : {
				'tag':'#addTagField',
				'saveBtn':'#tagSaveBtn'
			},
			'editPopUp' : {
				'tag':'#editTagField',
				'saveBtn':'#tagEditBtn'
			}
		}
		
		
}

$(profile.details.userDetailsSave).jqxButton();
$(profile.details.assignBtn).jqxButton();
$(profile.details.removeBtn).jqxButton();
$(profile.details.addTagBtn).jqxButton();
$(profile.details.editTagBtn).jqxButton();
$(profile.details.addPopUp.saveBtn).jqxButton();
$(profile.details.editPopUp.saveBtn).jqxButton();
$( profile.details.addPopUpWin).jqxWindow({
	   position: 'center',
	   height: 100,
	   width: 600,
	   isModal: true,
	   autoOpen:false
	   
	});

$( profile.details.editPopUpWin).jqxWindow({
	   position: 'center',
	   height: 100,
	   width: 600,
	   isModal: true,
	   autoOpen:false
	   
	});

$(profile.details.addPopUp.tag).jqxInput();
$(profile.details.editPopUp.tag).jqxInput();
$(profile.details.id).jqxInput();
$(profile.details.name).jqxInput();
$(profile.details.designation).jqxInput();
$(profile.details.email).jqxInput();
$(profile.details.mobile).jqxInput();
$(profile.details.experience).jqxInput();



//clicks
$(  profile.details.assignBtn).on('click', function (event) {
	event.preventDefault();
	var selectedTags =  $(profile.details.availableTags).jqxListBox('getSelectedItems'); 
	for(var i= 0;i<selectedTags.length;i++){
		var object = {}
		object['label'] = selectedTags[i].label;
		object['value'] = selectedTags[i].value;
	$(profile.details.availableTags).jqxListBox('removeItem', selectedTags[i].value); 
	$(profile.details.assignedTags).jqxListBox('addItem', object); 
	
	}
	$(profile.details.availableTags).jqxListBox('clearSelection');
});


$( profile.details.removeBtn).on('click', function (event) {
	event.preventDefault();
	var selectedTags =  $(profile.details.assignedTags).jqxListBox('getSelectedItems'); 
	//console.log($(manageUsers.userDetails.assignedprojects).jqxListBox('getSelectedItems'));
	for(var i= 0;i<selectedTags.length;i++){
		var object = {}
		object['label'] = selectedTags[i].label;
		object['value'] = selectedTags[i].value;
	$(profile.details.assignedTags).jqxListBox('removeItem', selectedTags[i].value); 
	$(profile.details.availableTags).jqxListBox('addItem', object); 
	
	}
	$(profile.details.assignedTags).jqxListBox('clearSelection');
});


$( profile.details.userDetailsSave).on('click', function (event) {
	event.preventDefault();
	var request = {};
	request['username'] = $(profile.details.name).val();
	request['userid'] = $(profile.details.id).val();
	request['designation'] = $(profile.details.designation).val();
	request['email'] = $(profile.details.email).val();
	request['mobile'] = $(profile.details.mobile).val();
	request['experience'] = $(profile.details.experience).val();
	var tagArray = [];
	var assignedTag = $(profile.details.assignedTags).jqxListBox('getItems');
	for(var i=0;i<assignedTag.length;i++){
		tagArray.push(assignedTag[i].value);
	}
	request['tags'] = tagArray.toString();
	console.log(request);
	$.get('/TalentSearch/jsp/data/update-employee.jsp',request);
});

$( profile.details.addPopUp.saveBtn).on('click', function (event) {
	event.preventDefault();
	var tag = $(profile.details.addPopUp.tag).prop("value");
	console.log(tag);
	$.post( '/TalentSearch/jsp/data/insert-tag.jsp?tag='+tag).done(function(){
		createUserData();
		
	});
	$( profile.details.addPopUpWin).jqxWindow('hide');
	
});

$( profile.details.editPopUp.saveBtn).on('click', function (event) {
	event.preventDefault();
	
});

$( profile.details.addTagBtn).on('click', function (event) {
	event.preventDefault();
	$( profile.details.addPopUpWin).jqxWindow('open');
});
//functions

function createUserData(){
	$.get('/TalentSearch/jsp/data/get-employeedata.jsp',function(dat){
	var response = JSON.parse(dat);
		var id = response[0].Id;
		$(profile.details.id).val(response[0].Id);
		$(profile.details.name).val(response[0].Name);
		$(profile.details.designation).val(response[0].Designation);
		$(profile.details.email).val(response[0].MailId);
		$(profile.details.mobile).val(response[0].MobileNo);
		$(profile.details.experience).val(response[0].Experience);
	  
	
	var available = [];
	var assigned = [];
	$.post( '/TalentSearch/jsp/data/get-tags.jsp?id='+id, function( data ) {
		  var response = JSON.parse(data);
		 // console.log(response);
		  available = response[0].available;
		  assigned = response[0].assigned;
		  
		  if(assigned[0] == ""){
			  assigned = [];
			  
		  }
		  $(profile.details.availableTags).jqxListBox({
       	 
       	 allowDrop: true,
       	 allowDrag: true,
       	 multiple: true,
       	 filterable : true,
       	 source: available,
       	 width: '100%',
       	 height: 300
        });
        $(profile.details.assignedTags).jqxListBox({ 
       	 
       	 allowDrop: true, 
       	 allowDrag: true,
       	 multiple: true,
       	 source: assigned, 
       	 width: '100%', 
       	 height: 300
        });
		});
	});
}


$(document).ready(function () {
	
	createUserData();
	createAllTagBox();
	updateData(0);
});