<template>
    <div v-loading="loading">
        <h2>Всего рецептов: {{maxRecords}}. Отображено записей: {{recipes.length}}</h2>
        <ul class="recipes_list" v-infinite-scroll="load">
            <li :key="recipe.id" v-for="recipe in recipes">
                <recipe-menu-component :id-user="idUser" :recipe="recipe"></recipe-menu-component>
            </li>
        </ul>
    </div>
</template>

<script>
import axios from "axios";
import Constants from "@/constants/Constants";
import Vue from "vue";

import infiniteScroll from "element-ui/lib/infinite-scroll";
Vue.use(infiniteScroll);
import ElCard from 'element-ui/lib/card';
import ElButton from 'element-ui/lib/button';
import {Loading} from "element-ui";
import RecipeMenuComponent from "@/components/recipe-menu-component";

Vue.use(Loading);
axios.defaults.baseURL = Constants.backendBaseUrl;

export default {
    name: "recipes-list",
    components: {
        RecipeMenuComponent,
        ElCard,
        ElButton
    },
    props: {
        idUser: String
    },
    data() {
        return {
            recipes: [],
            maxRecords: null,
            shownRecords: null,
            loading: true,
            start: 0,
            pageSize: 10
        }
    },
    mounted() {
        this.requestMethod(this.start, this.pageSize, this.idUser);
    },
    methods: {
        load() {
            if (this.maxRecords - this.start >= 5) {
                this.requestMethod(this.start, this.pageSize, this.idUser);
            } else if (this.maxRecords - this.start < 5 && this.maxRecords - this.start > 0) {
                this.requestMethod(this.start, this.maxRecords - this.start, this.idUser);
            }
        },



        requestMethod(start, pageSize, idUser) {
            if (!!idUser) {
                axios.get('recipe/recipes-by-userId', {
                    params: {
                        start,
                        pageSize,
                        idUser
                    }
                })
                .then(result => {
                    if (result.status === 200) {
                        console.log(result);
                        this.maxRecords = result.data.recordsLength;
                        this.recipes = [...this.recipes, ...result.data.data];
                        this.start += this.pageSize;
                        this.loading = false;
                    }
                }).catch(e => {
                    console.error(e);
                    this.loading = false;
                });
            }
        }
    }
}
</script>

<style>
    .recipes_list {
        list-style-type: none;
        padding: 0;
        display: grid;
        grid-template: 150px / repeat(4, 300px);
        gap: 10px;
    }
</style>