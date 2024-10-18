<template>
    <div class="card-post">
        <button class="button-close" @click="$emit('actionButtonCloseCard', false)">
            <h1>x</h1>
        </button>
        <h2>Criar Novo Post</h2>
        <form @submit.prevent="submitPost">
            <div class="form-group">
                <label for="author">Autor:</label>
                <input type="text" v-model="author" id="author" required />
            </div>
            <div class="form-group">
                <label for="title">Título:</label>
                <input type="text" v-model="title" id="title" required />
            </div>
            <div class="form-group">
                <label for="description">Descrição:</label>
                <input type="description" id="description" required></input>
            </div>
            <div class="form-group">
                <label for="image">Imagem:</label>
                <input type="file" @change="handleImageUpload" accept="image/*" />
            </div>
            <button type="submit">Criar Post</button>
        </form>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.button-close {
    position: absolute;
    margin-left: 94.5%;
    padding: 0 0;
    border: none;
    height: 30px;
    background-color: #000000;
    cursor: pointer;
}

.button-close h1 {
    color: white;
    font-size: 25px;
}

.card-post {
    width: 60vw;
    height: 70vh;
    background-color: black;
    color: white;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"] {
  width: 98%;
  padding: 8px;
  border: 1px solid #000000;
  background-color: rgb(49, 49, 49);
  color: white;
  border-radius: 4px;
}

input[type="description"] {
  width: 98%;
  height: 60px;
  padding: 8px;
  border: 1px solid #000000;
  background-color: rgb(49, 49, 49);
  color: white;
  border-radius: 4px;
}

button[type="submit"] {
  width: 200px;
  height: 40px;
  padding: 2px;
  margin-top: 210px;
  border: 1px solid #000000;
  background-color: rgb(49, 49, 49);
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

button[type="submit"]:hover {
  width: 200px;
  height: 40px;
  padding: 2px;
  border: 1px solid #000000;
  background-color: rgb(88, 87, 87);
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.image-preview {
  margin-top: 10px;
  max-width: 100%;
  height: auto;
}

</style>