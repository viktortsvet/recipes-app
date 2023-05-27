import axios from "axios";

const state = {
    loggedIn: false
};

const getters = {
    authenticateForm: (state) => {
        return [
            {
                label: 'Введите логин',
                value: '',
                key: 1,
                type: 'text'
            },
            {
                label: 'Введите пароль',
                value: '',
                key: 2,
                type: 'password'
            }
        ]
    },

    loggedIn: state => state.loggedIn
};

const mutations = {

};

const actions = {
    authenticate: (context) => {
        const data = context.getters.authenticateForm;
        const username = data[0].value;
        const password = data[1].value;
        if (!!username && !!password) {
            const authenticationRequest = {username, password};
            axios.post('auth/authenticate', authenticationRequest)
            .then(result => {
                //в зависимости от результата перенаправлять на страницу пользователя или выдавать ошибку
                if (result.status === 200) {
                    window.location.href = `user.html?id=${result.data.idUser}`;
                }
            })
        }
    }
};

const namespaced = true;

export default {
    getters, actions, mutations, namespaced
}