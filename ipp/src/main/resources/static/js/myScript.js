

document.addEventListener("DOMContentLoaded", function() {
    // Code to be executed when the DOM is ready
   // alert();
   // loadMyProfile();
});




function functionEditProfile() {
	
	
	
	var currentRole = document.getElementById("currentRole").innerHTML;
	var currentProject = document.getElementById("currentProject").innerHTML;
	var workExperience = document.getElementById("workExperience").innerHTML;
	var skills = document.getElementById("skills").innerHTML;
	
	console.log("currentRole : " + currentRole);
	console.log("currentProject : " + currentProject);
	console.log("workExperience : " + workExperience);
	console.log("skills : " + skills);
		
	document.getElementById("profileReadOnly").style.display="none";
	document.getElementById("profileUpdatable").style.display="block";
}

function functionCancelEditProfile() {
	document.getElementById("profileReadOnly").style.display="block";
	document.getElementById("profileUpdatable").style.display="none";
}


function loadCurrentDemandsView() {

    document.getElementById("myProfile").style.display="none";
    document.getElementById("checkMyStatusView").style.display="none";
	document.getElementById("createDemandsView").style.display="none";
	document.getElementById("currentDemandsView").style.display="block";
}

function loadCreateDemandView() {

    document.getElementById("myProfile").style.display="none";
    document.getElementById("checkMyStatusView").style.display="none";
	document.getElementById("createDemandsView").style.display="block";
	document.getElementById("currentDemandsView").style.display="none";
}


function loadCheckMyStatsView() {

    document.getElementById("myProfile").style.display="none";
    document.getElementById("checkMyStatusView").style.display="block";
	document.getElementById("createDemandsView").style.display="none";
	document.getElementById("currentDemandsView").style.display="none";
}



function loadMyProfile() {

    document.getElementById("myProfile").style.display="block";
    document.getElementById("checkMyStatusView").style.display="none";
	document.getElementById("createDemandsView").style.display="none";
	document.getElementById("currentDemandsView").style.display="none";
}





