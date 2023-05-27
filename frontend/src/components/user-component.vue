<template>
    <div>
        <h1>{{name}}</h1>
        <button @click="createChat">Создать чат</button>

        <el-container style="height: 100%; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu>
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-menu"></i>Основное</template>
                        <el-menu-item @click="setCurrentTab('user-info')" index="1-1">Основая страница</el-menu-item>
                        <el-menu-item @click="setCurrentTab('recipes-list')" index="1-2">Рецепты</el-menu-item>
                        <el-menu-item index="1-3">Добавить рецепт</el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title"><i class="el-icon-setting"></i>Настройки</template>
                        <el-menu-item-group>
                            <template slot="title">Group 1</template>
                            <el-menu-item index="2-1">Option 1</el-menu-item>
                            <el-menu-item index="2-2">Option 2</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>

            <el-container>
                <el-main class="user-info">
                    <component :userData="userData" :idUser="id" :is="currentTab"></component>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from "axios";
import Constants from "@/constants/Constants";
import UserInfo from "@/components/user-info";
import RecipesList from "@/components/recipes-list";

import ElContainer from 'element-ui/lib/container';
import ElHeader from 'element-ui/lib/header';
import ElAside from 'element-ui/lib/aside';
import ElMenu from 'element-ui/lib/menu';
import ElMenuItem from 'element-ui/lib/menu-item';
import ElMenuItemGroup from 'element-ui/lib/menu-item-group';
import ElSubmenu from 'element-ui/lib/submenu';
import ElMain from 'element-ui/lib/main';
import ElCard from 'element-ui/lib/card';

axios.defaults.baseURL = Constants.backendBaseUrl;

export default {
    name: "user-component",

    components: {
        ElContainer,
        ElHeader,
        ElAside,
        ElMenu,
        ElMenuItemGroup,
        ElMenuItem,
        ElSubmenu,
        ElMain,
        ElCard,
        UserInfo,
        RecipesList
    },


    data() {
        return {
            name: null,
            id: null,
            currentTab: '',
            userData: null
        }
    },

    methods: {

        createChat() {
            axios.get('user/doChat', {
                params: {
                    idUser: this.id,
                    idChat: '57e36dc7-4d9e-44f3-ba7f-6e669b60a7dc'
                }
            }).then(result => {
                console.log(result);
            }).catch(e => console.error(e));
        },

        doLike() {

        },

        loadUser() {
            this.id = document.URL.substring(35);
            axios.get('user/getUserById', {
                params: {
                    idUser: this.id
                }
            })
            .then(result => {
                if (result.status === 200) {
                    console.log(result);
                    const userData = result.data;
                    this.userData = userData;
                    this.name = userData.name + " " + userData.lastname;
                    document.title = this.name;
                }
            })
        },

        setCurrentTab(tabName) {
            this.currentTab = tabName;
        }
    },

    mounted() {
        this.loadUser();
    }
}
</script>

<style>
    .user-info {
        overflow-y: scroll;
        height: 400px;
    }
</style>