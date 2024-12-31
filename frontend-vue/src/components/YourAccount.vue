<template>
    <div class="account-info">
      <h2>Informações da Conta</h2>
      
      <div class="profile-image">
        <img :src="profileImage" alt="Imagem de perfil" />
        <input type="file" @change="onImageChange" />
      </div>
      
      <form @submit.prevent="saveChanges">
        <div class="form-group">
          <label for="name">Nome:</label>
          <input
            type="text"
            id="name"
            v-model="accountInfo.name"
            placeholder="Digite seu nome"
          />
        </div>
        <div class="form-group">
          <label for="email">E-mail:</label>
          <input
            type="email"
            id="email"
            v-model="accountInfo.email"
            placeholder="Digite seu e-mail"
          />
        </div>
        <div class="form-group">
          <label for="phone">Telefone:</label>
          <input
            type="tel"
            id="phone"
            v-model="accountInfo.phone"
            placeholder="Digite seu telefone"
          />
        </div>
        <button type="submit">Salvar alterações</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        accountInfo: {
          name: '',
          email: '',
          phone: '',
        },
        // Imagem de perfil do cliente
        profileImage: 'https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg',
      };
    },
    methods: {
      onImageChange(event) {
        const file = event.target.files[0];
        if (file) {
          this.profileImage = URL.createObjectURL(file);
        }
      },
      saveChanges() {
        console.log('Alterações salvas:', this.accountInfo);
        alert('Alterações salvas com sucesso!');
      },
    },
  };
  </script>
  
  <style scoped>
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
  