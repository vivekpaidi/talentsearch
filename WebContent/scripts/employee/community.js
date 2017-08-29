var community = {
		'allTagBox' : '#allTagBox',
		'searchTagBox' :'#searchTagBox',
		'employeeTable' : '#employeeTable',
		'assignedTags' :[]
}

var tableData = [];
var reference = [];
var count = 0;
console.log(CustomMap);

function createAllTagBox(){
	$.post( '/TalentSearch/jsp/data/get-tags.jsp?id=-1', function( data ) {
		console.log(data);
		  var response = JSON.parse(data);
		  available = response[0].available;
		  
		  $(community.allTagBox).jqxListBox({
		       	 allowDrop: true,
		       	 allowDrag: true,
		       	 filterable : true,
		       	filterPlaceHolder:" search here",
		       	dragEnd: function (dragItem, dropItem) {
                    updateData(dragItem.label);
                },
		       	 source: available,
		       	 width: '100%',
		       	 height: '80%'
		        });
		  $(community.searchTagBox).jqxListBox({ 
		       	 
		       	 allowDrop: true, 
		       	 allowDrag: true,
		       	 multiple: true,
		       	 dragEnd: function (dragItem, dropItem) {
                    removeData(dragItem.label);
                },
		       	 source: community.assignedTags, 
		       	 width: '100%', 
		       	 height: '60%'
		        });
	});
}

function updateData(tag){
	if(tag == 0){
		$.get('/TalentSearch/jsp/data/get-allemployeedetails.jsp',function(data){
			 var response = JSON.parse(data);
			 reference = response;
			 tableData = response;
			 $.each(response,function(key,value){
				CustomMap.addName(value['Id'],value['Name']);
				CustomMap.addMail(value['Id'],value['MailId']);
				CustomMap.addIdTag(value['Id'],value['Tags']);
				var empTags = value['Tags'].split(",");
				for(i=0;i<empTags.length;i++){
					if(empTags[i] != "")
					CustomMap.addTag(empTags[i],value['Id']);
				}
			 });
			
		}).done(function(){
			 createTable();
		});
	}
	else{
		count = count+1;
		var newdata = [];
		CustomMap.searchTag(tag);
		var mapItr = CustomMap.countMap.keys();
		for(j=0;j<CustomMap.countMap.size;j++){
			var empId = mapItr.next().value;
			var obj = {};
			obj['Id'] = empId;
			obj['Name'] = CustomMap.nameMap.get(empId);
			obj['MailId'] = CustomMap.mailMap.get(empId);
			obj['Tags'] = CustomMap.idTagMap.get(empId);
			obj['Match'] = (CustomMap.countMap.get(empId)*100)/count;
			newdata.push(obj);
		}
		tableData = newdata;
		console.log(tableData);
		createTable();
		
	}
}


function removeData(tag){
	count = count-1;
	if(count==0){
		CustomMap.removeSearchTag(tag);
		tableData = reference;
		createTable();
	}
	else{
		var newdata = [];
		CustomMap.removeSearchTag(tag);
		var mapItr = CustomMap.countMap.keys();
		for(j=0;j<CustomMap.countMap.size;j++){
			var empId = mapItr.next().value;
			var obj = {};
			obj['Id'] = empId;
			obj['Name'] = CustomMap.nameMap.get(empId);
			obj['MailId'] = CustomMap.mailMap.get(empId);
			obj['Tags'] = CustomMap.idTagMap.get(empId);
			obj['Match'] = (CustomMap.countMap.get(empId)*100)/count;
			newdata.push(obj);
		}
		tableData = newdata;
		console.log(tableData);
		createTable();
	}
}

function createTable(){
	
	var source =
    {
        localData: tableData,
        dataType: "json",
        dataFields:
        [
            { name: 'Id', type: 'string' },
            { name: 'Name', type: 'string' },
            { name: 'MailId', type: 'string' },
            { name: 'Tags', type: 'number' },
            { name: 'Match', type: 'number' }
        ]
    };
	
	var dataAdapter = new $.jqx.dataAdapter(source);
	
	$(community.employeeTable).jqxDataTable(
            {
                width: '100%',
                pageable: true,
                pagerButtonsCount: 10,
                source: dataAdapter,
                columnsResize: true,
                columns: [
                  { text: 'Id', dataField: 'Id', width: '10%' },
                  { text: 'Name', dataField: 'Name', width: '15%' },
                  { text: 'Mail Id',  dataField: 'MailId', width: '20%' },
                  { text: 'Skills', dataField: 'Tags', width: '40%' },
                  { text: 'Match %', dataField: 'Match', width: '15%'}
                ]
            });
}
