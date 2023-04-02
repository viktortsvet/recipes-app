<template>
    <el-select
        v-model="value"
        filterable
        multiple
        clearable
        @change="onChange"
        :placeholder="placeholder">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
    </el-select>
</template>

<script>
import ElSelect from 'element-ui/lib/select';
import ElOption from 'element-ui/lib/option';
export default {
    name: "multiple-select",
    components: {
        ElSelect,
        ElOption
    },
    props: {
        options: Array,
        placeholder: {type: String, default: 'Выберите значение'},
        eventName: String
    },
    data() {
        return {
            value: []
        }
    },
    methods: {
        onChange(value) {
            console.log(value);
            this.$emit(this.eventName, value);
        }
    }
}
</script>
