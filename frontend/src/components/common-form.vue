<template>
    <el-form :model="dataForm" ref="commonForm">
        <el-form-item :label="item.label" :key="item.key" v-for="item in dataForm.data">
            <el-input v-if="item.type === 'text'" v-model="item.value"></el-input>
            <el-input :rows="3" type="textarea" v-if="item.type === 'textarea'" v-model="item.value"></el-input>
            <el-select
                v-if="item.type === 'select'"
                filterable
                clearable
                :multiple="item.multiple"
                v-model="item.value"
            >
                <el-option
                    v-for="option in item.options"
                    :key="option.key"
                    :label="option.label"
                    :value="option.value"
                >
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="saveData('commonForm')">Сохранить</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import ElForm from 'element-ui/lib/form';
import ElFormItem from 'element-ui/lib/form-item';
import ElInput from 'element-ui/lib/input';
import ElSelect from 'element-ui/lib/select';
import ElOption from 'element-ui/lib/option';
import ElButton from 'element-ui/lib/button';
export default {
    name: "common-form",
    components: {
        ElForm,
        ElFormItem,
        ElInput,
        ElSelect,
        ElOption,
        ElButton
    },
    props: {
        $store: Object,
        onSubmit: Function
    },
    computed: {
        dataForm: {
            get() {
                return {data: this.$store.getters['FormStorage/dataForm']};
            }
        }
    },
    methods: {
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        saveData(formName) {
            this.onSubmit(this.dataForm.data);
            this.resetForm(formName);
        }
    }
}
</script>