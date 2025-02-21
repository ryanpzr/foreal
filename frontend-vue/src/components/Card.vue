<template>
    <div v-for="jsonElement in this.json" id="card" class="card">
        <br>
        <div class="profile">
            <img style="border: none" class="img-profile" src="../assets/img/usuario.png">
            <h1 style="margin-left: 10px; margin-top: 20px;">{{ jsonElement.autor }}</h1>
        </div>
        <img class="img-post" :src="getImagePost(jsonElement.pathImgPost)"> 
        <div class="like">
            <button v-on:click="setLikePost(jsonElement)" class="like-button" aria-label="Like">
                <img style="border: none" :src="jsonElement.pathImgLike" alt="Like" />
            </button>
            <button class="salvar-button" aria-label="Salvar" style="margin-left: 3%;">
                <img style="border: none" src="../assets/img/salvo.png" alt="Salvar" />
            </button>
                <p style="margin-left: 3%;">{{ jsonElement.gostar }} pessoas curtiram esse post</p>
        </div>
        <div class="description">
            <h1>{{ jsonElement.titulo }} <span style="margin: 3px;">|</span></h1>
            <p style="margin-left: 3px;">{{ jsonElement.conteudo }}</p>
        </div>
        <div class="button-comment">
            <button @click="changeStateComment(jsonElement.id)">Comentar</button>
            <a href="#" @click.prevent="toggleComments(jsonElement.id)" :class="{'active-comments': showComments[jsonElement.id]}">
                Exibir Comentários
            </a>
        </div>
        <transition-group name="fade" tag="div" v-if="showComments[jsonElement.id]">
            <div v-for="comment in jsonElement.comentarios" :key="comment.autor" class="card-toComment">
                <h1>{{ comment.autor }}:</h1>
                <p style="margin-left: 10px;">{{ comment.comentario }}</p>
            </div>
        </transition-group>
        <br>
    </div>

    <AddCommentCard class="card-addPost"
        v-show="showAddCommentsCard"
        :newIdPostEmitted="idPostCommentsCard"
        @valueNewComment="pushNewComment"
        @closeCommentCard="this.showAddCommentsCard = !this.showAddCommentsCard"
    >
    </AddCommentCard>
</template>

<script>
    import { getAllPosts, setLike } from '/src/js/queryBd.js';
    import AddCommentCard from './AddCommentCard.vue';

    export default {
        data() {
            return {
                json: [],
                like: null,
                isClicked: false,
                mainState: 'buscarDadosHome',
                showComments: {},
                idComment: '',
                showAddCommentsCard: false,
                idPostCommentsCard: 0            
            }
        },
        components: {
            AddCommentCard
        },
        mounted() {
            this.getDataPost()
        },
        watch: {
            valueMainState(newVal) {
                this.mainState = newVal;
                this.getDataPost();
            },
            valueNewPost(newVal) {
                let listContent = this.json;
                listContent.push(JSON.parse(JSON.stringify(newVal)));
                this.json = listContent;
            }
        },
        props: {
            valueMainState: { type: String, required: true },
            valueNewPost: {type: Object, required: true}
        },
        methods: {
            async getDataPost() {
                const responseJson = await getAllPosts(this.mainState);
                this.json = responseJson;
            },
            async setLikePost(jsonElement) {
                setLike(jsonElement, this.json);
            },
            getImagePost(value) {
                const imagePath = new URL(`../assets/imagesPosts/${value}`, import.meta.url).href;
                return imagePath;
            },
            toggleComments(id) {
                this.showComments[id] = !this.showComments[id];

                    if(!this.showComments[id]) {
                        this.idComment = '';
                    } else {
                        this.idComment = id;
                    }
                
            },
            changeStateComment(id) {
                this.idPostCommentsCard = id;
                this.showAddCommentsCard = !this.showAddCommentsCard;
            },
            pushNewComment(newJson, idPost) {
                console.log('chegou aqui')
                console.log(newJson)
                console.log(idPost)

                for (const element of this.json) {
                    if (element.id === idPost) {
                        element.comentarios.push(newJson);
                    }
                }
            }
        }
    }
</script>

<style scoped>
    .card {
        width: 25vw;
        height: auto;
        border-top: 1px solid rgb(53, 53, 53);
    }

    .profile {
        display: flex;
        margin-top: 3%;
    }

    .img-profile {
        width: 50px; 
        height: 50px; 
        border-radius: 50%; 
        object-fit: cover;
    }

    img {
        width: 100%;
        border: 3px solid rgb(53, 53, 53);
    }

    .like {
        display: flex;
        margin-top: 2%;
        align-items: center;
    }

    .like-button,
    .salvar-button {
        width: 30px;
        height: 30px; 
        border: none;
        background: none;
        cursor: pointer;
        outline: none;
        padding: 0; 
        margin: 0;
    }

    .like-button img,
    .salvar-button img {
        width: 100%;
        height: auto;
    }

    .description {
        display: flex;
    }

    .button-comment {
        display: flex;
        margin-top: 2%;
        margin-bottom: 3%;
        align-items: center;
    }

    a {
        color: gray;
        font-size: 10px;
        margin-left: 10px;
    }

    a:hover {
        color: rgb(228, 224, 224);
        font-size: 10px;
        margin-left: 10px;
    }

    .active-comments {
        color: rgb(228, 224, 224);
        font-size: 10px;
        margin-left: 10px;
    }

    button {
        height: 30px;
        width: 100px;
        padding: 2px;
        border: 1px solid #000000;
        background-color: rgb(49, 49, 49);
        color: white;
        cursor: pointer;
        border-radius: 4px;
    }

    .card-toComment {
        display: flex;
        margin-left: 15px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.5s;
    }

    .fade-enter, .fade-leave-to {
        opacity: 0;
    }

    h1 {
        color: white;
        font-size: 15px;
    }

    p {
        color: white;
        font-size: 12px;
        white-space: normal;
        word-wrap: break-word;
    }

    .img-post {
        margin-top: 3%;
        max-height: 500px;
    }
</style>