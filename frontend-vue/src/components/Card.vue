<template>
    <div class="card">
        <br>
        <div class="profile">
            <img style="border: none" class="img-profile" src="../assets/img/usuario.png">
            <h1 style="margin-left: 10px; margin-top: 20px;">ryanpzr</h1>
        </div>
        <img style="margin-top: 3%;" src="../assets/img/futebol.jpg"> 
        <div class="like">
            <button class="like-button" aria-label="Like">
                <img style="border: none" src="../assets/img/like.png" alt="Like" />
            </button>
            <button class="salvar-button" aria-label="Salvar" style="margin-left: 3%;">
                <img style="border: none" src="../assets/img/salvo.png" alt="Dislike" />
            </button>
            <p style="margin-left: 3%;">0 pessoas curtiram esse post</p>
        </div>
        <div class="description">
            <h1>ryanpzr</h1>
            <p style="margin-left: 10px;">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Praesentium, quos. Illum, hic cum pariatur non quo impedit cupiditate debitis harum quasi atque odit repellendus</p>
        </div>
        <div class="button-comment">
            <button>Comentar</button>
        </div>
        <br>
    </div>
</template>

<script>
export default {

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