import Vue from "vue";
import UserComponent from "@/components/user-component";

import 'element-ui/lib/theme-chalk/index.css';

new Vue({
    el: '#app',
    render: h => h(UserComponent)
});