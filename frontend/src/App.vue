<template>
    <div id="app">
        <button @click="doCount">Счётчик: {{counter}}</button>
    </div>
</template>

<script>
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
            }
        }
    }
</script>