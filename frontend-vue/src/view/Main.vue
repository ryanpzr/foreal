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
  import Navbar from '@/components/Navbar.vue';
  import MainPage from '@/components/MainPage.vue';

  export default {
  components: {
    Navbar,
    MainPage
  },
  data() {
    return {
      showAddPost: false,
      imgPaths: {
        selected: require("../assets/img/adicionar-selecionado.png"),
        normal: require("../assets/img/adicionar.png"),
      },
      imgAddPost: require("../assets/img/adicionar.png"),
    }
  },
  methods: {
    stateOfPost(state) {
      this.showAddPost = state;
      this.imgAddPost = state ? this.imgPaths.selected : this.imgPaths.normal;
    },
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