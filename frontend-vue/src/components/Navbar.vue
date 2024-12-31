<template>
    <nav>
        <div class="nav_left">
            <h1>
                Foreal
            </h1>
        </div>

        <div class="nav_center">
            <form style="margin-top: 18px">
                <input 
                    class="input-search"
                    type="text" 
                    name="query" 
                    placeholder="Digite sua pesquisa..." 
                    v-model="itemSearched"
                    @input="getJsonOfSearched"
                    id="input-navbar"
                    style="background-color: rgb(49, 49, 49); height: 18px; padding: 10px; width: 300px; border: 1px solid rgb(49, 49, 49);">
                
            </form>
        </div>

        
        <div class="nav_right">
                <button id="button-addPost" @click="toggleStatePost">
                    <img :src="imgAddPost">
                </button>
                <button id="button-addPost">
                    <img src="../assets/img/tres-pontos.png">
                </button>
        </div>
    </nav>
</template>

<script>
import { getSearchedPost } from '@/js/queryBd';

export default {
    data() {
        return {
            showAddPost: false,
            itemSearched: '',
            jsonSearched: []
        }
    },
    props: {
        imgAddPost: {type: String, required: true}
    },
    methods: {
        toggleStatePost() {
            if(this.imgAddPost == '/src/assets/img/adicionar.png') {
                this.showAddPost = true;
            } else {
                this.showAddPost = false;
            }

            this.$emit('componentAddPost', this.showAddPost)
        },
        async getJsonOfSearched() {
            if(this.itemSearched === '') {
                return this.$emit('jsonOfPostSearched', undefined);
            }

            const value = await getSearchedPost(this.itemSearched);

            if (value.length === 0) {
                this.$emit('jsonOfPostSearched', undefined);

            } else {
                console.log('tem resultado')
                this.jsonSearched = value;
                this.$emit('jsonOfPostSearched', this.jsonSearched)
            }
        }
    }

}
</script>

<style scoped>

* {
    margin: 0;
    padding: 0;
}

nav {
    display: flex;
    position: fixed;
    background-color: black;
    width: 100vw;
    height: 8vh;
}

.nav_left {
    width: 19.8vw;
    border-right: 1px solid rgb(53, 53, 53);
}

.nav_left h1 {
    font-size: 30px;
    color: white;
    margin-left: 20px;
    margin-top: 18px;
}

.nav_center {
    width: 47.5vw;
    align-items: center;
    text-align: right;
}

.nav_center h1 {
    margin-top: 30px;
    font-size: 15px;
    color: white;
}

.nav_right {
    width: 32.7vw;
    text-align: end;
}

#button-addPost {
    margin-top: 4%;
    margin-right: 15%;
    cursor: pointer;
    color: white;
    padding: 0;
    border: none; 
    background: none; 
    width: 40px; 
    height: 40px; 
}

#button-addPost img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.input-search {
    outline: none;
    color: white;
}

.input-search:hover {
    outline: none;
    color: white;
}

</style>