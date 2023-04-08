<template>
    <div>
        <el-button @click="openForm" type="primary">Создать рецепт</el-button>
    </div>
</template>

<script>
import axios from "axios";
import Constants from "@/constants/Constants";
import CommonForm from "@/components/common-form";
import ElButton from 'element-ui/lib/button';

axios.defaults.baseURL = Constants.backendBaseUrl;

export default {
    name: "create-recipe",

    components: {
        CommonForm,
        ElButton
    },

    data() {
        return {
            data: []
        }
    },

    created() {
        // this.createData();
    },

    methods: {

        createData() {
            axios.get('user/getTestUsers')
            .then(result => {
                if (result.status === 200) {
                    console.log(result);
                    const users = result.data.map(item => {
                        return {
                            label: item.name + " " + item.lastname,
                            value: item.id,
                            key: item.id
                        }
                    });
                        const dataArr = [
                            {
                                label: 'Выберите имя',
                                key: 1,
                                options: [...users],
                                multiple: false,
                                value: null,
                                type: 'select'
                            },
                            {
                                label: 'Введите название',
                                key: 2,
                                type: 'text',
                                value: null
                            },
                            {
                                label: 'Введите описание',
                                key: 3,
                                type: 'textarea',
                                value: null
                            }
                    ];
                    this.data = [...dataArr];
                }
            })
            .catch(e => console.error(e));
        },

        saveRecipe(data) {
            console.log(this.data);
            const recipe = {
                id: data[0].value,
                recipeName: data[1].value,
                description: data[2].value
            };
            const formData = new FormData();
            formData.append("idUser", recipe.id);
            formData.append("recipeName", recipe.recipeName);
            formData.append("description", recipe.description);
            axios.post('recipe/createRecipe', formData)
            .then(result => {
                if (result.status === 200) {
                    this.$message({
                        message: 'Рецепт успешно сохранён',
                        type: 'success',
                        showClose: true
                    });
                }
            }).catch(e => {
                this.$message({
                    message: 'Возникла ошибка: ' + e,
                    type: 'error',
                    showClose: true
                })
            });
        },

        openForm() {
            this.$store.dispatch('FormStorage/setDataForm', this.data);
            const h = this.$createElement;
            this.$msgbox({
                title: 'Создание рецепта',
                message: h(CommonForm, {
                    attrs: {
                        $store: this.$store,
                        onSubmit: this.saveRecipe
                    }
                })
            });
        }
    }
}
</script>
