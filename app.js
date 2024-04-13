function searchUsers() {
    var searchInput = document.getElementById('searchInput').value.trim();
    if (searchInput === "") {
        alert("Please enter a user name to search.");
        return;
    }

    fetch('/WebAssignment/search?userName=' + searchInput)
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
    var userId = document.getElementById('newUserID').value.trim();
    var userName = document.getElementById('newUserName').value.trim();
    var userType = document.getElementById('newUserType').value.trim();

    if (userName === "" || userType === "" || userId === "") {
        alert("Please fill in all fields to add a user.");
        return;
    }

    fetch(`/WebAssignment/createUser?userId=${userId}&userName=${encodeURIComponent(userName)}&userType=${encodeURIComponent(userType)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            document.getElementById('addUserResult').textContent = "User added successfully.";
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('addUserResult').textContent = "Error adding user.";
        });
}

function updateUser() {
    var userId = document.getElementById('oldUserID').value.trim();
    var userName = document.getElementById('updateUserName').value.trim();
    var userType = document.getElementById('updateUserType').value.trim();

    if (userName === "" || userType === "" || userId === "") {
        alert("Please fill in all fields to update a user.");
        return;
    }

    fetch(`/WebAssignment/updateUser?userId=${userId}&userName=${encodeURIComponent(userName)}&userType=${encodeURIComponent(userType)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            document.getElementById('updateUserResult').textContent = "User updated successfully.";
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('updateUserResult').textContent = "Error updating user.";
        });
}

function deleteUser() {
    var userId = document.getElementById('deleteUserID').value.trim();

    if (userId === "") {
        alert("Please enter a user ID to delete.");
        return;
    }

    fetch(`/WebAssignment/deleteUser?userId=${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            document.getElementById('deleteUserResult').textContent = "User deleted successfully.";
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('deleteUserResult').textContent = "Error deleting user.";
        });
}
