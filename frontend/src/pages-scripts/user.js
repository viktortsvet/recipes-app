import Vue from "vue";
import UserComponent from "@/components/user-component";

new Vue({
    el: '#app',
    render: h => h(UserComponent)
});