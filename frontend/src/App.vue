<template>
    <div id="app">
        <button @click="doCount">Счётчик: {{counter}}</button>
        <button @click="exportRecipeWord">Загрузить тест ворда</button>
        <button @click="testWord">Тестовый ещё ворд</button>

        <div>
            <button @click="createUser">Пользоваптель</button>
        </div>

        <div>
            <button @click="getUsers">Все пользователи</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Constants from "./constants/Constants";
axios.defaults.baseURL = Constants.backendBaseUrl;

    export default {
        name: 'app',
        data() {
            return {
                counter: 0
            }
        },
        methods: {
            doCount() {
                this.counter += 1;
            },

            getUsers() {
                axios.get("user/get-all-users")
                    .then(result => {
                        console.log(result);
                    }).catch(e => console.error(e));
            },

            testWord() {
                axios.post('exportDoc/testSomeWord', null, {
                    responseType: 'blob'
                })
                    .then(result => {
                        console.log(result);
                        let link = document.createElement('a');
                        link.download = "Тест ворда";
                        let blob = new Blob([result.data], {type: 'application/msword'});
                        link.href = URL.createObjectURL(blob);
                        document.body.appendChild(link);
                        link.click();
                    }).catch(e => console.error(e));
            },

            createUser() {
                const test = {
                    name: "Mister",
                    lastname: "govno",
                    username: 'mister14',
                    password: '1234567',
                    role: "user"
                };
                axios.post("user/addOrUpdate", JSON.stringify(test), {
                    headers: {
                        "Content-type": 'application/json'
                    }
                })
                    .then(result => console.log(result)).catch(e => console.error(e));
            },

            exportRecipeWord() {
                const idRecipe = '6e68d67c-02d9-499e-aa69-6ce0dbe03367';
                let formData = new FormData();
                formData.append('idRecipe', idRecipe);
                axios.post('exportDoc/recipe-word', formData, {
                    headers: {
                        'Content-type': 'multipart/form-data'
                    },
                    responseType: "blob"
                })
                    .then(result => {
                        console.log(result);
                        let link = document.createElement('a');
                        link.download = "Тест ворда";
                        let blob = new Blob([result.data], {type: 'application/msword'});
                        link.href = URL.createObjectURL(blob);
                        link.click();
                        document.body.appendChild(link);
                        URL.revokeObjectURL(link.href);
                    }).catch(e => console.error(e));
            }
        }
    }
</script>