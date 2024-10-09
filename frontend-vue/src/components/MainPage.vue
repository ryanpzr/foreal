<template>
    <main class="main">
        <div class="main-left">
            <SectionOptions />
        </div>

        <div class="main-right">
            <Card />
        </div>
    </main>
</template>

<script>
import SectionOptions from './SectionOptions.vue';
import Card from './Card.vue';

export default {
    components: {
        SectionOptions,
        Card
    }
}
</script>

<style>

.main {
    display: flex;
}

.main-left {
    width: 20vw;
    height: 100vh;
    margin-top: 8vh;
    border-right: 1px solid rgb(53, 53, 53);
    background-color: black;
}

.main-right {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    text-align: center;
    width: 80vw;
    height: 100vh;
    margin-top: 8vh;
    background-color: black;

    border: 1px solid red;
}
</style>
