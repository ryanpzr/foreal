<template>
    <Navbar />
    <MainPage />
</template>

<script>
import Navbar from './components/Navbar.vue';
import MainPage from './components/MainPage.vue';

export default {
  components: {
      Navbar,
      MainPage
  }
}

</script>

<style>

body {
    overflow-x: hidden;
    margin: 0; 
    padding: 0; 
    background-color: black;
}

</style>