<template>
    <div class="account-info">

      <div v-if="loading" class="loading-overlay">
      <div class="spinner"></div>
      Loading...
      </div>

      <h2>Informações da Conta</h2>
      
      <div class="profile-image">
        <img :src="profilePreview" alt="Imagem de perfil" />
        <input type="file" @change="onImageChange" />
      </div>
      
      <form @submit.prevent="saveChanges">
        <div class="form-group">
          <label for="name">Nome:</label>
          <input
            type="text"
            id="name"
            v-model="name"
            placeholder="Digite seu nome"
          />
        </div>
        <div class="form-group">
          <label for="email">E-mail:</label>
          <input
            type="email"
            id="email"
            v-model="email"
            placeholder="Digite seu e-mail"
          />
        </div>
        <div class="form-group">
          <label for="phone">Telefone:</label>
          <input
            type="tel"
            id="phone"
            v-model="phone"
            placeholder="Digite seu telefone"
          />
        </div>
        <button type="submit">Salvar alterações</button>
      </form>
    </div>
  </template>
  
  <script>
  import { saveAccountInfo, getAccountInfo } from '../js/queryBd'

  export default {
    data() {
      return {
        name: '',
        email: '',
        phone: '',
        profilePreview: 'https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg',
        profileImage: '',
        loading: false,
        jsonAccountInfo: {}
      };
    },
    async mounted() {
      this.jsonAccountInfo = await getAccountInfo();

      if(this.jsonAccountInfo.length > 0) {
        this.profilePreview = '/src/assets/imageProfilePicture/' + this.jsonAccountInfo[0]["pathProfilePicture"]
        this.name = this.jsonAccountInfo[0]["name"]
        this.email = this.jsonAccountInfo[0]["email"]
        this.phone = this.jsonAccountInfo[0]["cellphone"]
      }
    },
    methods: {
      onImageChange(event) {
        const file = event.target.files[0];
        if (file) {
          this.profilePreview = URL.createObjectURL(file);
          this.profileImage = file;
        }
      },
      async saveChanges() {
        this.loading = true;

        try {
          const formData = new FormData();
          formData.append("name", this.name);
          formData.append("email", this.email);
          formData.append("cellphone", this.phone);

          if (this.profileImage && this.profileImage instanceof File) {
            formData.append("picture", this.profileImage);
          }

          const response = await saveAccountInfo(formData);
          
          if (response) {
            this.loading = false;
            alert('Alterações salvas com sucesso!');
          } else {
            this.loading = false;
            alert('Ocorreu algum erro, tente novamente.');
          }
        } catch (error) {
          console.error('Erro ao salvar as alterações:', error.response || error);
          alert('Ocorreu um erro inesperado. Tente novamente mais tarde.');
        } finally {
          this.loading = false;
        }
      }
    },
  };
  </script>
  
  <style scoped>
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

  .account-info {
    margin: 1% auto;
    padding: 30px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    font-family: 'Arial', sans-serif;
    color: #333;
  }
  
  h2 {
    text-align: center;
    margin-bottom: 30px;
    font-size: 24px;
    font-weight: bold;
    color: #000;
  }
  
  .profile-image {
    text-align: center;
    margin-bottom: 30px;
  }
  
  .profile-image img {
    width: 8vw;
    height: 16vh;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid #000;
  }
  
  .profile-image input {
    margin-top: 10px;
    background-color: #000;
    color: #fff;
    border: none;
    padding: 10px;
    cursor: pointer;
    border-radius: 5px;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    font-size: 16px;
    font-weight: 500;
    margin-bottom: 8px;
    color: #333;
  }
  
  input {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    font-size: 16px;
    color: #333;
    transition: border-color 0.3s ease;
  }
  
  input:focus {
    border-color: #000;
    outline: none;
  }
  
  button {
    width: 100%;
    padding: 12px;
    background-color: #000;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #444;
  }
  </style>
  