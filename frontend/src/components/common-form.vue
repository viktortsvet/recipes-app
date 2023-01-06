<template>
    <el-form>
        <el-form-item v-for="item in dataForm">
            <el-input v-if="item.type === Constants.types.TEXT" v-model="item.value"></el-input>
        </el-form-item>
    </el-form>
</template>

<script>
import ElForm from 'element-ui';
import ElFormItem from 'element-ui';
import ElInput from 'element-ui';
import Constants from "@/constants/Constants";
export default {
    name: "common-form",
    components: {
        ElForm,
        ElFormItem,
        ElInput
    },
    computed: {
        dataForm: {
            get() {
                console.warn("Override in computed");
                return [];
            }
        }
    }
}
</script>