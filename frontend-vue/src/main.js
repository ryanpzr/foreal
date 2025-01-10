import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import Main from './view/Main.vue';
import LoginPage from './view/Login.vue';

const routes = [
  {
    path: '/',
    component: Main
  },
  {
    path: '/Login',
    name: 'Login',
    component: LoginPage
  }
];
const router = createRouter({
  history: createWebHistory(),
  routes
});

const app = createApp(App);
app.use(router);
app.mount('#app');
