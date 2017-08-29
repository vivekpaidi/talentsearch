var login  = {
	username : '#username',
	password : '#password',
	loginBtn : '#loginbtn'
};

$( login.username).jqxInput({width:'73%', height: '30'});
$( login.password).jqxInput({width:'73%', height: '30'});
$( login.loginBtn).jqxButton({ height: '30'});

$(login.loginBtn).on('click',function(event){
	var loginparam = {};
	if($(login.username).val() == "" || $(login.password).val() == "" ){
		alert('invalid username or password');
	}
	else{
		loginparam['username'] = $(login.username).val();
		loginparam['password'] = $(login.password).val();
		$.get('/TalentSearch/jsp/validateuser.jsp',loginparam,function(data){
			var pdata = JSON.parse(data);
			if(pdata == 1){
				window.location.replace('/TalentSearch/jsp/divert.jsp');
			}
			else if(pdata ==0){
				alert("invalid username or password");
			}
		});
		
	}
});