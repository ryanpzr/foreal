<template>
  <Navbar
    @componentAddPost="stateOfPost"
    @jsonOfPostSearched="(newVal) => this.jsonOfPostSearched = newVal"
    @stateCardPostSearched="(newVal) => this.stateCardPostSearche = newVal"
    :imgAddPost="imgAddPost"
  ></Navbar>

  <MainPage
    :postState="showAddPost"
    @componentAddPost="stateOfPost"
    :stateCardPostSearche="stateCardPostSearche"
  ></MainPage>
</template>

<script>
import Navbar from './components/Navbar.vue';
import MainPage from './view/MainPage.vue';
import { reactive, watch } from 'vue';

export default {
components: {
  Navbar,
  MainPage
},
data() {
  return {
    showAddPost: false,
    imgAddPost: '/src/assets/img/adicionar.png',
    jsonOfPostSearched: {},
    stateCardPostSearche: false
  }
},
provide() {
  return {
    postSearched: reactive(this.jsonOfPostSearched)
  };
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