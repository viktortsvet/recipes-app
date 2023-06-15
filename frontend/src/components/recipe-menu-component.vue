<template>
    <el-card style="height: 100%">
        <!--<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
        <div slot="header">
            <span>{{recipe.recipeName}}</span>
            <el-button style="float: right; padding: 3px 0" type="text">Operation button</el-button>
        </div>
        <div class="text item">
            {{recipe.description}}
            <button @click="doLike(idUser, recipe.id)">Нравится: {{likesLength}}</button>
        </div>
    </el-card>
</template>

<script>
import axios from "axios";
import ElCard from 'element-ui/lib/card';
import ElButton from 'element-ui/lib/button';

export default {
    name: "recipe-menu-component",
    props: {
        idUser: String,
        recipe: Object
    },
    components: {
        ElButton, ElCard
    },
    data() {
        return {
            likesLength: null
        }
    },
    mounted() {
        this.loadLikesLength(this.recipe.id);
    },
    methods: {
        doLike(idUser, idRecipe) {
            if (!!idUser && !!idRecipe) {
                const likeDto = {idUser, idRecipe};
                axios.post("like/save-like", likeDto)
                .then(result => {
                    console.log(result);
                    this.loadLikesLength(idRecipe);
                }).catch(e => console.error(e));
            }
        },

        loadLikesLength(idRecipe) {
            if (!!idRecipe) {
                axios.get("like/get-likes-length", {
                    params: {
                        idRecipe
                    }
                }).then(result => {
                    this.likesLength = result.data;
                }).catch(e => console.error(e));
            }
        }
    }
}
</script>
