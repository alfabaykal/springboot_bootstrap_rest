const url = '/api/users/'

function showUsersTable() {
    fetch(url)
        .then(response => {
            if (response.ok) {
                response.json()
                    .then(userList => {
                        if (userList.length > 0) {
                            let temp = ''
                            userList.forEach((user) => {
                                temp += '<tr>'
                                temp += `<td>${user.id}`
                                temp += `<td>${user.name}`
                                temp += `<td>${user.surname}`
                                temp += `<td>${user.age}`
                                temp += `<td>${user.username}`
                                temp += `<td>${user.roles.map(role => ' ' + role.name.substring(5))}`
                                temp += `<td><input id="edit_button" class="btn btn-sm btn-info" value="Edit" onclick="editModal(${user.id})" type="button" data-bs-toggle="modal" data-bs-target="#editModal"></td>`
                                temp += `<td><button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" onclick="deleteModal(${user.id})" data-bs-target="#deleteModal">Delete</button></td>`
                                temp += '</tr>'
                            })
                            document.getElementById('userList').innerHTML = temp
                        } else {
                            console.log('FCKFCKFCKFCKFCKFK')
                            console.error(response.status + " - " + response.statusText)
                        }
                    })

            }
        })
}

function editModal(userId) {
    fetch(url + userId)
        .then((response) => {
            if (response.ok) {
                response.json()
                    .then((user) => {
                        $('#id0').val(user.id)
                        $('#name0').val(user.name)
                        $('#surname0').val(user.surname)
                        $('#age0').val(user.age)
                        $('#username0').val(user.username)
                    })
            } else {
                console.log('FCKFCKFCKFCKFCKFK')
                console.error(response.status + " - " + response.statusText)
            }
        })
}

function deleteModal(userId) {
    fetch(url + userId)
        .then((response) => {
            if (response.ok) {
                response.json()
                    .then((user) => {
                        $('#id1').val(user.id)
                        $('#name1').val(user.name)
                        $('#surname1').val(user.surname)
                        $('#age1').val(user.age)
                        $('#username1').val(user.username)
                        $('#deleteAction').val("/admin/" + user.id)
                    })
            } else {
                console.log('FCKFCKFCKFCKFCKFK')
                console.error(response.status + " - " + response.statusText)
            }
        })
}

function someFunction(userId) {
    fetch('/api/users/' + userId)
        .then((response) => {
            if (response.ok) {
                response.json()
                    .then((user) => {
                    $('#testText').val(user.username)
                    })
            } else {
                console.log('FCKFCKFCKFCKFCKFK')
                console.error(response.status + " - " + response.statusText)
            }
        })
}

showUsersTable()

function getUserById(userId) {
    fetch("api/users/" + userId).then((response) => {
        if (response.ok) {
            response.json().then((user) => {

                $('#method').val('put');
                $('#surname5').val(user.surname);
                $('#name5').val(user.name);
                $('#age5').val(user.age);
                $('#username5').val(user.username);
            })
        } else {
            console.error(response.status + " - " + response.statusText)
        }
    })
}

function getRoles() {
    fetch("/api/roles").then((response) => {
        if (response.ok) {
            response.json().then((roles) => {
                let option = '';
                roles.forEach((role) => {
                    option += '<option value="' + role.id + '">' + role.name + '</option>';
                });
                $('#role5').append(option);
            });
        } else {
            console.error(response.status + " - " + response.statusText);
        }
    });
}