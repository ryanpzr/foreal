<template>
    <div class="card-comment">
      <div v-if="loading" class="loading-overlay">
        <div class="spinner"></div>
        Loading...
      </div>
  
      <button class="button-close">
        <h1>x</h1>
      </button>
      <h2>Adicionar Comentário</h2>
      <form @submit.prevent="addComment">
        <div class="form-group">
          <label for="author">Autor:</label>
          <input type="text" v-model="author" id="author" required />
        </div>
        <div class="form-group">
          <label for="comment">Comentário:</label>
          <textarea v-model="comment" id="comment" required></textarea>
        </div>
        <button type="submit">Enviar Comentário</button>
      </form>
    </div>
</template>
  
  <script>
    import { setComment } from '/src/js/queryBd.js';

    export default {
        data() {
            return {
                author: '',
                comment: '',
                loading: false,
                idPost: 0

            };
        },
        watch: {
            newIdPostEmitted(newVal) {
                this.idPost = newVal;
            }
        },
        props: {
            newIdPostEmitted: {type: Number, required: true}
        },
        methods: {
            async addComment() {
              const loadingDelay = 1200; 
              this.loading = true;
              
              const response = await setComment(this.author, this.comment, this.idPost);

              if (response) {
                setTimeout(async () => {
                    const jsonComment = await response.json();
                    this.loading = false;
                    this.clearValues()
                    this.$emit('valueNewComment', jsonComment, this.idPost)
                }, loadingDelay);
                
              } else {
                this.loading = false;
              }
            },
            clearValues() {
              this.author = '';
              this.comment = '';
            }
        } 
    }
  </script>

  <style scoped>
  
  .button-close {
      position: absolute;
      margin-left: 94.5%;
      padding: 0;
      border: none;
      height: 30px;
      background-color: #000000;
      cursor: pointer;
  }
  
  .button-close h1 {
      color: white;
      font-size: 25px;
  }
  
  .card-comment {
      width: 60vw;
      height: 50vh;
      background-color: black;
      color: white;
      padding: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="text"], textarea {
    width: 98%;
    padding: 8px;
    border: 1px solid #000000;
    background-color: rgb(49, 49, 49);
    color: white;
    border-radius: 4px;
  }
  
  textarea {
    height: 100px;
    resize: none;
  }
  
  button[type="submit"] {
    width: 200px;
    height: 40px;
    padding: 2px;
    margin-top: 20px;
    border: 1px solid #000000;
    background-color: rgb(49, 49, 49);
    color: white;
    cursor: pointer;
    border-radius: 4px;
  }
  
  button[type="submit"]:hover {
    background-color: rgb(88, 87, 87);
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
  