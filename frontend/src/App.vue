<template>
    <div>
        <create-recipe></create-recipe>
        <h1>Для теста!</h1>
    </div>
</template>

<script>
    import CreateRecipe from "@/components/create-recipe";
    export default {
        name: 'app',
        components: {
            CreateRecipe
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