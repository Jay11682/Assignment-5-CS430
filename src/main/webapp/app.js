function searchUsers() {
    var searchInput = document.getElementById('searchInput').value.trim();
    if (searchInput === "") {
        alert("Please enter a user name to search.");
        return;
    }

    fetch('/demo/search?userName=' + searchInput)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displaySearchResult(data);
        })
        .catch(error => console.error('Error:', error));
}

function displaySearchResult(users) {
    var searchResultDiv = document.getElementById('searchResult');
    searchResultDiv.innerHTML = "";

    if (users.length === 0) {
        searchResultDiv.textContent = "No users found.";
    } else {
        var userList = document.createElement('ul');
        users.forEach(user => {
            var listItem = document.createElement('li');
            listItem.textContent = user.userName + ' - ' + user.userType; // Update property names according to the backend
            userList.appendChild(listItem);
        });
        searchResultDiv.appendChild(userList);
    }
}

function addUser() {
    var newUserID = document.getElementById('newUserID').value.trim();
    var newUserName = document.getElementById('newUserName').value.trim();
    var newUserType = document.getElementById('newUserType').value.trim();
    
    if (newUserID === "" || newUserName === "" || newUserType === "") {
        alert("Please fill in all fields to add a new user.");
        return;
    }

    fetch('/demo/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'userID=' + encodeURIComponent(newUserID) + '&userName=' + encodeURIComponent(newUserName) + '&userType=' + encodeURIComponent(newUserType)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        document.getElementById('addUserResult').textContent = data;
    })
    .catch(error => console.error('Error:', error));
}