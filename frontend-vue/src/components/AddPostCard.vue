<template>
  <div class="card-post">
    <div v-if="loading" class="loading-overlay">
      <div class="spinner"></div>
      Loading...
    </div>

    <button class="button-close" @click="$emit('actionButtonCloseCard', false)">
      <h1>x</h1>
    </button>
    <h2>Criar Novo Post</h2>
    <form @submit.prevent="addPost">
      <div class="form-group">
        <label for="author">Autor:</label>
        <input type="text" v-model="author" id="author" required />
      </div>
      <div class="form-group">
        <label for="title">Título:</label>
        <input type="text" v-model="title" id="title" required />
      </div>
      <div class="form-group">
        <label for="content">Conteudo:</label>
        <input type="text" v-model="content" id="content" required />
      </div>
      <div class="form-group">
        <label for="image">Imagem:</label>
        <input type="file" id="image" @change="onFileChange" accept="image/*" />
        <br><br>
        <template v-if="preview">
          <label for="image">Preview:</label>
          <img :src="preview" style="height: 80px; width: 80px;"/>
        </template>
      </div>
      <button type="submit">Criar Post</button>
    </form>
  </div>
</template>

<script>
export default {
    name: 'AddPostCard',
    data() {
      return {
        author: '',
        title: '',
        content: '',
        loading: false,
        imageFile: null,
        preview: null
      }
    },
    methods: {
      onFileChange(event) {
        var input = event.target;
        if (input.files) {
          var reader = new FileReader();
          reader.onload = (e) => {
            this.preview = e.target.result;
          }
          this.imageFile=input.files[0];
          reader.readAsDataURL(input.files[0]);
        }
      },
      async addPost() {
        this.loading = true;
        const url = 'http://localhost:8080/auth/home/postarDadosHome';

        const formData = new FormData();
        formData.append("autor", this.author);
        formData.append("titulo", this.title);
        formData.append("conteudo", this.content);
        formData.append("imagem", this.imageFile);

        const loadingDelay = 1200; 
        
        try {
          const response = await fetch(url, {
            method: "POST",
            body: formData
          });

          if (response.ok) {
            const newJson = response.json()
            this.$emit('newJson', newJson)
            console.log("Requisição realizada com sucesso")
          } else {
            console.error("Falha na resposta do servidor");
            this.loading = false;
          }

        } catch (error) {
          console.error("Falha na requisição", error);
          this.loading = false;

        } finally {
            setTimeout(() => {
              this.loading = false;
              this.$emit('actionButtonCloseCard', false);
              this.clearValues()
            }, loadingDelay);
        }
      }, 
      clearValues() {
        this.author = '';
        this.title = '';
        this.content = '';
        document.getElementById('image').value = "";
      }
    }
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

input[type="content"] {
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
  margin-top: 130px;
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

.loading-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  z-index: 10;
}

.spinner {
  border: 4px solid rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  border-top: 4px solid white;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>