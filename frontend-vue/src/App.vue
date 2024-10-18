<template>
    <Navbar
    @componentAddPost="stateOfPost"
    :imgAddPost="imgAddPost"
    ></Navbar>

    <MainPage
    :postState="showAddPost"
    @componentAddPost="stateOfPost"
    ></MainPage>
</template>

<script>
import Navbar from './components/Navbar.vue';
import MainPage from './components/MainPage.vue';

export default {
  components: {
      Navbar,
      MainPage
  },
  data() {
    return {
        showAddPost: false,
        imgAddPost: '/src/assets/img/adicionar.png'
    }
  },
  methods: {
    stateOfPost(state) {
      this.showAddPost = state;

      this.imgAddPost = this.showAddPost 
                ? '/src/assets/img/adicionar-selecionado.png' 
                : '/src/assets/img/adicionar.png';
    }
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