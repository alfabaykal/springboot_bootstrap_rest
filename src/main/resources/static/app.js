const url = '/api/users/'
let headers = new Headers();
headers.append('Content-Type', 'application/json; charset=utf-8')
let deleteUserButton = document.getElementById('confirmDelete')
let confirmEdit = document.getElementById('confirmEdit')
let newUser = document.getElementById('addNewUserButton')
let roleSelect = 'USER'

let test = ''
$('#role').click(function () {
    roleSelect = $('#role').val()
    if (roleSelect === 'USER') {
        role = roleUser
    }
    if (roleSelect === 'ADMIN') {
        role = roleAdmin
    }
})
$('#role0').click(function () {
    roleSelect = $('#role0').val()
    if (roleSelect === 'USER') {
        role = roleUser
    }
    if (roleSelect === 'ADMIN') {
        role = roleAdmin
    }
})
let roleAdmin = [
    {
        "id": 1,
        "name": "ROLE_USER",
        "users": [],
        "authority": "ROLE_USER"
    },
    {
        "id": 2,
        "name": "ROLE_ADMIN",
        "users": [],
        "authority": "ROLE_ADMIN"
    }
]
let roleUser = [
    {
        "id": 1,
        "name": "ROLE_USER",
        "users": [],
        "authority": "ROLE_USER"
    }
]
let role = roleUser
let currentID = ''
showUsersTable()

let currentUser = ''

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

newUser.addEventListener('click', addUser)

async function addUser() {
    let form = $('#addUserForm');
    let user = {
        'username': form.find('#username').val(),
        'name': form.find('#name').val(),
        'surname': form.find('#username').val(),
        'password': form.find('#password').val(),
        'age': parseInt(form.find('#age').val()),
        'roles': role
    };
    let request = new Request(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(user)
    });
    await fetch(request)
    console.log(request)
    let tebTrigger = new bootstrap.Tab(document.getElementById('nav-home-tab'))
    tebTrigger.show()
    $('#username').val('')
    $('#name').val('')
    $('#surname').val('')
    $('#password').val('')
    $('#age').val('')
    showUsersTable()
}

function editModal(userId) {
    fetch(url + userId)
        .then((response) => {
            if (response.ok) {
                response.json()
                    .then((user) => {
                        currentID = user.id
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

confirmEdit.addEventListener('click', updateUser)

async function updateUser() {
    let form = $('#editModal');
    let user = {
        'id': currentID,
        'username': form.find('#username0').val(),
        'name': form.find('#name0').val(),
        'surname': form.find('#username0').val(),
        'password': form.find('#password0').val(),
        'age': parseInt(form.find('#age0').val()),
        'roles': role
    };
    let request = new Request(url, {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(user)
    });
    await fetch(request)
    console.log(request)
    showUsersTable()
    $('#closeEditModal').click()
}

function deleteModal(userId) {
    fetch(url + userId)
        .then((response) => {
            if (response.ok) {
                response.json()
                    .then((user) => {
                        currentID = user.id
                        document.forms.deleteUserForm.action = "/admin/" + user.id
                        $('#id1').val(user.id)
                        $('#name1').val(user.name)
                        $('#surname1').val(user.surname)
                        $('#age1').val(user.age)
                        $('#username1').val(user.username)
                    })
            } else {
                console.log('FCKFCKFCKFCKFCKFK')
                console.error(response.status + " - " + response.statusText)
            }
        })
}

deleteUserButton.addEventListener('click', deleteUser)

function deleteUser() {
    fetch(url + currentID, {method: "DELETE"})
        .then((response) => {
            if (response.ok) {
                $('#closeDeleteModal').click()
                showUsersTable()
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
