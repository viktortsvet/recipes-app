const state = {
    username: null,
    password: null,
    token: localStorage.getItem("token") !== null ? localStorage.getItem("token") : null
};