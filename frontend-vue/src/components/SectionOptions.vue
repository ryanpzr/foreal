<template>
    <div class="options-button">
        <a href="" class="button">
            <img src="../assets/img/home.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Home</span>
        </a>
        <a href="" class="button">
            <img src="../assets/img/popular.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Popular</span>
        </a>
        <a href="" class="button">
            <img src="../assets/img/breaking-news.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Breaking News</span>
        </a>
        <a href="" class="button">
            <img src="../assets/img/conta.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Sua conta</span>
        </a>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <a href="" class="button">
            <img src="../assets/img/Imagem do WhatsApp de 2024-09-12 à(s) 19.53.04_6fbaa5f5.jpg" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Tema</span>
        </a>
    </div>

</template>

<script>
export default {

}
</script>

<style scoped>
    .options-button {
    display: flex;    
    position: fixed;      
    flex-direction: column;  
    justify-content: flex-start;
}

.button {
    display: flex;
    align-items: center;
    text-decoration: none; 
    background-color: black; 
    padding-right: 180px;
    margin-top: 30px; 
    border-radius: 5px; 
    margin-left: 20px;
    transition: background-color 0.3s; 
}

.button:hover {
    background-color: #424242; 
}

.button-image {
    width: 45px; 
    height: 45px;
    margin-right: 10px;
}

.button-text {
    font-size: 16px; 
    margin-left: 10px;
    color: white;
}


</style>