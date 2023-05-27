<template>
    <div>
        <div>Имя: {{userData.name}}</div>
        <div>Фамилия: {{userData.lastname}}</div>
    </div>
</template>

<script>
export default {
    name: "user-info",
    props: {
        userData: Object
    }
}
</script>
