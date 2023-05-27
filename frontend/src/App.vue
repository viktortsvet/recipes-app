<template>
    <div>
        <common-form :onSubmit="authenticate"></common-form>
    </div>
</template>

<script>
    import axios from "axios";
    import Constants from "@/constants/Constants";
    import CommonForm from "@/components/common-form";

    axios.defaults.baseURL = Constants.backendBaseUrl;

    export default {
        name: 'app',
        components: {
            CommonForm

        },

        data() {
            return {
                file: null,
                filename: null,
                valueDate: '',
                dates: []
            }
        },

        mounted() {
            const authenticateForm = this.$store.getters['AuthenticationStorage/authenticateForm'];
            this.$store.dispatch('FormStorage/setDataForm', authenticateForm);
        },

        methods: {
            authenticate() {
                this.$store.dispatch('AuthenticationStorage/authenticate');
            },


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