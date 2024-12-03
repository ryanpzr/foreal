<template>
  <Navbar
    @componentAddPost="stateOfPost"
    @jsonOfPostSearched="(newVal) => this.jsonOfPostSearched = newVal"
    :imgAddPost="imgAddPost"
  ></Navbar>

  <MainPage
    :postState="showAddPost"
    @componentAddPost="stateOfPost"
    :jsonOfPostSearched="jsonOfPostSearched"
  ></MainPage>
</template>

<script>
import { reactive } from 'vue';
import Navbar from './components/Navbar.vue';
import MainPage from './view/MainPage.vue';

export default {
components: {
  Navbar,
  MainPage
},
data() {
  return {
    showAddPost: false,
    imgAddPost: '/src/assets/img/adicionar.png',
    jsonOfPostSearched: reactive({})
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