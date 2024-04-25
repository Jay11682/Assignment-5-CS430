function searchUsers() {
    var searchInput = document.getElementById('searchInput').value.trim();
    if (searchInput === "") {
        alert("Please enter a user name or user ID to search.");
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

function updateUser() {
    var oldUserID = document.getElementById('oldUserID').value.trim();
    var updateUserName = document.getElementById('updateUserName').value.trim();
    var updateUserType = document.getElementById('updateUserType').value.trim();
    
    if (oldUserID === "" || updateUserName === "" || updateUserType === "") {
        alert("Please fill in all fields to update the user.");
        return;
    }

    fetch('/demo/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'oldUserID=' + encodeURIComponent(oldUserID) + '&updateUserName=' + encodeURIComponent(updateUserName) + '&updateUserType=' + encodeURIComponent(updateUserType)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        document.getElementById('updateUserResult').textContent = data;
    })
    .catch(error => console.error('Error:', error));
}

function deleteUser() {
    var deleteUserID = document.getElementById('deleteUserID').value.trim();
    
    if (deleteUserID === "") {
        alert("Please fill in the user ID to delete the user.");
        return;
    }

    fetch('/demo/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'deleteUserID=' + encodeURIComponent(deleteUserID)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        document.getElementById('deleteUserResult').textContent = data;
    })
    .catch(error => console.error('Error:', error));
}

function searchDeviceUsage() {
    var userId = document.getElementById('searchDeviceUserID').value.trim();
    var date1 = document.getElementById('date1').value.trim();
    var date2 = document.getElementById('date2').value.trim();

    if (userId === "" || date1 === "" || date2 === "") {
        alert("Please enter user ID and date range.");
        return;
    }

    fetch('/demo/searchDeviceUsage?userId=' + userId + '&date1=' + date1 + '&date2=' + date2)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayDeviceUsageResult(data);
        })
        .catch(error => console.error('Error:', error));
}

function displayDeviceUsageResult(deviceUsage) {
    var searchResultDiv = document.getElementById('searchDeviceUsage');
    searchResultDiv.innerHTML = "";

    if (deviceUsage.length === 0) {
        searchResultDiv.textContent = "No device usage found.";
    } else {
        var usageList = document.createElement('ul');
        deviceUsage.forEach(usage => {
            var listItem = document.createElement('li');
            listItem.textContent = 'Device: ' + usage.deviceName + ', Usage Duration: ' + usage.usageDuration + ' hours';
            usageList.appendChild(listItem);
        });
        searchResultDiv.appendChild(usageList);
    }
}
