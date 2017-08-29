var profile = {
		'details': {
			'form' : '#userForm',
			'id' : '#userId',
			'name' : '#userName',
			'designation' : '#designation',
			'email' : '#emailId',
			'mobile' : '#mobileNo',
			'experience' : '#experience',
			'userDetailsSave' : '#userDetailsSave'
		}
}

$(profile.details.userDetailsSave).jqxButton();
$(profile.details.id).jqxInput();
$(profile.details.name).jqxInput();
$(profile.details.designation).jqxInput();
$(profile.details.email).jqxInput();
$(profile.details.mobile).jqxInput();
$(profile.details.experience).jqxInput();