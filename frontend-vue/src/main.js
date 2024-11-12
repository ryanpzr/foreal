
import { createApp } from 'vue'
import App from './App.vue'

const app = createApp(App);

app.config.errorHandler = (err, instance, info) => {
    console.err("Global error:", err);
    console.log("Vue instance:", instance)
    console.log("Error info:", info)
};

app.mount('#app');