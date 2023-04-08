<template>
    <div>
        <create-recipe></create-recipe>
        <h1>Для теста!</h1>
    </div>
</template>

<script>
    import CreateRecipe from "@/components/create-recipe";
    import axios from "axios";
    import Constants from "@/constants/Constants";
    axios.defaults.baseURL = Constants.backendBaseUrl;

    export default {
        name: 'app',
        components: {
            CreateRecipe
        },

        mounted() {
            axios.get("recipe/recipesDto")
            .then(result => {
                console.log('recipes sql:');
                console.log(result);
            }).catch(e => console.error(e));
        },

        methods: {
            mouseEnter(e) {
                console.log('mouse enter');
                console.log(e);
            }
        }

    }
</script>

<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    .main {
        width: 50%;
    }
</style>