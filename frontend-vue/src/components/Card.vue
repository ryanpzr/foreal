<template>
    <div v-for="jsonElement in this.json" id="card" class="card">
        <br>
        <div class="profile">
            <img style="border: none" class="img-profile" src="../assets/img/usuario.png">
            <h1 style="margin-left: 10px; margin-top: 20px;">{{ jsonElement.autor }}</h1>
        </div>
        <img style="margin-top: 3%;" src="../assets/img/futebol.jpg"> 
        <div class="like">
            <button v-on:click="setLike(jsonElement)" class="like-button" aria-label="Like">
                <img style="border: none" :src="jsonElement.pathImg" alt="Like" />
            </button>
            <button class="salvar-button" aria-label="Salvar" style="margin-left: 3%;">
                <img style="border: none" src="../assets/img/salvo.png" alt="Salvar" />
            </button>
                <p style="margin-left: 3%;">{{ jsonElement.gostar }} pessoas curtiram esse post</p>
        </div>
        <div class="description">
            <h1>{{ jsonElement.autor }}</h1>
            <p style="margin-left: 10px;">{{ jsonElement.conteudo }}</p>
        </div>
        <div class="button-comment">
            <button>Comentar</button>
        </div>
        <br>
    </div>
</template>

<script>
import { updateImgBd } from '/src/queryBd.js';
import { updateLikeState } from '/src/queryBd.js';

export default {

    data() {
        return {
            json: [],
            like: null,
            isClicked: false
        }
    },
    mounted() {
        this.getAllPosts();

        const storedValue = localStorage.getItem('isClicked');

        if(storedValue != null) {
            this.isClicked = JSON.parse(storedValue);
        }

    },
    watch: {
        isClicked(newVal) {
            localStorage.setItem('isClicked', JSON.stringify(newVal));
        }
    },
    methods: {
        async getAllPosts() {
            const url = 'http://localhost:8080/auth/home/buscarDadosHome';

            try {
                const response = await fetch(url);
                if (!response.ok) {
                    throw new Error(`Response status: ${response.status}`);
                }

                const json = await response.json();
                this.json = json;

            } catch (error) {
                console.log('Erro ao buscar os posts:', error);
            }
        },
        async setLike(jsonElement) {
            console.log(jsonElement)

            const url = 'http://localhost:8080/auth/home/like';
            var likeState = 0;

            this.changeImg(jsonElement);

            if(jsonElement.likeState) {
                likeState = 1;

            } else {
                likeState = -1;
            }


            const data = {
                id: Number(jsonElement.id),
                likeParam: likeState
            }

            try {
                const response = await fetch(url, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json' 
                    },
                    body: JSON.stringify(data)
                });

                if (response.ok) {
                    const post = this.json.find(p => p.id === jsonElement.id);
                    if (post) {
                        if(jsonElement.likeState) {
                            post.gostar += 1;

                        } else {
                            post.gostar -= 1;

                        }
                    }
                }


            } catch (error) {
                console.log('Erro ao buscar os posts:', error);
            }
        },
        changeImg(jsonElement) {

            jsonElement.likeState = !jsonElement.likeState;

            if (jsonElement.likeState) {
                updateImgBd(jsonElement.id, '/src/assets/img/like-clicado.png');
                updateLikeState(jsonElement.id, jsonElement.likeState);

                jsonElement.pathImg = '/src/assets/img/like-clicado.png'
            } else {
                updateImgBd(jsonElement.id, '/src/assets/img/like.png');
                updateLikeState(jsonElement.id, jsonElement.likeState);

                jsonElement.pathImg = '/src/assets/img/like.png'
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
    margin-bottom: 3%;
}

button {
    height: 20px;
    width: 80px;
    cursor: pointer;
}

h1 {
    color: white;
    font-size: 15px;
}

p {
    color: white;
    font-size: 12px;
}
</style>