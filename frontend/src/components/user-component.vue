<template>
    <div>
        <h1>{{name}}</h1>
        <div></div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "user-component",
    data() {
        return {
            name: null,
            id: null
        }
    },

    methods: {
        loadUser() {
            const url = document.URL;
            console.log(url);
        }
    },

    mounted() {
        this.loadUser();
    }
}
</script>

<style>

</style>