<template>
    <div class="options-button">
        <button 
            @click="selectButton('buscarDadosHome')" 
            :class="['button', { selected: selectedButton === 'buscarDadosHome' }]" 
            id="buscarDadosHome"
        >
            <img src="../assets/img/home.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Home</span>
        </button>
        <button 
            @click="selectButton('buscarPopular')" 
            :class="['button', { selected: selectedButton === 'buscarPopular' }]" 
            id="buscarPopular"
        >
            <img src="../assets/img/popular.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Popular</span>
        </button>
        <button 
            @click="selectButton('buscarBreaking')" 
            :class="['button', { selected: selectedButton === 'buscarBreaking' }]" 
            id="buscarBreaking"
        >
            <img src="../assets/img/breaking-news.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Breaking News</span>
        </button>
        <button class="button"
            @click="selectButton('suaConta')" 
            :class="['button', { selected: selectedButton === 'suaConta' }]"
        >
            <img src="../assets/img/conta.png" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Sua conta</span>
        </button>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <a class="button">
            <img src="../assets/img/Imagem do WhatsApp de 2024-09-12 à(s) 19.53.04_6fbaa5f5.jpg" alt="Descrição da Imagem" class="button-image">
            <span class="button-text">Tema</span>
        </a>
    </div>
</template>

<script>
export default {
    data() {
        return {
            selectedButton: null,
        }
    },
    methods: {
        selectButton(state) {
            this.selectedButton = state;
            this.emitMainState(state);
        },
        emitMainState(state) {
            this.$emit('valueMainState', state);
        }
    }
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
    padding-right: 150px;
    margin-top: 30px; 
    border-radius: 5px; 
    margin-left: 20px;
    transition: background-color 0.3s; 
    cursor: pointer;
    border: none;
}

.button:hover {
    background-color: #424242; 
}

.button.selected {
    background-color: #616161; /* Cor para o botão selecionado */
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
